package uce.edu.ec.SDG_Enterprise.View;


import uce.edu.ec.SDG_Enterprise.Container.Controler;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;

public class VistaCliente extends JFrame {
    Controler controler;
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel rolLabel;
    private JTextField rolTextField;
    private JButton registrarButton;

    public VistaCliente(Controler controler) {
        this.controler = controler;
        setTitle("Vista Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana en pantalla

        // Crear componentes
        titleLabel = new JLabel("Registro de Usuario");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nameLabel = new JLabel("Nombre:");
        nameTextField = new JTextField(20);

        emailLabel = new JLabel("Email:");
        emailTextField = new JTextField(20);

        passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField(20);

        phoneLabel = new JLabel("Teléfono:");
        phoneTextField = new JTextField(20);

        rolLabel = new JLabel("Rol:");
        rolTextField = new JTextField(20);

        registrarButton = new JButton("Registrar");

        // Configurar diseño con BorderLayout
        setLayout(new BorderLayout());

        // Panel para los campos de registro
        JPanel registroPanel = new JPanel();
        registroPanel.setLayout(new GridLayout(6, 2, 10, 10)); // Filas, columnas, espacio horizontal y vertical

        registroPanel.add(nameLabel);
        registroPanel.add(nameTextField);
        registroPanel.add(emailLabel);
        registroPanel.add(emailTextField);
        registroPanel.add(passwordLabel);
        registroPanel.add(passwordField);
        registroPanel.add(phoneLabel);
        registroPanel.add(phoneTextField);
        registroPanel.add(rolLabel);
        registroPanel.add(rolTextField);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registrarButton);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(registroPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        registrarButton.addActionListener(e -> {

            registrarUsuario();

        });


        // Agregar panel principal al JFrame
        add(mainPanel);


        setVisible(true);
    }
    private void registrarUsuario() {
        String nombre = nameTextField.getText();
        String email = emailTextField.getText();
        String password = new String(passwordField.getPassword());
        String telefono = phoneTextField.getText();
        String rol = rolTextField.getText();
        controler.registro(email,nombre,password,telefono,rol,"987654321","jjjj");
    }


}
