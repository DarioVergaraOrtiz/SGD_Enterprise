package uce.edu.ec.SDG_Enterprise.View;

import uce.edu.ec.SDG_Enterprise.Container.Controler;

import javax.swing.*;
import java.awt.*;

public class UIRegister extends JDialog {
    Controler controler;

    public UIRegister(Controler controler) {
        setUndecorated(true);
        setResizable(false);

        //Generalizar valores de pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        int x = screenSize.width / 96;
        int y = screenSize.height / ((screenSize.height * 96) / screenSize.width);

        setBounds(32 * x, 6 * y, 32 * x, 35 * y);

        this.controler = controler;

        //Tamño del panel
        BackgroundPanel panelPrincipal = new BackgroundPanel("/Recursos/fondo.jpg", 0.75f);
        panelPrincipal.setLayout(null);
        panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Titulo del panel
        JLabel tituloPanel = new JLabel("Cliente Nuevo");
        tituloPanel.setBounds(0, 0, 32 * x, 4 * y);
        tituloPanel.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 60));
        tituloPanel.setHorizontalAlignment(SwingConstants.CENTER);
        tituloPanel.setVerticalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(tituloPanel);

        //Creacion de campos
        //Campo Nombre
        JLabel jlNombre = new JLabel("Nombre");
        jlNombre.setBounds(x, 6 * y, 14 * x, 3 * y);
        jlNombre.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 45));
        jlNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        panelPrincipal.add(jlNombre);

        JTextField jtNombre = new JTextField();
        jtNombre.setBounds(17 * x, 6 * y, 14 * x, 3 * y);
        jtNombre.setFont(new Font("Arial", Font.BOLD, 30));
        jtNombre.setBackground(new Color(240, 240, 240));
        jtNombre.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jtNombre);


        //Cammpo Cedula
        JLabel jlCedula = new JLabel("Cedula");
        jlCedula.setBounds(x, 10 * y, 14 * x, 3 * y);
        jlCedula.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 45));
        jlCedula.setHorizontalAlignment(SwingConstants.RIGHT);
        panelPrincipal.add(jlCedula);

        JTextField jtCedula = new JTextField();
        jtCedula.setBounds(17 * x, 10 * y, 14 * x, 3 * y);
        jtCedula.setFont(new Font("Arial", Font.BOLD, 30));
        jtCedula.setBackground(new Color(240, 240, 240));
        jtCedula.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jtCedula);


        //Campo Nombre de Usuario
        JLabel jlUsuario = new JLabel("Usuario");
        jlUsuario.setBounds(x, 14 * y, 14 * x, 3 * y);
        jlUsuario.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 45));
        jlUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        panelPrincipal.add(jlUsuario);

        JTextField jtUsuario = new JTextField();
        jtUsuario.setBounds(17 * x, 14 * y, 14 * x, 3 * y);
        jtUsuario.setFont(new Font("Arial", Font.BOLD, 30));
        jtUsuario.setBackground(new Color(240, 240, 240));
        jtUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jtUsuario);


        //Campo de Correo
        JLabel jlCorreo = new JLabel("Correo");
        jlCorreo.setBounds(x, 18 * y, 14 * x, 3 * y);
        jlCorreo.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 45));
        jlCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
        panelPrincipal.add(jlCorreo);

        JTextField jtCorreo = new JTextField();
        jtCorreo.setBounds(17 * x, 18 * y, 14 * x, 3 * y);
        jtCorreo.setFont(new Font("Arial", Font.BOLD, 30));
        jtCorreo.setBackground(new Color(240, 240, 240));
        jtCorreo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jtCorreo);

        // capo telefono
        JLabel jlTelefono = new JLabel("Telefono");
        jlTelefono.setBounds(x, 22 * y, 14 * x, 3 * y);
        jlTelefono.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 45));
        jlTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
        panelPrincipal.add(jlTelefono);

        JTextField jtTelefono = new JTextField();
        jtTelefono.setBounds(17 * x, 22 * y, 14 * x, 3 * y);
        jtTelefono.setFont(new Font("Arial", Font.BOLD, 30));
        jtTelefono.setBackground(new Color(240, 240, 240));
        jtTelefono.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jtTelefono);


        // Campo de Pasword
        JLabel jlPassword = new JLabel("Password");
        jlPassword.setBounds(x, 26 * y, 14 * x, 3 * y);
        jlPassword.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 40));
        jlPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        panelPrincipal.add(jlPassword);

        JPasswordField jpPassword = new JPasswordField();
        jpPassword.setBounds(17 * x, 26 * y, 11 * x, 3 * y);
        jpPassword.setFont(new Font("Arial", Font.BOLD, 30));
        jpPassword.setBackground(new Color(240, 240, 240));
        jpPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(jpPassword);

        CustomImageButton mostrarContrasena = new CustomImageButton("/Recursos/ojo.jpg", 40, 40);
        mostrarContrasena.setBounds(29 * x, 26 * y + (y / 2), 2 * x, 2 * y);
        mostrarContrasena.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelPrincipal.add(mostrarContrasena);

        mostrarContrasena.addActionListener(e -> {
            boolean mostrar = jpPassword.getEchoChar() == '•';
            char echoChar = mostrar ? (char) 0 : '•';
            jpPassword.setEchoChar(echoChar);
        });

        // Boton Cancelar
        JButton jbCancelar = new JButton("Cancelar");
        jbCancelar.setBounds(x, 30 * y, 14 * x, 3 * y);
        jbCancelar.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 30));
        panelPrincipal.add(jbCancelar);

        jbCancelar.addActionListener(e -> {
            dispose();
        });

        // Boton Registar
        JButton jbRegistrarse = new JButton("Registrarse");
        jbRegistrarse.setBounds(17 * x, 30 * y, 14 * x, 3 * y);
        jbRegistrarse.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 30));
        panelPrincipal.add(jbRegistrarse);

        jbRegistrarse.addActionListener(e -> {

            if (jtCedula.getText().isEmpty() || jtNombre.getText().isEmpty() || jtUsuario.getText().isEmpty() || jtCorreo.getText().isEmpty() || new String(jpPassword.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String nombre = jtNombre.getText();
                String cedula = jtCedula.getText();
                String usuario = jtUsuario.getText();
                String correo = jtCorreo.getText();
                String password = new String(jpPassword.getPassword());
                String phone = jtTelefono.getText();
                controler.registerClient(cedula, nombre, usuario, password, "Cliente", correo, phone);
                dispose();
            }
        });

        getContentPane().add(panelPrincipal);
        setVisible(true);
    }
}
