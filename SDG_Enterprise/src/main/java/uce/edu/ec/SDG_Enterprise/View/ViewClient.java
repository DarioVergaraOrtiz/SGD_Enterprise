package uce.edu.ec.SDG_Enterprise.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uce.edu.ec.SDG_Enterprise.Container.Controler;
import uce.edu.ec.SDG_Enterprise.Model.Product;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class ViewClient extends JFrame {
    Controler controler;
    private static final Logger log = LoggerFactory.getLogger(ViewClient.class);
    private DefaultListModel<String> carritoModel;
    private Set<Product> productosSeleccionados;
    private JLabel jtTotalCarrito;

    public ViewClient(Controler controler) {
        super("SDG_Enterprise");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Inicializa el carrito y productos seleccionados
        carritoModel = new DefaultListModel<>();
        productosSeleccionados = new HashSet<>();

        //Lienzo Princpal
        BackgroundPanel panelLienzo = new BackgroundPanel("/Logo/logo_sdg.jpg", 0.2f);
        panelLienzo.setBackground(new Color(255, 245, 220));
        panelLienzo.setLayout(null);
        this.controler = controler;

        //Panel de Productos
        JPanel productos = new JPanel();
        productos.setLayout(null);
        productos.setOpaque(false);
        productos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        productos.setBounds(50, 75, screenSize.width - 100, screenSize.height - 250);
        panelLienzo.add(productos);

        // Boton de Salir
        CustomImageButton jbSalir = new CustomImageButton("/Recursos/salir.jpg", 70, 65);
        jbSalir.setBounds(screenSize.width - 105, 5, 90, 65);
        panelLienzo.add(jbSalir);

        jbSalir.addActionListener(e -> {
            dispose();
        });

        // Boton para ver pedidos pendientes
        JButton jbPendientes = new JButton("Pedidos");
        jbPendientes.setBounds(screenSize.width - 240, 15, 125, 50);
        jbPendientes.setFont(new Font("Arial", Font.ITALIC, 25));
        panelLienzo.add(jbPendientes);


        // Nombre del Clientes
        JLabel jlClient = new JLabel("Bienvenido: " + controler.userName());
        Font fontjlClient = new Font("Sinserif", Font.ITALIC, 40);
        jlClient.setFont(fontjlClient);
        jlClient.setBounds(10, 10, 800, 55);
        panelLienzo.add(jlClient);

        // Botón para actualizar productos
        JButton jbActualizar = new JButton("Actualizar Productos");
        jbActualizar.setBounds(screenSize.width - 250, screenSize.height - 100, 200, 50);
        jbActualizar.setFont(new Font("Arial", Font.ITALIC, 20));
        jbActualizar.addActionListener(e -> cargarProductos(productos));
        panelLienzo.add(jbActualizar);

        cargarProductos(productos);

        // Carrito
        JList<String> jtCarrito = new JList<>(carritoModel);
        jtCarrito.setBounds(50, screenSize.height - 160, 200, 100);
        jtCarrito.setFont(new Font("Arial", Font.ITALIC, 15));
        jtCarrito.setOpaque(false);
        jtCarrito.setBackground(new Color(235, 235, 220));

        JScrollPane jsCarrito = new JScrollPane(jtCarrito);
        jsCarrito.setBounds(50, screenSize.height - 160, 200, 100);
        jsCarrito.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jsCarrito.setBackground(new Color(235, 235, 220));
        panelLienzo.add(jsCarrito);

        jtTotalCarrito = new JLabel();
        jtTotalCarrito.setBounds(50, screenSize.height - 50, 200, 40);
        jtTotalCarrito.setFont(new Font("Arial", Font.ITALIC, 25));
        jtTotalCarrito.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jtTotalCarrito.setBackground(new Color(235, 235, 220));
        panelLienzo.add(jtTotalCarrito);
        jtTotalCarrito.setText("Total: $0.00");

        //Reinciar Carrito
        JButton jbReiniciar = new JButton("Reiniciar Carrito");
        jbReiniciar.setBounds(350, screenSize.height - 120, 300, 50);
        jbReiniciar.setFont(new Font("Arial", Font.ITALIC, 25));
        jbReiniciar.addActionListener(e -> {
            carritoModel.clear();
            productosSeleccionados.clear();
            jtTotalCarrito.setText("Total: $0.00");
        });
        panelLienzo.add(jbReiniciar);

        //Reinciar Comprar
        JButton jbComprar = new JButton("Comprar");
        jbComprar.setBounds(750, screenSize.height - 120, 300, 50);
        jbComprar.setFont(new Font("Arial", Font.ITALIC, 25));
        jbComprar.addActionListener(e -> controler.realizarCompra(controler.getProduct()));
        panelLienzo.add(jbComprar);

        getContentPane().add(panelLienzo);
        this.setVisible(true);

    }

    private void cargarProductos(JPanel productosPanel) {
        productosPanel.removeAll();
        try {

            List<Product> productos = controler.getProduct();

            int y = 10;
            for (Product producto : productos) {
                JLabel productoLabel = new JLabel(producto.getName() + " - " + producto.getMaterial() + " - $" + producto.getPrice());
                productoLabel.setBounds(10, y, productosPanel.getWidth() - 20, 30);
                productoLabel.setOpaque(true);
                productoLabel.setBackground(new Color(235, 235, 220));
                productoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                // Añadir MouseListener para seleccionar producto
                productoLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!productosSeleccionados.contains(producto)) {
                            productosSeleccionados.add(producto);
                            carritoModel.addElement(producto.getName() + " - $" + producto.getPrice());

                            jtTotalCarrito.setText("Total: " + calcularTotal());
                        }
                    }
                });

                productosPanel.add(productoLabel);
                y += 40;
            }
            productosPanel.revalidate();
            productosPanel.repaint();
        } catch (Exception e) {
            log.error("Error loading products", e);
        }
    }
    private String calcularTotal() {
        double total = productosSeleccionados.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        return String.format("$%.2f", total);
    }



}
