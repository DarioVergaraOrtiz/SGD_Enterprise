package uce.edu.ec.SDG_Enterprise.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewClient extends JFrame {

    private static final Logger log = LoggerFactory.getLogger(ViewClient.class);

    public ViewClient() {
        super("SDG_Enterprise");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);

        //Lienzo Princpal
        BackgroundPanel panelLienzo = new BackgroundPanel("/Logo/logo_sdg.jpg", 0.2f);
        panelLienzo.setBackground(new Color(255, 245, 220));
        panelLienzo.setLayout(null);

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
        JLabel jlClient = new JLabel("Bienvenido " + "Dario");
        Font fontjlClient = new Font("Sinserif", Font.ITALIC, 40);
        jlClient.setFont(fontjlClient);
        jlClient.setBounds(10, 10, 800, 55);
        panelLienzo.add(jlClient);

        // Carrito

        // Crear una lista de objetos (por ejemplo, productos)
        ArrayList<String> prodcutosCarrito = new ArrayList<>();
        prodcutosCarrito.add("Producto 1");
        prodcutosCarrito.add("Producto 2");
        prodcutosCarrito.add("Producto 3");
        prodcutosCarrito.add("Producto 1");
        prodcutosCarrito.add("Producto 2");
        prodcutosCarrito.add("Producto 3");
        prodcutosCarrito.add("Producto 1");
        prodcutosCarrito.add("Producto 2");
        prodcutosCarrito.add("Producto 3");


        // Crear un modelo para el JList
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (String producto : prodcutosCarrito) {
            modelo.addElement(producto);
        }

        JList<String> jtCarrito = new JList<>(modelo);
        jtCarrito.setBounds(50, screenSize.height - 160, 200, 100);
        jtCarrito.setFont(new Font("Arial", Font.ITALIC, 15));
        jtCarrito.setOpaque(false);
        jtCarrito.setBackground(new Color(235, 235, 220));

        // Crear el JScrollPane y agregar el JList
        JScrollPane jsCarrito = new JScrollPane(jtCarrito);
        jsCarrito.setBounds(50, screenSize.height - 160, 200, 100);
        jsCarrito.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jsCarrito.setBackground(new Color(235, 235, 220));
        panelLienzo.add(jsCarrito);

        // Calcular el total
        JLabel jtTotalCarrito = new JLabel();
        jtTotalCarrito.setBounds(50, screenSize.height - 50, 200, 40);
        jtTotalCarrito.setFont(new Font("Arial", Font.ITALIC, 25));
        jtTotalCarrito.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jtTotalCarrito.setBackground(new Color(235, 235, 220));
        panelLienzo.add(jtTotalCarrito);
        jtTotalCarrito.setText("Total: " + "$100.00");

        //Reinciar Carrito
        JButton jbReiniciar = new JButton("Reiniciar Carrito");
        jbReiniciar.setBounds(350, screenSize.height - 120, 300, 50);
        jbReiniciar.setFont(new Font("Arial", Font.ITALIC, 25));
        panelLienzo.add(jbReiniciar);

        //Reinciar Comprar
        JButton jbComprar = new JButton("Comprar");
        jbComprar.setBounds(750, screenSize.height - 120, 300, 50);
        jbComprar.setFont(new Font("Arial", Font.ITALIC, 25));
        panelLienzo.add(jbComprar);




        getContentPane().add(panelLienzo);
        this.setVisible(true);

    }
}
