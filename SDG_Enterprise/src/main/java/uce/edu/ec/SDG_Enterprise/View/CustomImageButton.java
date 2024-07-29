package uce.edu.ec.SDG_Enterprise.View;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CustomImageButton extends JButton {

    public CustomImageButton(String imagePath, int width, int height) {

        ImageIcon icon = createImageIcon(imagePath);

        if (icon != null) {
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(width - 5, height - 5, Image.SCALE_AREA_AVERAGING);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);
            setIcon(scaledIcon);
        } else {
            setText("Image not found");
        }

    }

    // Método para crear un ImageIcon desde un archivo de imagen en cualquier formato
    private ImageIcon createImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("No se puede encontrar el archivo de imagen: " + path);
            return null;
        }
    }


}