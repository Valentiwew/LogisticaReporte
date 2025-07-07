package com.logistica.reportes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration // Indica que esta clase es una clase de configuración de Spring y define beans.
public class WebClientConfig {

    @Value("${microservicio.producto_venta.url}") // Inyecta la URL base de ProductoVenta desde application.properties.
    private String productoVentaUrl;

    @Value("${microservicio.gestion_usuarios.url}") // Inyecta la URL base de GestionUsuarios desde application.properties.
    private String gestionUsuariosUrl;

    // Declara un bean de WebClient para conectarse a ProductoVenta.
    @Bean
    public WebClient productoVentaWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(productoVentaUrl) // Configura la URL base con la propiedad inyectada.
                .build(); // Construye la instancia de WebClient.
    }

    // Declara un bean de WebClient para conectarse a GestionUsuarios.
    @Bean
    public WebClient gestionUsuariosWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(gestionUsuariosUrl) // Configura la URL base con la propiedad inyectada.
                .build(); // Construye la instancia de WebClient.
    }
}
