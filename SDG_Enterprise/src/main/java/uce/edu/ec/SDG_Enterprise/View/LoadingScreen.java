package uce.edu.ec.SDG_Enterprise.View;


import javax.swing.*;
import java.awt.*;

public class LoadingScreen extends JFrame {

    private JProgressBar progressBar;

    public LoadingScreen() {
        super("Loading...");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(200, 33); // Ajusta el tamaño
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());

        // Estilo del contenedor
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(50, 50, 50)); // Color de fondo oscuro
        add(panel, BorderLayout.CENTER);

        // Configura la barra de progreso
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setForeground(new Color(150, 0, 0)); // Color verde de la barra
        progressBar.setBorderPainted(false); // Quitar borde
        progressBar.setPreferredSize(new Dimension(300, 20)); // Ajusta el tamaño de la barra
        panel.add(progressBar, BorderLayout.CENTER);

        // Etiqueta de texto
        JLabel label = new JLabel("Loading, please wait...", JLabel.CENTER);
        label.setForeground(Color.WHITE); // Color de texto blanco
        label.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente y tamaño
        panel.add(label, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        if (!visible) {
            dispose();
        } else {
            super.setVisible(visible);
        }
    }
}

