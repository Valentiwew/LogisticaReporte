package com.logistica.reportes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* Esta clase se marca como clase de configuracion */
@Configuration
public class SwaggerConfig {

    /* Esto permite crea una configuracion personalizada para la documentacion */
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                    .info(new Info()
                        .title("API Logistica Reportes") /* Titulo que aparecera en la interfaz de Swagger */
                        .version("1.0") /* Version de nuestra API */
                        .description("Documentacion de la API Logistica Reportes")); /* Descripcion visible en la UI */
    }
}
