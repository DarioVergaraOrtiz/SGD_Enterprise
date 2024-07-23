package uce.edu.ec.SDG_Enterprise.View;

import uce.edu.ec.SDG_Enterprise.Container.Controler;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class ViewAdmin extends JFrame {
    private Controler controler;
    private List<String> productosPendientes;
    private JList<String> jtProductosPendientes;
    private JPanel panelFabricacion;

    public ViewAdmin(Controler controler) {
        super("ViewAdmin");
        this.controler = controler;

        // Obtener lista inicial de productos pendientes
        productosPendientes = controler.getPendingRequestsDetails();

        // Configurar el JFrame
        setupFrame();

        // Configurar el panel principal
        JPanel panelPrincipal = setupMainPanel();

        // Configurar la lista de productos pendientes
        setupProductList(panelPrincipal);

        // Configurar el panel de fabricación
        setupFabricationPanel(panelPrincipal);

        // Configurar botones
        setupButtons(panelPrincipal);

        // Agregar panel principal al JFrame y hacer visible
        getContentPane().add(panelPrincipal);
        setVisible(true);
    }

    private void setupFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel setupMainPanel() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(245, 245, 220));
        panelPrincipal.setLayout(null);

        // Primer mensaje de bienvenida
        JLabel jlClient = new JLabel("Bienvenido " + controler.userName());
        Font fontjlClient = new Font("SansSerif", Font.ITALIC, 40);
        jlClient.setFont(fontjlClient);
        jlClient.setBounds(10, 10, 800, 55);
        panelPrincipal.add(jlClient);

       /* // Segundo mensaje de bienvenida
        JLabel jlClient2 = new JLabel("Bienvenido " + controler.userName());
        Font fontjlClient2 = new Font("Serif", Font.BOLD, 30);
        jlClient2.setFont(fontjlClient2);
        jlClient2.setBounds(50, 50, 500, 40);
        panelPrincipal.add(jlClient2);*/

        return panelPrincipal;
    }

    private void setupProductList(JPanel panelPrincipal) {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (String producto : productosPendientes) {
            modelo.addElement(producto);
        }

        jtProductosPendientes = new JList<>(modelo);
        jtProductosPendientes.setFont(new Font("Arial", Font.PLAIN, 20));
        jtProductosPendientes.setOpaque(false);

        JScrollPane jsProductosPendientes = new JScrollPane(jtProductosPendientes);
        jsProductosPendientes.setBounds(50, 150, (getWidth() / 2) - 100, 500);
        jsProductosPendientes.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jsProductosPendientes.setBackground(new Color(235, 235, 220));
        jsProductosPendientes.setOpaque(false);
        panelPrincipal.add(jsProductosPendientes);

        jtProductosPendientes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedProduct = jtProductosPendientes.getSelectedValue();
                    updateFabricationPanel(selectedProduct);
                }
            }
        });
    }

    private void setupFabricationPanel(JPanel panelPrincipal) {
        panelFabricacion = new JPanel();
        panelFabricacion.setBackground(new Color(245, 245, 220));
        panelFabricacion.setLayout(null);
        panelFabricacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelFabricacion.setBounds((getWidth() / 2) + 50, 150, (getWidth() / 2) - 100, 300);
        panelPrincipal.add(panelFabricacion);
    }

    private void setupButtons(JPanel panelPrincipal) {
        JButton jbEliminar = new JButton("Eliminar");
        jbEliminar.setBounds((getWidth() / 2) + 50, 500, 150, 50);
        jbEliminar.setFont(new Font("Arial", Font.PLAIN, 20));
        panelPrincipal.add(jbEliminar);

        JButton jbFabricar = new JButton("Fabricar");
        jbFabricar.setBounds((getWidth() / 2) + 220, 500, 150, 50);
        jbFabricar.setFont(new Font("Arial", Font.PLAIN, 20));
        panelPrincipal.add(jbFabricar);
        jbEliminar.addActionListener(e -> {
            String selectedProduct = jtProductosPendientes.getSelectedValue();
            if (selectedProduct != null) {
                controler.eliminarSolicitudPorId(selectedProduct);
                productosPendientes = controler.getPendingRequestsDetails();
                DefaultListModel<String> modelo = new DefaultListModel<>();
                for (String producto : productosPendientes) {
                    modelo.addElement(producto);
                }
                jtProductosPendientes.setModel(modelo);
                updateFabricationPanel(selectedProduct);
            } else {
                JOptionPane.showMessageDialog(ViewAdmin.this, "Seleccione un producto para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        jbFabricar.addActionListener(e -> {
            String selectedProduct = jtProductosPendientes.getSelectedValue();
            if (selectedProduct != null) {
                controler.fabricarProducto(selectedProduct);
                productosPendientes = controler.getPendingRequestsDetails();
                DefaultListModel<String> modelo = new DefaultListModel<>();
                for (String producto : productosPendientes) {
                    modelo.addElement(producto);
                }
                jtProductosPendientes.setModel(modelo);
                updateFabricationPanel(selectedProduct);
            } else {
                JOptionPane.showMessageDialog(ViewAdmin.this, "Seleccione un producto para fabricar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void updateFabricationPanel(String selectedRequest) {
        panelFabricacion.removeAll();

        JLabel labelTitulo = new JLabel("Detalles de la solicitud en fabricación:");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setBounds(10, 10, 300, 30);
        panelFabricacion.add(labelTitulo);

        // Aquí obtienes los detalles de la solicitud en estado "Fabricando..."
        List<String> makingRequestsDetails = controler.getMakingRequestsDetails();

        for (String details : makingRequestsDetails) {
            if (details.equals(selectedRequest)) { // Buscas la solicitud seleccionada
                JLabel labelDetalles = new JLabel(details);
                labelDetalles.setFont(new Font("Arial", Font.PLAIN, 16));
                labelDetalles.setBounds(10, 50, 600, 30);
                panelFabricacion.add(labelDetalles);
                break; // Terminas el ciclo una vez que encuentres la solicitud
            }
        }

        panelFabricacion.revalidate();
        panelFabricacion.repaint();
    }




}
