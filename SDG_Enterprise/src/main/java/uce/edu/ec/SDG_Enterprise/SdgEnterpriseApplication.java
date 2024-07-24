package uce.edu.ec.SDG_Enterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import uce.edu.ec.SDG_Enterprise.View.UIPrincipal;

@SpringBootApplication
public class SdgEnterpriseApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = new SpringApplicationBuilder(
		SdgEnterpriseApplication.class).headless(false).run(args);
		UIPrincipal appFrame = context.getBean(UIPrincipal.class);
		appFrame.setVisible(true);

	}

}
