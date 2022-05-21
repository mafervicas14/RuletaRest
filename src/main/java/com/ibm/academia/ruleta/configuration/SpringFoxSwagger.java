package com.ibm.academia.ruleta.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class SpringFoxSwagger {
	
	@Bean
    public OpenAPI getDocket()
    {
        return new OpenAPI()
                .info(new Info().title("Ruleta API")
                .description("Este es el desarrollo del primer entregable dentro de la academia de microservicios de Java. "
                		+ "Aquí podremos encontrar cómo por medio de métodos de petición podemos ir simulando una ruleta de Casino")
                .version("v0.0.1")
                .license(new License().name("Mafervicas").url("https://www.tiktok.com/@mafervicas")));
    }


}
