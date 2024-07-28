package uce.edu.ec.SDG_Enterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import uce.edu.ec.SDG_Enterprise.View.UIPrincipal;
import uce.edu.ec.SDG_Enterprise.View.LoadingScreen;

import javax.swing.*;

@SpringBootApplication
public class SdgEnterpriseApplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// Muestra la interfaz de carga
			LoadingScreen loadingScreen = new LoadingScreen();

			// Ejecuta Spring Boot en un hilo separado
			new Thread(() -> {
				try {
					// Crea el contexto de Spring
					ConfigurableApplicationContext context = new SpringApplicationBuilder(
							SdgEnterpriseApplication.class).headless(false).run(args);

					// Inicializa la UI Principal
					SwingUtilities.invokeLater(() -> {
						// Cierra la pantalla de carga
						loadingScreen.setVisible(false);
						try {
							// Obtiene el bean UIPrincipal
							UIPrincipal uiPrincipal = context.getBean(UIPrincipal.class);
							uiPrincipal.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error al obtener el bean UIPrincipal: " + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}).start();
		});
	}
}
