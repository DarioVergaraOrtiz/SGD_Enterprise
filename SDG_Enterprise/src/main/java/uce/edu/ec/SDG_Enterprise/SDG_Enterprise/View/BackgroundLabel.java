package uce.edu.ec.SDG_Enterprise.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BackgroundLabel extends JLabel {
    private BufferedImage backgroundImage;
    private float opacity;

    public BackgroundLabel(String imagePath, float opacity) {
        this.opacity = opacity;
        try {
            // Cargar la imagen desde el classpath
            backgroundImage = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar la imagen de fondo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            // Establece la opacidad
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            // Dibuja la imagen ajustada al tamaño del componente
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}
