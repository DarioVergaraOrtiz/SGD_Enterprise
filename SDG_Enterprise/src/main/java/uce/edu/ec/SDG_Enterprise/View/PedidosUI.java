package uce.edu.ec.SDG_Enterprise.View;

import uce.edu.ec.SDG_Enterprise.Container.Controler;
import uce.edu.ec.SDG_Enterprise.Model.Product;
import uce.edu.ec.SDG_Enterprise.Model.Requested;
import uce.edu.ec.SDG_Enterprise.Model.User;
import uce.edu.ec.SDG_Enterprise.Sevice.Repository.INotify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class PedidosUI extends JFrame implements INotify {

    Controler controler;
    int presionar = 0;
    List<Requested> pedidos;
    DefaultListModel<String> modeloidPedidos;
    DefaultListModel<String> modeloNombreProducto;
    DefaultListModel<String> modeloEstado;

    public PedidosUI(Controler controler) {; // Establecer el JDialog como modal
        setUndecorated(true);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Generalizar valores de pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        int x = screenSize.width / 96;
        int y = screenSize.height / ((screenSize.height * 96) / screenSize.width);
        this.controler = controler;
        pedidos = controler.getRequestedByUserId(controler.userId());
        modeloidPedidos = new DefaultListModel<>();
        modeloNombreProducto = new DefaultListModel<>();
        modeloEstado = new DefaultListModel<>();

        setBounds(27 * x, 5 * y, 42 * x, 44 * y);

        // Lienzo principal donde se agrega cualquier cosa
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(new Color(245, 245, 220));
        panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Panel Encabezado
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setLayout(null);
        panelEncabezado.setBounds(0, 0, 42 * x, 6 * y);
        panelEncabezado.setBackground(Color.WHITE);
        panelEncabezado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // de quien es el pedido
        JLabel lbUsuario = new JLabel("Usuario: " + controler.userName());
        lbUsuario.setBounds(x, y, 34 * x, 4 * y);
        lbUsuario.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 40));
        panelEncabezado.add(lbUsuario);

        // Botón de salir
        CustomImageButton botonSalir = new CustomImageButton("/Recursos/salir 2.jpg", 5 * x, 5 * y);
        botonSalir.setBounds(36 * x, y, 5 * x, 4 * y);
        botonSalir.setContentAreaFilled(false);
        botonSalir.setBorderPainted(false);
        panelEncabezado.add(botonSalir);

        botonSalir.addActionListener(e -> dispose());

        panelPrincipal.add(panelEncabezado);

        // Contenido
        JLabel jlProceso = new JLabel("¡GRACIAS POR PREFERIRNOS!");
        jlProceso.setBounds(2 * x, 8 * y, 38 * x, 4 * y);
        jlProceso.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 35));
        jlProceso.setVerticalAlignment(SwingConstants.CENTER);
        jlProceso.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(jlProceso);

        // Notificaciones
        JLabel jlNotificacion = new JLabel("LISTA DE PEDIDOS");
        jlNotificacion.setBounds(5 * x, 14 * y, 32 * x, 4 * y);
        jlNotificacion.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 40));
        jlNotificacion.setHorizontalAlignment(SwingConstants.CENTER);
        jlNotificacion.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(jlNotificacion);

        // Contenido notificaciones
        JPanel panelNotificaciones = new JPanel();
        panelNotificaciones.setLayout(null);
        panelNotificaciones.setBounds(2 * x, 20 * y, 38 * x, 22 * y);
        panelNotificaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(panelNotificaciones);

        JLabel labelNombrePedido = new JLabel("NUMERO DE PEDIDO");
        labelNombrePedido.setBounds(0,0, 28*x, 3*y);
        labelNombrePedido.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 25));
        labelNombrePedido.setForeground(Color.BLACK);
        labelNombrePedido.setHorizontalAlignment(SwingConstants.CENTER);
        labelNombrePedido.setVerticalAlignment(SwingConstants.CENTER);
        panelNotificaciones.add(labelNombrePedido);

        JLabel labelEstadoPedido = new JLabel("ESTADO");
        labelEstadoPedido.setBounds(28*x,0,10*x, 3*y );
        labelEstadoPedido.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 25));
        labelEstadoPedido.setForeground(Color.BLACK);
        labelEstadoPedido.setHorizontalAlignment(SwingConstants.CENTER);
        labelEstadoPedido.setVerticalAlignment(SwingConstants.CENTER);
        panelNotificaciones.add(labelEstadoPedido);

        for (Requested pedido : pedidos) {
            Product product = controler.getProductById(pedido.getProduct().getId());
            modeloidPedidos.addElement(String.valueOf(pedido.getId()));
            modeloNombreProducto.addElement(product.getName());
            modeloEstado.addElement(pedido.getEstado());
        }




        JList<String> jlIDPedidos = new JList<>(modeloidPedidos);
        jlIDPedidos.setBounds(2,3*x, 2*x-2, 19*y-2);
        jlIDPedidos.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 15));
        jlIDPedidos.setOpaque(false);
        jlIDPedidos.setBackground(new Color(245, 245, 220));
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) jlIDPedidos.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        panelNotificaciones.add(jlIDPedidos);

        JList<String> jlNombrePedidos = new JList<>(modeloNombreProducto);
        jlNombrePedidos.setBounds(2*x,3*x, 26*x, 19*y-2);
        jlNombrePedidos.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 15));
        jlNombrePedidos.setOpaque(false);
        jlNombrePedidos.setBackground(new Color(245, 245, 220));
        panelNotificaciones.add(jlNombrePedidos);

        JList<String> jlEstadoPedidos = new JList<>(modeloEstado);
        jlEstadoPedidos.setBounds(28*x,3*x, 10*x-2, 19*y-2);
        jlEstadoPedidos.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 15));
        jlEstadoPedidos.setOpaque(false);
        jlEstadoPedidos.setBackground(new Color(245, 245, 220));
        panelNotificaciones.add(jlEstadoPedidos);



        getContentPane().add(panelPrincipal);
        setVisible(true);
    }

    @Override
    public void actualizar() {

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println(controler.userId() + " " + controler.userName());
        System.out.println(pedidos.size() + " pedidos");
        for (Requested pedido : pedidos) {
            System.out.println(pedido.toString());
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------");


    }
}
