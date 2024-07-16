package ec.edu.uce.SDG_Enterprise.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UIPrincipal extends JFrame {

    public UIPrincipal() {
        super("Ui_Principal");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Lienzo principal donde se agrega cualquier cosa
        JPanel lienzoPrincipal = new JPanel();
        lienzoPrincipal.setLayout(null);
        lienzoPrincipal.setVisible(true);
        lienzoPrincipal.setBackground(new Color(255, 200, 0, 20));

        // Control de bordes
        Border bordePersonalizado = BorderFactory.createLineBorder(Color.BLACK, 2);

        // Panel del logo
        BackgroundPanel panelLogo = new BackgroundPanel("/Logo/logo_sdg.jpg", 1.0f);
        panelLogo.setLayout(null);
        panelLogo.setOpaque(false);
        panelLogo.setBounds(screenSize.width / 2 - 200, 50, 400, 400);
        lienzoPrincipal.add(panelLogo);

        // Ingresar Credenciales
        // Nombre de Usuario
        JLabel jlUser = new JLabel("User");
        Font fontjlUser = new Font("SansSerif", Font.BOLD, 50);
        jlUser.setBounds(screenSize.width / 2 - 250, screenSize.height / 2 + 50, 200, 50);
        jlUser.setFont(fontjlUser);
        lienzoPrincipal.add(jlUser);

        JTextField jtUser = new JTextField();
        Font fontjtUser = new Font("SansSerif", Font.BOLD, 30);
        jtUser.setBounds(screenSize.width / 2 - 100, screenSize.height / 2 + 50, 300, 50);
        jtUser.setFont(fontjtUser);
        jtUser.setBorder(bordePersonalizado);
        lienzoPrincipal.add(jtUser);

        // Contraseña
        JLabel jlPassword = new JLabel("Password");
        Font fontjlPassword = new Font("SansSerif", Font.BOLD, 50);
        jlPassword.setBounds(screenSize.width / 2 - 370, screenSize.height / 2 + 150, 300, 50);
        jlPassword.setFont(fontjlPassword);
        lienzoPrincipal.add(jlPassword);

        JPasswordField jpPassword = new JPasswordField();
        Font fontjpPassword = new Font("SansSerif", Font.BOLD, 30);
        jpPassword.setBounds(screenSize.width / 2 - 100, screenSize.height / 2 + 150, 300, 50);
        jpPassword.setFont(fontjpPassword);
        jpPassword.setBorder(bordePersonalizado);
        lienzoPrincipal.add(jpPassword);

        CustomImageButton mostrarContrasena = new CustomImageButton("/Recursos/ojo.jpg", 40, 38);
        mostrarContrasena.setBorder(bordePersonalizado);
        mostrarContrasena.setBounds(screenSize.width / 2 + 210, screenSize.height / 2 + 156, 40, 38);
        lienzoPrincipal.add(mostrarContrasena);

        mostrarContrasena.addActionListener(e -> {
            boolean mostrar = jpPassword.getEchoChar() == '•';
            char echoChar = mostrar ? (char) 0 : '•';
            jpPassword.setEchoChar(echoChar);
        });

        // Botón De Registrarse
        JButton jbRegistrarse = new JButton("Registrarse");
        Font fontjbRegistrarse = new Font("SansSerif", Font.BOLD, 30);
        jbRegistrarse.setFont(fontjbRegistrarse);
        jbRegistrarse.setBounds(screenSize.width / 2 - 260, screenSize.height / 2 + 250, 250, 50);
        jbRegistrarse.setBorder(bordePersonalizado);
        lienzoPrincipal.add(jbRegistrarse);

        jbRegistrarse.addActionListener(e -> {
            new UIRegister(UIPrincipal.this);
        });

        // Botón De Ingresar
        JButton jbIngresar = new JButton("Ingresar");
        Font fontjbIngresar = new Font("SansSerif", Font.BOLD, 30);
        jbIngresar.setFont(fontjbRegistrarse);
        jbIngresar.setBounds(screenSize.width / 2 + 20, screenSize.height / 2 + 250, 250, 50);
        jbIngresar.setBorder(bordePersonalizado);
        lienzoPrincipal.add(jbIngresar);

        jbIngresar.addActionListener(e -> {
            String usuario = jtUser.getText();
            System.out.println("usuario: " + usuario);
            if (usuario.equals("administrador")) {
                // Lógica para el usuario administrador
            } else {
                new ViewClient();
            }
        });

        getContentPane().add(lienzoPrincipal);
        lienzoPrincipal.revalidate();
        lienzoPrincipal.repaint();
        this.setVisible(true);
    }
}
