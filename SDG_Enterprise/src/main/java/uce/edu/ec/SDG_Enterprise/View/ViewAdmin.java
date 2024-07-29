package uce.edu.ec.SDG_Enterprise.View;

import uce.edu.ec.SDG_Enterprise.Container.Controler;
import uce.edu.ec.SDG_Enterprise.Model.Process;
import uce.edu.ec.SDG_Enterprise.Model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewAdmin extends JFrame {
    private Controler controler;
    private JPanel panelFabricacion;
    private String selectedProduct;
    List<String> productosPendientes;
    private DefaultListModel<String> modelo;
    DefaultListModel<String> modeloProcesos;
    DefaultListModel<String> modeloProductos;
    JScrollPane scrollPanelFabricacion;
    private ExecutorService executorService;

    public ViewAdmin(Controler controler) {
        super("ViewAdmin");
        setUndecorated(true);
        setResizable(false);
        modelo = new DefaultListModel<>();
        modeloProcesos = new DefaultListModel<>();
        modeloProductos = new DefaultListModel<>();
        executorService = Executors.newFixedThreadPool(3);

        // Generaliza el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        int x = screenSize.width / 96;
        int y = screenSize.height / ((screenSize.height * 96) / screenSize.width);

        this.controler = controler;

        JList<String> jlProductosPendientes = new JList<>(modelo);
        productosPendientes = controler.getPendingRequestsDetails();

        // Panel que contiene
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 245, 220));

        // Panel del encabezado
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setLayout(null);
        panelEncabezado.setPreferredSize(new Dimension(screenSize.width, 6 * y));
        panelEncabezado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Bienvenida al usuario
        JLabel jlClient = new JLabel("Bienvenido " + controler.userName());
        jlClient.setBounds(x, y, 62 * x, 4 * y);
        jlClient.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 40));
        jlClient.setHorizontalAlignment(SwingConstants.CENTER);
        jlClient.setVerticalAlignment(SwingConstants.CENTER);
        panelEncabezado.add(jlClient);

        CustomImageButton botonSalir = new CustomImageButton("/Recursos/salir.jpg", 14 * x, 4 * y);
        botonSalir.setBounds(81 * x, y, 14 * x, 4 * y);
        botonSalir.setContentAreaFilled(false);
        botonSalir.setBorderPainted(false);
        panelEncabezado.add(botonSalir);

        botonSalir.addActionListener(e -> dispose());


        panelPrincipal.add(panelEncabezado, BorderLayout.NORTH);

        // Configurar las pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel de Fabricación
        JPanel tabFabricacion = new JPanel();
        tabFabricacion.setLayout(null);
        tabFabricacion.setBackground(new Color(245, 245, 220));

        // Configurar la lista de productos pendientes
        JScrollPane jsProductosPendientes = new JScrollPane(jlProductosPendientes);
        jsProductosPendientes.setBounds(2 * x, 2 * y, 60 * x, 44 * y);
        jsProductosPendientes.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jsProductosPendientes.setOpaque(false);
        tabFabricacion.add(jsProductosPendientes);

        jlProductosPendientes.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        jlProductosPendientes.setOpaque(false);
        jlProductosPendientes.setPreferredSize(new Dimension(44 * x, 100 * y));

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

        scrollPanelFabricacion = new JScrollPane(panelFabricacion);
        scrollPanelFabricacion.setBounds(65 * x, 2 * y, 29 * x, 30 * y);
        scrollPanelFabricacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        scrollPanelFabricacion.setBackground(new Color(245, 245, 220));
        scrollPanelFabricacion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanelFabricacion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tabFabricacion.add(scrollPanelFabricacion);

        // Configurar botones en la pestaña de Fabricación
        JButton jbEliminar = new JButton("Eliminar");
        jbEliminar.setBounds(67 * x, 33 * y, 25 * x, 5 * y);
        jbEliminar.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        jbEliminar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tabFabricacion.add(jbEliminar);

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
        jbFabricar.setBounds(67 * x, 40 * y, 25 * x, 5 * y);
        jbFabricar.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 20));
        jbFabricar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tabFabricacion.add(jbFabricar);

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


        tabbedPane.addTab("Fabricación", tabFabricacion);

        // Panel de Procesos (vacío por ahora)
        JPanel tabProcesos = new JPanel();
        tabProcesos.setLayout(new BorderLayout());
        tabProcesos.setBackground(new Color(245, 245, 220));
        tabbedPane.addTab("Procesos", tabProcesos);

        JPanel panelNuevoProceso = new JPanel();
        panelNuevoProceso.setLayout(new GridLayout(3, 2, 10, 10));
        panelNuevoProceso.setBorder(BorderFactory.createTitledBorder("Ingresar Nuevo Proceso"));
        panelNuevoProceso.setBackground(new Color(245, 245, 220));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblTiempo = new JLabel("Tiempo:");
        JTextField txtTiempo = new JTextField();
        JButton btnIngresar = new JButton("Ingresar");

        panelNuevoProceso.add(lblNombre);
        panelNuevoProceso.add(txtNombre);
        panelNuevoProceso.add(lblTiempo);
        panelNuevoProceso.add(txtTiempo);
        panelNuevoProceso.add(new JLabel());
        panelNuevoProceso.add(btnIngresar);

// Acción del botón Ingresar
        btnIngresar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String tiempoStr = txtTiempo.getText();
            try {
                int tiempo = Integer.parseInt(tiempoStr);

                // Crear un panel para el JOptionPane
                JPanel panel = new JPanel(new GridLayout(2, 1));
                JLabel lblMaterial = new JLabel("A qué material pertenece este proceso:");
                JTextField txtMaterial = new JTextField();

                panel.add(lblMaterial);
                panel.add(txtMaterial);


                int result = JOptionPane.showConfirmDialog(null, panel, "Ingresar Material", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String material = txtMaterial.getText();
                    long id = controler.addProcess(nombre, material, tiempo).getId();
                    System.out.println(material);
                    controler.processMaterial(material, id);
                    JOptionPane.showMessageDialog(null, "Proceso ingresado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Proceso cancelado.", "Cancelación", JOptionPane.WARNING_MESSAGE);
                }

                txtNombre.setText("");
                txtTiempo.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un tiempo válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

// Panel para modificar el tiempo de un proceso existente
        JPanel panelModificarProceso = new JPanel();
        panelModificarProceso.setLayout(new GridLayout(3, 2, 10, 10));
        panelModificarProceso.setBorder(BorderFactory.createTitledBorder("Modificar Tiempo de Proceso"));
        panelModificarProceso.setBackground(new Color(245, 245, 220));

        JLabel lblNombreModificar = new JLabel("Nombre:");
        JTextField txtNombreModificar = new JTextField();
        JLabel lblNuevoTiempo = new JLabel("Nuevo Tiempo:");
        JTextField txtNuevoTiempo = new JTextField();
        JButton btnActualizar = new JButton("Actualizar");

        panelModificarProceso.add(lblNombreModificar);
        panelModificarProceso.add(txtNombreModificar);
        panelModificarProceso.add(lblNuevoTiempo);
        panelModificarProceso.add(txtNuevoTiempo);
        panelModificarProceso.add(new JLabel());  // Placeholder for layout
        panelModificarProceso.add(btnActualizar);

        btnActualizar.addActionListener(e -> {
            String nombreModificar = txtNombreModificar.getText();
            String nuevoTiempoStr = txtNuevoTiempo.getText();
            try {
                double nuevoTiempo = Double.parseDouble(nuevoTiempoStr);
                controler.updateProcessTime(nombreModificar, nuevoTiempo);

                JOptionPane.showMessageDialog(null, "Tiempo de proceso actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtNombreModificar.setText("");
                txtNuevoTiempo.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un tiempo válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        JList<String> jlProcesos = new JList<>(modeloProcesos);
        JScrollPane scrollProcesos = new JScrollPane(jlProcesos);
        scrollProcesos.setBorder(BorderFactory.createTitledBorder("Procesos Existentes"));
        scrollProcesos.setBackground(new Color(245, 245, 220));

        tabProcesos.add(panelNuevoProceso, BorderLayout.NORTH);
        tabProcesos.add(scrollProcesos, BorderLayout.CENTER);
        tabProcesos.add(panelModificarProceso, BorderLayout.SOUTH);

        tabbedPane.addTab("Procesos", tabProcesos);
        List<Process> procesos = controler.getAllProcess();
        for (Process process : procesos) {
            modeloProcesos.addElement(process.getNameProcess() + "  " + process.getTime());
        }
        jlProcesos.setModel(modeloProcesos);

        JPanel tabProductos = new JPanel();
        tabProductos.setLayout(new BorderLayout());
        tabProductos.setBackground(new Color(245, 245, 220));
        tabbedPane.addTab("Productos", tabProductos);

        // Panel para ingresar un nuevo producto
        JPanel panelNuevoProducto = new JPanel();
        panelNuevoProducto.setLayout(new GridLayout(4, 2, 10, 10));
        panelNuevoProducto.setBorder(BorderFactory.createTitledBorder("Ingresar Nuevo Producto"));
        panelNuevoProducto.setBackground(new Color(245, 245, 220));

        JLabel lblNombreProducto = new JLabel("Nombre:");
        JTextField txtNombreProducto = new JTextField();
        JLabel lblMaterialProducto = new JLabel("Material:");
        JTextField txtMaterialProducto = new JTextField();
        JLabel lblPrecioProducto = new JLabel("Precio:");
        JTextField txtPrecioProducto = new JTextField();
        JButton btnIngresarProducto = new JButton("Ingresar");

        panelNuevoProducto.add(lblNombreProducto);
        panelNuevoProducto.add(txtNombreProducto);
        panelNuevoProducto.add(lblMaterialProducto);
        panelNuevoProducto.add(txtMaterialProducto);
        panelNuevoProducto.add(lblPrecioProducto);
        panelNuevoProducto.add(txtPrecioProducto);
        panelNuevoProducto.add(new JLabel());
        panelNuevoProducto.add(btnIngresarProducto);

        // Acción del botón Ingresar
        btnIngresarProducto.addActionListener(e -> {
            String nombre = txtNombreProducto.getText();
            String material = txtMaterialProducto.getText();
            String precioStr = txtPrecioProducto.getText();
            try {
                double precio = Double.parseDouble(precioStr);
                controler.addProduct(nombre, material, precio);
                JOptionPane.showMessageDialog(null, "Producto ingresado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtNombreProducto.setText("");
                txtMaterialProducto.setText("");
                txtPrecioProducto.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panelModificarProducto = new JPanel();
        panelModificarProducto.setLayout(new GridLayout(4, 2, 10, 10));
        panelModificarProducto.setBorder(BorderFactory.createTitledBorder("Modificar Precio de Producto"));
        panelModificarProducto.setBackground(new Color(245, 245, 220));

        JLabel lblNombreModificarProducto = new JLabel("Nombre:");
        JTextField txtNombreModificarProducto = new JTextField();
        JLabel lblMaterialModificarProducto = new JLabel("Material:");
        JTextField txtMaterialModificarProducto = new JTextField();
        JLabel lblNuevoPrecioProducto = new JLabel("Nuevo Precio:");
        JTextField txtNuevoPrecioProducto = new JTextField();
        JButton btnActualizarProducto = new JButton("Actualizar");

        panelModificarProducto.add(lblNombreModificarProducto);
        panelModificarProducto.add(txtNombreModificarProducto);
        panelModificarProducto.add(lblMaterialModificarProducto);
        panelModificarProducto.add(txtMaterialModificarProducto);
        panelModificarProducto.add(lblNuevoPrecioProducto);
        panelModificarProducto.add(txtNuevoPrecioProducto);
        panelModificarProducto.add(new JLabel());
        panelModificarProducto.add(btnActualizarProducto);

// Acción del botón Actualizar
        btnActualizarProducto.addActionListener(e -> {
            String nombreModificar = txtNombreModificarProducto.getText();
            String materialModificar = txtMaterialModificarProducto.getText();
            String nuevoPrecioStr = txtNuevoPrecioProducto.getText();
            try {
                double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
                controler.changePrice(nombreModificar, materialModificar, nuevoPrecio);
                JOptionPane.showMessageDialog(null, "Precio de producto actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtNombreModificarProducto.setText("");
                txtMaterialModificarProducto.setText("");
                txtNuevoPrecioProducto.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        JList<String> jlProductos = new JList<>(modeloProductos);
        JScrollPane scrollProductos = new JScrollPane(jlProductos);
        scrollProductos.setBorder(BorderFactory.createTitledBorder("Productos Existentes"));
        scrollProductos.setBackground(new Color(245, 245, 220));


        tabProductos.add(panelNuevoProducto, BorderLayout.NORTH);
        tabProductos.add(scrollProductos, BorderLayout.CENTER);
        tabProductos.add(panelModificarProducto, BorderLayout.SOUTH);

        tabbedPane.addTab("Productos", tabProductos);
        List<Product> products = controler.getProduct();
        for (Product product : products) {
            modeloProductos.addElement(product.getName() + "  " + product.getMaterial() + "  " + String.valueOf(product.getPrice()));
        }
        jlProductos.setModel(modeloProductos);

        panelPrincipal.add(tabbedPane, BorderLayout.CENTER);


        getContentPane().add(panelPrincipal);
        setVisible(true);


        actualizaciones();
    }

    private void actualizaciones() {
        controler.startBackgroundUpdate(this::actualizarListaProcesos);
        controler.startBackgroundUpdate(this::actualizarListaProductos);
        controler.startBackgroundUpdate(this::actualizarListaProductosPendientes);
    }


    private void actualizarListaProcesos() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Actualizando lista de Procesos");
            List<Process> proces = controler.getAllProcess();
            modeloProcesos.clear();
            for (Process process : proces) {
                modeloProcesos.addElement(process.getNameProcess() + " " + process.getTime());
            }
        });
    }

    private void actualizarListaProductos() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Actualizando lista de productos");
            List<Product> productos = controler.getProduct();
            modeloProductos.clear();
            for (Product product : productos) {
                modeloProductos.addElement(product.getName() + " " + product.getMaterial() + " " + String.valueOf(product.getPrice()));
            }
        });
    }

    private void actualizarListaProductosPendientes() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Actualizando lista de productos pendientes");
            List<String> productosPendientes = controler.getPendingRequestsDetails();
            modelo.clear();
            for (String producto : productosPendientes) {
                modelo.addElement(producto);
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
