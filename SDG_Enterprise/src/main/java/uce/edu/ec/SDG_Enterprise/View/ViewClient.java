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
        setUndecorated(true);
        setResizable(false);

        // Generaliza el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        int x = screenSize.width / 96;
        int y = screenSize.height / ((screenSize.height * 96) / screenSize.width);


        // Inicializa el carrito y productos seleccionados
        carritoModel = new DefaultListModel<>();
        productosSeleccionados = new HashSet<>();

        //Lienzo Princpal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);

        this.controler = controler;

        //Panel Encabezado y sus componentes
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setLayout(null);
        panelEncabezado.setBounds(-3, 0, (96 * x) + 6, 6 * y);
        panelEncabezado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Nombre del Clientes
        JLabel jlClient = new JLabel("¡SDG Enterprise te da la bienvenida " + controler.userName() + "!");
        jlClient.setBounds(2 * x, y, 53 * x, 4 * y);
        jlClient.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 35));
        jlClient.setHorizontalAlignment(SwingConstants.CENTER);
        panelEncabezado.add(jlClient);

        // Boton de Salir
        CustomImageButton jbSalir = new CustomImageButton("/Recursos/salir.jpg", 10 * x, 4 * y);
        jbSalir.setBounds(83 * x, y, 10 * x, 4 * y);
        jbSalir.setContentAreaFilled(false);
        jbSalir.setBorderPainted(false);
        panelEncabezado.add(jbSalir);

        jbSalir.addActionListener(e -> {
            dispose();
        });

        // Boton para ver pedidos pendientesjua
        JButton jbPendientes = new JButton("Pedidos");
        jbPendientes.setBounds(71 * x, y, 10 * x, 4 * y);
        jbPendientes.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 30));

        jbPendientes.addActionListener(e -> {
            PedidosUI pedidos = new PedidosUI(controler);
        });
        panelEncabezado.add(jbPendientes);

        panelPrincipal.add(panelEncabezado);

        //Panel de Productos
        JPanel productos = new JPanel();
        productos.setLayout(null);
        productos.setOpaque(false);
        productos.setBounds(2 * x, 7 * y, 92 * x, 32 * y);
        productos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(productos);

        // Carrito y sus funcionalidad

        // Icono Carrito
        BackgroundLabel jlCarrito = new BackgroundLabel("/Recursos/carrito.jpg", 1.0f);
        jlCarrito.setBounds(2 * x, 42 * y + (y / 2), 6 * x, 5 * y);
        panelPrincipal.add(jlCarrito);

        JList<String> jtCarrito = new JList<>(carritoModel);
        jtCarrito.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        jtCarrito.setOpaque(false);
        jtCarrito.setBackground(new Color(235, 235, 220));

        jtCarrito.setPreferredSize(new Dimension(23 * x, 20 * y));
        JScrollPane jsCarrito = new JScrollPane(jtCarrito);
        jsCarrito.setBounds(9 * x, 40 * y, 25 * x, 10 * y);
        jsCarrito.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jsCarrito.setBackground(new Color(235, 235, 220));
        jsCarrito.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelPrincipal.add(jsCarrito);

        //Total Carrito
        jtTotalCarrito = new JLabel();
        jtTotalCarrito.setBounds(9 * x, 50 * y, 25 * x, 2 * y);
        jtTotalCarrito.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 25));
        jtTotalCarrito.setBackground(new Color(235, 235, 220));
        jtTotalCarrito.setHorizontalAlignment(SwingConstants.RIGHT);
        //jtTotalCarrito.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jtTotalCarrito);
        jtTotalCarrito.setText("Total: $0.00");

        // Boton Reinciar Carrito
        JButton jbReiniciar = new JButton("Reiniciar Carrito");
        jbReiniciar.setBounds(37 * x, 41 * y, 20 * x, 5 * y);
        jbReiniciar.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        panelPrincipal.add(jbReiniciar);

        jbReiniciar.addActionListener(e -> {
            carritoModel.clear();
            productosSeleccionados.clear();
            jtTotalCarrito.setText("Total: $0.00");
        });

        // Boton Comprar
        JButton jbComprar = new JButton("Comprar");
        jbComprar.setBounds(37 * x, 47 * y, 20 * x, 5 * y);
        jbComprar.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        panelPrincipal.add(jbComprar);

        jbComprar.addActionListener(e -> {
            controler.realizarCompra(productosSeleccionados);
            carritoModel.clear();
            productosSeleccionados.clear();
            jtTotalCarrito.setText("Total: $0.00");
        });

        // Botón para actualizar productos
        JButton jbActualizar = new JButton("Actualizar Productos");
        jbActualizar.setBounds(59 * x, 41 * y, 20 * x, 5 * y);
        jbActualizar.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        panelPrincipal.add(jbActualizar);

        jbActualizar.addActionListener(e -> cargarProductos(productos));

        cargarProductos(productos);

        //Logo Empresa
        BackgroundLabel jlLogo = new BackgroundLabel("/Logo/logo_sdg.jpg", 1.0f);
        jlLogo.setBounds(82 * x, 40 * y, 12 * x, 12 * y);
        panelPrincipal.add(jlLogo);

        getContentPane().add(panelPrincipal);
        this.setVisible(true);

    }

    private void cargarProductos(JPanel productosPanel) {
        SwingUtilities.invokeLater(() -> {
            controler.addProduct();
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
        });
    }


    private String calcularTotal() {
        double total = productosSeleccionados.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        return String.format("$%.2f", total);
    }


}
