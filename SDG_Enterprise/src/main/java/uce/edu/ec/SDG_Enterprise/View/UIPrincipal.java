package uce.edu.ec.SDG_Enterprise.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uce.edu.ec.SDG_Enterprise.Container.Controler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Component
public class UIPrincipal extends JFrame {
    @Autowired
    Controler controler;

    public UIPrincipal() {
        setTitle("Ui_Principal");
        setUndecorated(true);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Generalizar valores de pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        int x = screenSize.width / 96;
        int y = screenSize.height / ((screenSize.height * 96) / screenSize.width);

        // Lienzo principal donde se agrega cualquier cosa
        JPanel lienzoPrincipal = new JPanel();
        lienzoPrincipal.setLayout(null);
        lienzoPrincipal.setBackground(new Color(255, 200, 0, 20));

        // Panel del logo
        BackgroundLabel labeLogo = new BackgroundLabel("/Logo/logo_sdg.jpg", 1.0f);
        labeLogo.setOpaque(false);
        labeLogo.setBounds(35 * x, 5 * y, 26 * x, 24 * y);
        lienzoPrincipal.add(labeLogo);

        // Ingresar Credenciales
        // Nombre de Usuario
        JLabel jlUser = new JLabel("Usuario");
        jlUser.setBounds(29 * x, 31 * y, 16 * x, 4 * y);
        jlUser.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 50));
        jlUser.setHorizontalAlignment(JLabel.RIGHT);
        lienzoPrincipal.add(jlUser);

        JTextField jtUser = new JTextField();
        jtUser.setBounds(46 * x, 31 * y + (y / 2), 21 * x, 3 * y);
        jtUser.setFont(new Font("Arial", Font.BOLD, 35));
        jtUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jtUser);

        // Contraseña
        JLabel jlPassword = new JLabel("Password");
        jlPassword.setBounds(29 * x, 37 * y, 16 * x, 4 * y);
        jlPassword.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 48));
        jlPassword.setHorizontalAlignment(JLabel.RIGHT);
        lienzoPrincipal.add(jlPassword);

        JPasswordField jpPassword = new JPasswordField();
        jpPassword.setBounds(46 * x, 37 * y + (y / 2), 21 * x, 3 * y);
        Font fontjpPassword = new Font("SansSerif", Font.BOLD, 35);
        jpPassword.setFont(new Font("Arial", Font.BOLD, 30));
        jpPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jpPassword);

        CustomImageButton mostrarContrasena = new CustomImageButton("/Recursos/ojo.jpg", 3 * x, 3 * y);
        mostrarContrasena.setBounds(68 * x, (37 * y) + (y / 2), 3 * x, 3 * y);
        mostrarContrasena.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mostrarContrasena.setOpaque(false);
        lienzoPrincipal.add(mostrarContrasena);

        mostrarContrasena.addActionListener(e -> {
            boolean mostrar = jpPassword.getEchoChar() == '•';
            char echoChar = mostrar ? (char) 0 : '•';
            jpPassword.setEchoChar(echoChar);
        });

        // Botón De Registrarse
        JButton jbRegistrarse = new JButton("Registrarse");
        jbRegistrarse.setBounds(33 * x, 43 * y, 14 * x, 3 * y);
        jbRegistrarse.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 30));
        jbRegistrarse.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jbRegistrarse);

        jbRegistrarse.addActionListener(e -> {
            controler.startViewRegister();
        });

        // Botón De Ingresar
        JButton jbIngresar = new JButton("Ingresar");
        jbIngresar.setBounds(49 * x, 43 * y, 14 * x, 3 * y);
        jbIngresar.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 30));
        jbIngresar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jbIngresar);

        // Definir el ActionListener para ingresar
        ActionListener ingresarListener = e -> {
            String usuario = jtUser.getText();
            String contrasena = new String(jpPassword.getPassword());
            controler.iniciarSegundaVista(usuario, contrasena);
            jpPassword.setText("");
            jtUser.setText("");
        };

        jbIngresar.addActionListener(ingresarListener);

        // Agregar ActionListener a los campos de texto
        jtUser.addActionListener(ingresarListener);
        jpPassword.addActionListener(ingresarListener);

        getContentPane().add(lienzoPrincipal);
        setVisible(true);
    }
}
