package uce.edu.ec.SDG_Enterprise.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewAdmin extends JFrame {

    public ViewAdmin(){
        super("ViewAdmin");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);

        //Lienzo Princiapl
        JPanel panelPrincpal = new JPanel();
        panelPrincpal.setBackground(new Color(245,245,220));
        panelPrincpal.setLayout(null);

        // Boton de Salir
        CustomImageButton jbSalir = new CustomImageButton("/Recursos/salir.jpg", 70, 65);
        jbSalir.setBounds(screenSize.width - 105, 5, 90, 65);
        panelPrincpal.add(jbSalir);

        jbSalir.addActionListener(e -> {
            dispose();
        });

        //Bienvenida al usuario
        JLabel jlClient = new JLabel("Bienvenido " + "Dario");
        Font fontjlClient = new Font("Sinserif", Font.ITALIC, 40);
        jlClient.setFont(fontjlClient);
        jlClient.setBounds(10, 10, 800, 55);
        panelPrincpal.add(jlClient);

        // Carrito

        // Crear una lista de objetos (por ejemplo, productos)
        ArrayList<String> productosPendientes = new ArrayList<>();
        productosPendientes.add("Producto 1");
        productosPendientes.add("Producto 2");
        productosPendientes.add("Producto 3");
        productosPendientes.add("Producto 1");
        productosPendientes.add("Producto 2");
        productosPendientes.add("Producto 3");
        productosPendientes.add("Producto 1");
        productosPendientes.add("Producto 2");
        productosPendientes.add("Producto 3");

        // Crear un modelo para el JList
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (String producto : productosPendientes) {
            modelo.addElement(producto);
        }

        JList<String> jtProductosPendientes = new JList<>(modelo);
        //jtCarrito.setBounds(0, 0, (screenSize.width/2), 500);
        jtProductosPendientes.setFont(new Font("Arial", Font.ITALIC, 40));
        jtProductosPendientes.setOpaque(false);
        jtProductosPendientes.setBackground(new Color(235, 235, 220));

        // Crear el JScrollPane y agregar el JList
        JScrollPane jsProductosPendientes = new JScrollPane(jtProductosPendientes);
        jsProductosPendientes.setBounds(50, 150, (screenSize.width/2)-50, 500);
        jsProductosPendientes.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jsProductosPendientes.setBackground(new Color(235, 235, 220));
        jsProductosPendientes.setOpaque(false);
        panelPrincpal.add(jsProductosPendientes);

        //panel de fabricacion
        JPanel panelFabricacion = new JPanel();
        panelFabricacion.setBackground(new Color(245, 245, 220));
        panelFabricacion.setLayout(null);
        panelFabricacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelFabricacion.setBounds((screenSize.width/2)+40, 150, (screenSize.width/2)-80, 380);
        panelPrincpal.add(panelFabricacion);

        //Boton de eliminar
        JButton jbEliminar = new JButton("Eliminar");
        jbEliminar.setBounds((screenSize.width/2)+40, 540, (((screenSize.width/2)-80)/2)-10, 100);
        jbEliminar.setFont(new Font("Arial", Font.ITALIC, 40));
        panelPrincpal.add(jbEliminar);

        //Boton de Fabricar
        JButton jbFabricar = new JButton("Fabricar");
        jbFabricar.setBounds(((screenSize.width/2)+40)+(((screenSize.width/2)-80)/2)+10, 540, (((screenSize.width/2)-80)/2)-10, 100);
        jbFabricar.setFont(new Font("Arial", Font.ITALIC, 40));
        panelPrincpal.add(jbFabricar);

        getContentPane().add(panelPrincpal);
        setVisible(true);
    }
}
