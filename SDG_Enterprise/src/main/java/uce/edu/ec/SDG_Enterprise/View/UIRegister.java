package ec.edu.uce.SDG_Enterprise.View;

import javax.swing.*;
import java.awt.*;

public class UIRegister extends JDialog {

    public UIRegister(Frame parent) {
        super(parent, "Ventana de Registro", true); // true para modal
        setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(screenSize.width / 2 - 250, screenSize.height / 2 - 250, 500, 500);
        setLocationRelativeTo(parent);
        setLayout(null);

        int panelWidth = 500;
        int panelHeight = 500;

        //Tamño del panel
        BackgroundPanel panelPrincipal = new BackgroundPanel("/Recursos/fondo.jpg",0.75f);
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(new Color(169,169,169,75));
        panelPrincipal.setSize(panelWidth, panelHeight);
        panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel tituloPanel = new JLabel("Cliente Nuevo");
        Font fuenteTitulo = new Font("Lemon Jelly", Font.BOLD, 45);
        tituloPanel.setFont(fuenteTitulo);
        tituloPanel.setBounds(100,10,350,50);
        panelPrincipal.add(tituloPanel);

        //Creacion de campos

        //Campo Nombre
        JLabel jlNombre = new JLabel("Nombre");
        Font fontjlNombre = new Font("Roboto", Font.TRUETYPE_FONT, 45); // Tamaño inicial de la fuente
        jlNombre.setBounds(20, 70, 220, 50);
        jlNombre.setFont(fontjlNombre);
        //jlNombre.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jlNombre);

        JTextField jtNombre = new JTextField();
        Font fontjtNombre = new Font("Roboto", Font.TRUETYPE_FONT, 25);
        jtNombre.setBounds(250, 75, 230, 40);
        jtNombre.setFont(fontjtNombre);
        jtNombre.setBackground(new Color(240,240,240));
        jtNombre.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jtNombre);


        //Cammpo Cedula
        JLabel jlCedula = new JLabel("Cedula");
        Font fontjlCedula = new Font("Roboto", Font.TRUETYPE_FONT, 45); // Tamaño inicial de la fuente
        jlCedula.setBounds(20, 140, 220, 50);
        jlCedula.setFont(fontjlCedula);
        //jlCedula.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jlCedula);

        JTextField jtCedula = new JTextField();
        Font fontjtCedula = new Font("Roboto", Font.TRUETYPE_FONT, 25);
        jtCedula.setBounds(250, 145, 230, 40);
        jtCedula.setFont(fontjtCedula);
        jtCedula.setBackground(new Color(240,240,240));
        jtCedula.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jtCedula);

        //Campo Nombre de Usuario
        JLabel jlUsuario = new JLabel("Usuario");
        Font fontjlUsario = new Font("Roboto", Font.TRUETYPE_FONT, 45); // Tamaño inicial de la fuente
        jlUsuario.setBounds(20, 210, 220, 50);
        jlUsuario.setFont(fontjlUsario);
        //jlUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jlUsuario);

        JTextField jtUsuario = new JTextField();
        Font fontjtUsario = new Font("Roboto", Font.TRUETYPE_FONT, 25);
        jtUsuario.setBounds(250, 215, 230, 40);
        jtUsuario.setFont(fontjtUsario);
        jtUsuario.setBackground(new Color(240,240,240));
        jtUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jtUsuario);

        //Campo de Correo
        JLabel jlCorreo = new JLabel("Correo");
        Font fontjlCorreo = new Font("Roboto", Font.TRUETYPE_FONT, 45); // Tamaño inicial de la fuente
        jlCorreo.setBounds(20, 280, 220, 50);
        jlCorreo.setFont(fontjlCorreo);
        //jlCorreo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jlCorreo);

        JTextField jtCorreo = new JTextField();
        Font fontjtCorreo = new Font("Roboto", Font.TRUETYPE_FONT, 25);
        jtCorreo.setBounds(250, 285, 230, 40);
        jtCorreo.setFont(fontjtCorreo);
        jtCorreo.setBackground(new Color(240,240,240));
        jtCorreo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jtCorreo);

        // Campo de Pasword

        JLabel jlPassword = new JLabel("Password");
        Font fontjlPassword = new Font("Roboto", Font.TRUETYPE_FONT, 45); // Tamaño inicial de la fuente
        jlPassword.setBounds(20, 350, 220, 50);
        jlPassword.setFont(fontjlPassword);
        //jlPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jlPassword);

        JPasswordField jpPassword = new JPasswordField();
        Font fontjpPassword = new Font("Roboto", Font.TRUETYPE_FONT, 25);
        jpPassword.setBounds(250, 355, 180, 40);
        jpPassword.setFont(fontjpPassword);
        jpPassword.setBackground(new Color(240,240,240));
        jpPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jpPassword);

        CustomImageButton mostrarContrasena = new CustomImageButton("/Recursos/ojo.jpg", 40, 40);
        mostrarContrasena.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mostrarContrasena.setBounds(440,355,40,40);
        panelPrincipal.add(mostrarContrasena);

        mostrarContrasena.addActionListener(e -> {
            boolean mostrar = jpPassword.getEchoChar() == '•';
            char echoChar = mostrar ? (char) 0 : '•';
            jpPassword.setEchoChar(echoChar);
        });

        // Boton Cancelar
        JButton jbCancelar = new JButton("Cancelar");
        Font fontjbCancelar = new Font("Roboto", Font.TRUETYPE_FONT, 30);
        jbCancelar.setFont(fontjbCancelar);
        jbCancelar.setBounds(20, 420, 220, 50);
        panelPrincipal.add(jbCancelar);

        jbCancelar.addActionListener(e -> {
            dispose();
        });

        // Boton Registar
        JButton jbRegistrarse = new JButton("Registrarse");
        Font fontjbRegistrarse = new Font("Roboto", Font.TRUETYPE_FONT, 30);
        jbRegistrarse.setFont(fontjbRegistrarse);
        jbRegistrarse.setBounds(260, 420, 220, 50);
        panelPrincipal.add(jbRegistrarse);

        jbRegistrarse.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "REGISTRO EXISTOSO", "", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        getContentPane().add(panelPrincipal);
        setVisible(true);
    }
}
