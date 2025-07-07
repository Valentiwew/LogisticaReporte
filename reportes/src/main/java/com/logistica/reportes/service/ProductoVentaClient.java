package com.logistica.reportes.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service // Marca esta clase como un componente de servicio en Spring para inyección de dependencias.
public class ProductoVentaClient {

    private final WebClient webClient; // Cliente HTTP reactivo para llamar al microservicio ProductoVenta.

    // Constructor que inyecta el WebClient específico configurado para ProductoVenta.
    // Usa @Qualifier para especificar el bean productoVentaWebClient definido en WebClientConfig.
    public ProductoVentaClient(@Qualifier("productoVentaWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    // Método para obtener un producto por su ID desde el microservicio ProductoVenta.
    public Mono<String> getProductoPorId(Integer id) {
        return webClient.get() // Realiza un GET HTTP
                .uri("/{id}", id) // A la ruta /{id}, reemplazando {id} con el valor recibido
                .retrieve() // Ejecuta la petición y espera la respuesta
                .bodyToMono(String.class); // Convierte la respuesta en un Mono<String> (programación reactiva)
    }

    // Método para obtener la lista completa de productos desde ProductoVenta.
    public Mono<String> getAllProductos() {
        return webClient.get() // Realiza un GET HTTP
                .retrieve() // Ejecuta la petición y espera la respuesta
                .bodyToMono(String.class); // Convierte la respuesta en un Mono<String>
    }
}
