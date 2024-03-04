package com.edureka.bankProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankProjectApplication.class, args);
	}

//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//		return container -> {
//			JettyEmbeddedServletContainerFactory factory =
//					(JettyEmbeddedServletContainerFactory) container;
//			WebAppContext context = new WebAppContext();
//			context.setResourceBase("src/main/resources/WEB-INF/jsp"); // Replace with your JSP directory
//			context.setContextPath("/");
//			factory.addContext(context);
//		};
//	}

}
