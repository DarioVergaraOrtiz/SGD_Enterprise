package uce.edu.ec.SDG_Enterprise.View;

import uce.edu.ec.SDG_Enterprise.Container.Controler;

import javax.swing.*;
import java.awt.*;

public class PedidosUI extends JFrame {

    Controler controler;
    public PedidosUI(Controler controler) {
        setUndecorated(true);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Generalizar valores de pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        int x = screenSize.width / 96;
        int y = screenSize.height / ((screenSize.height * 96) / screenSize.width);
this.controler =controler;
        setBounds(27*x, 5*y, 42*x, 44*y);

        // Lienzo principal donde se agrega cualquier cosa
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(new Color(245, 245, 220));
        panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Panel Encambezado
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setLayout(null);
        panelEncabezado.setBounds(0,0 , 42*x, 6*y);
        panelEncabezado.setBackground(Color.WHITE);
        panelEncabezado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // de quien es el pedido
        JLabel lbUsuario = new JLabel("Usuario: "+ controler.userName());
        lbUsuario.setBounds(x,y, 34*x, 4*y);
        lbUsuario.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 40));
        panelEncabezado.add(lbUsuario);

        // Boton de salir
        CustomImageButton botonSalir = new CustomImageButton("/Recursos/salir 2.jpg", 5*x, 5*y);
        botonSalir.setBounds(36*x, y, 5*x, 4*y);
        botonSalir.setContentAreaFilled(false);
        botonSalir.setBorderPainted(false);
        panelEncabezado.add(botonSalir);

        botonSalir.addActionListener(e -> {
            dispose();;
        });

        panelPrincipal.add(panelEncabezado);


        //Contenido
        JLabel jlProceso = new JLabel("¡GRACIAS POR PREFERIRNOS!");
        jlProceso.setBounds(2*x, 8*y, 38*x, 4*y);
        jlProceso.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 35));
        jlProceso.setVerticalAlignment(SwingConstants.CENTER);
        jlProceso.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(jlProceso);

        //Notificaciones
        JLabel jlNotificacion = new JLabel("LISTA DE PEDIDOS");
        jlNotificacion.setBounds(5*x, 14*y, 32*x, 4*y);
        jlNotificacion.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 40));
        jlNotificacion.setHorizontalAlignment(SwingConstants.CENTER);
        jlNotificacion.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(jlNotificacion);

        // Contenido notificaciones
        JPanel panelNotificaciones = new JPanel();
        panelNotificaciones.setLayout(null);
        panelNotificaciones.setBounds(2*x, 20*y, 38*x, 22*y);
        panelNotificaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(panelNotificaciones);

        JLabel labelTexto = new JLabel(controler.concatProcessToProduct(1,3));
        labelTexto.setBounds(x, y, 36*x, 3*y);
        labelTexto.setHorizontalAlignment(SwingConstants.CENTER);
        panelNotificaciones.add(labelTexto, BorderLayout.CENTER);
        panelNotificaciones.add(labelTexto);

        getContentPane().add(panelPrincipal);
        setVisible(true);

    }

}
