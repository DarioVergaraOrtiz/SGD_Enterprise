package uce.edu.ec.SDG_Enterprise.View;

import uce.edu.ec.SDG_Enterprise.Container.Controler;
import uce.edu.ec.SDG_Enterprise.Sevice.ActualizacionPendientesWorker;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewAdmin extends JFrame {
    private Controler controler;
    private JPanel panelFabricacion;
    private String selectedProduct;
    List<String> productosPendientes;
    private DefaultListModel<String> modelo;




    public ViewAdmin(Controler controler) {
        super("ViewAdmin");
        setUndecorated(true);
        setResizable(false);
        startBackgroundUpdate();
        this.modelo = new DefaultListModel<>();

        // Generaliza el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        int x = screenSize.width / 96;
        int y = screenSize.height / ((screenSize.height * 96) / screenSize.width);

        this.controler = controler;

        JList<String> jlProductosPendientes = new JList<>(this.modelo);


        // Obtener lista inicial de productos pendientes
        productosPendientes = controler.getPendingRequestsDetails();

        // Panel que contiene
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(new Color(245, 245, 220));

        // Panel del encabezado
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setLayout(null);
        panelEncabezado.setBounds(-3, 0, (96 * x) + 6, 6 * y);
        panelEncabezado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Bienvenida al usuario
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
                jlProductosPendientes.repaint();
            }
        });

        panelFabricacion = new JPanel();
        panelFabricacion.setLayout(new BoxLayout(panelFabricacion, BoxLayout.Y_AXIS));
        panelFabricacion.setBackground(new Color(245, 245, 220));

        JScrollPane scrollPanelFabricacion = new JScrollPane(panelFabricacion);
        scrollPanelFabricacion.setBounds(65 * x, 8 * y, 29 * x, 30 * y);
        scrollPanelFabricacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        scrollPanelFabricacion.setBackground(new Color(245, 245, 220));
        scrollPanelFabricacion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanelFabricacion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelPrincipal.add(scrollPanelFabricacion);

        // Configurar botones
        JButton jbEliminar = new JButton("Eliminar");
        jbEliminar.setBounds(67 * x, 40 * y, 25 * x, 5 * y);
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
        jbFabricar.setBounds(67 * x, 47 * y, 25 * x, 5 * y);
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
    private void startBackgroundUpdate() {
        Thread updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        actualizarListaProductosPendientes();
                        Thread.sleep(5000); // Espera 5 segundos antes de actualizar nuevamente
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        updateThread.setDaemon(true); // Permite que el hilo se detenga cuando se cierra la aplicación
        updateThread.start();

    }
    private void actualizarListaProductosPendientes() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                List<String> productosPendientes = controler.getPendingRequestsDetails();
                modelo.clear();
                for (String producto : productosPendientes) {
                    modelo.addElement(producto);
                }
            }
        });
    }
    private void updateFabricationPanel(String selectedRequest, int x, int y) {
        panelFabricacion.removeAll();

        JLabel labelTitulo = new JLabel("Detalles de la solicitud en fabricación:");
        labelTitulo.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        panelFabricacion.add(labelTitulo);

        List<String> makingRequestsDetails = controler.getMakingRequestsDetails();

        for (String details : makingRequestsDetails) {
                JLabel labelDetalles = new JLabel(details);
                labelDetalles.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 16));
                panelFabricacion.add(labelDetalles);

        }


        panelFabricacion.revalidate();
        panelFabricacion.repaint();
    }


}
