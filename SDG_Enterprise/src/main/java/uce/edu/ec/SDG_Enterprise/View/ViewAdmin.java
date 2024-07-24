package uce.edu.ec.SDG_Enterprise.View;

import uce.edu.ec.SDG_Enterprise.Container.Controler;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class ViewAdmin extends JFrame {
    private Controler controler;
    private JPanel panelFabricacion;
    private String selectedProduct;
    List<String> productosPendientes;


    public ViewAdmin(Controler controler) {
        super("ViewAdmin");
        setUndecorated(true);
        setResizable(false);

        // Generaliza el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        int x = screenSize.width / 96;
        int y = screenSize.height / ((screenSize.height * 96) / screenSize.width);


        this.controler = controler;

        // Obtener lista inicial de productos pendientes
        productosPendientes = controler.getPendingRequestsDetails();

        //Panel que contiene todo
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(new Color(245, 245, 220));


        // Panel del encabezado
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setLayout(null);
        panelEncabezado.setBounds(-3, 0, (96 * x) + 6, 6 * y);
        panelEncabezado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // bienvenida al usuario
        JLabel jlClient = new JLabel("Bienvenido " + controler.userName());
        jlClient.setBounds(x, y, 62 * x, 4 * y);
        jlClient.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 40));
        jlClient.setHorizontalAlignment(SwingConstants.CENTER);
        jlClient.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(jlClient);

        CustomImageButton botonSalir = new CustomImageButton("/Recursos/salir.jpg", 14 * x, 4 * y);
        botonSalir.setBounds(81 * x, y, 14 * x, 4 * y);
        botonSalir.setContentAreaFilled(false);
        botonSalir.setBorderPainted(false);
        panelEncabezado.add(botonSalir);

        botonSalir.addActionListener(e -> {
            dispose();
        });

        panelPrincipal.add(panelEncabezado);


        // Configurar la lista de productos pendientes
        DefaultListModel<String> modelo = new DefaultListModel<>();
        productosPendientes = controler.getPendingRequestsDetails();
        for (String producto : productosPendientes) {
            modelo.addElement(producto);
        }

        JList<String> jlProductosPendientes = new JList<>(modelo);
        jlProductosPendientes.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        jlProductosPendientes.setOpaque(false);

        jlProductosPendientes.setPreferredSize(new Dimension(44 * x, 100 * y));

        JScrollPane jsProductosPendientes = new JScrollPane(jlProductosPendientes);
        jsProductosPendientes.setBounds(2 * x, 8 * y, 60 * x, 44 * y);
        jsProductosPendientes.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jsProductosPendientes.setOpaque(false);
        panelPrincipal.add(jsProductosPendientes);

        jlProductosPendientes.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedProduct = jlProductosPendientes.getSelectedValue();
                updateFabricationPanel(selectedProduct, x, y);
            }
        });

        // Configurar el panel de fabricación
        panelFabricacion = new JPanel();
        panelFabricacion.setLayout(null);
        panelFabricacion.setBounds(65 * x, 8 * y, 29 * x, 30 * y);
        panelFabricacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelFabricacion.setBackground(new Color(245, 245, 220));
        panelPrincipal.add(panelFabricacion);

        // Configurar botones
        JButton jbEliminar = new JButton("Eliminar");
        jbEliminar.setBounds(67*x, 40*y, 25*x, 5*y);
        jbEliminar.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        jbEliminar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jbEliminar);

        jbEliminar.addActionListener(e -> {

            if (selectedProduct != null) {
                controler.eliminarSolicitudPorId(selectedProduct);
                productosPendientes = controler.getPendingRequestsDetails();
                modelo.clear();
                for (String producto : productosPendientes) {
                    modelo.addElement(producto);
                }
                jlProductosPendientes.setModel(modelo);
                updateFabricationPanel(selectedProduct, x, y);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron solicitudes pendientes para el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        JButton jbFabricar = new JButton("Fabricar");
        jbFabricar.setBounds(67*x, 47*y, 25*x, 5*y);
        jbFabricar.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        jbFabricar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jbFabricar);

        jbFabricar.addActionListener(e -> {
            if (selectedProduct != null) {
                controler.fabricarProducto(selectedProduct);
                productosPendientes = controler.getPendingRequestsDetails();
                modelo.clear();
                for (String producto : productosPendientes) {
                    modelo.addElement(producto);
                }
                jlProductosPendientes.setModel(modelo);
                updateFabricationPanel(selectedProduct, x, y);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron solicitudes pendientes para el proyecto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });




        // Agregar panel principal al JFrame y hacer visible
        getContentPane().add(panelPrincipal);
        setVisible(true);
    }

    private void updateFabricationPanel(String selectedRequest, int x, int y) {
        panelFabricacion.removeAll();

        JLabel labelTitulo = new JLabel("Detalles de la solicitud en fabricación:");
        labelTitulo.setBounds(0, 0, 29 * x, 4 * y);
        labelTitulo.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        panelFabricacion.add(labelTitulo);

        // Aquí obtienes los detalles de la solicitud en estado "Fabricando..."
        List<String> makingRequestsDetails = controler.getMakingRequestsDetails();

        for (String details : makingRequestsDetails) {
            if (details.equals(selectedRequest)) { // Buscas la solicitud seleccionada
                JLabel labelDetalles = new JLabel(details);
                labelDetalles.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
                labelDetalles.setBounds(x, 5 * y, 18 * x, 3 * y);
                panelFabricacion.add(labelDetalles);
                break; // Terminas el ciclo una vez que encuentres la solicitud
            }
        }

        panelFabricacion.revalidate();
        panelFabricacion.repaint();
    }
}
