package com.logistica.reportes.controller;

import com.logistica.reportes.service.ProductoVentaClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/external")
@Tag(name = "External API", description = "Endpoints externos para consumir ProductoVenta")
public class ExternalController {

    private final ProductoVentaClient productoVentaClient;

    public ExternalController(ProductoVentaClient productoVentaClient) {
        this.productoVentaClient = productoVentaClient;
    }

    @GetMapping("/producto/{id}")
    @Operation(
            summary = "Obtener producto por ID",
            description = "Obtiene un producto desde el microservicio ProductoVenta dado su ID."
    )
    public Mono<String> getProducto(@PathVariable Integer id) {
        return productoVentaClient.getProductoPorId(id);
    }

    @GetMapping("/productos")
    @Operation(
            summary = "Listar todos los productos",
            description = "Obtiene la lista completa de productos desde el microservicio ProductoVenta."
    )
    public Mono<String> getAllProductos() {
        return productoVentaClient.getAllProductos();
    }
}
