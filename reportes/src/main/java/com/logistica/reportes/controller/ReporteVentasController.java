package com.logistica.reportes.controller;

import com.logistica.reportes.model.ReporteVentas;
import com.logistica.reportes.service.ReporteVentasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reportes")
@Tag(name = "ReporteVentas V1", description = "Operaciones CRUD sobre los reportes de ventas")
public class ReporteVentasController {

    private final ReporteVentasService service;

    public ReporteVentasController(ReporteVentasService service) {
        this.service = service;
    }

    @Operation(summary = "Obtener todos los reportes", description = "Retorna una lista con todos los reportes de ventas registrados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public List<ReporteVentas> obtenerTodos() {
        return service.obtenerTodos();
    }

    @Operation(summary = "Crear un nuevo reporte", description = "Agrega un nuevo reporte de ventas a la base de datos")
    @ApiResponse(responseCode = "201", description = "Reporte creado exitosamente")
    @PostMapping
    public ReporteVentas guardar(@RequestBody ReporteVentas reporte) {
        return service.guardar(reporte);
    }

    @Operation(summary = "Buscar reporte por ID", description = "Obtiene un reporte de ventas usando su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reporte encontrado"),
        @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    @GetMapping("/{id}")
    public Optional<ReporteVentas> buscarPorId(@PathVariable int id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Actualizar un reporte existente", description = "Modifica los datos de un reporte de ventas usando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reporte actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    @PutMapping("/{id}")
    public Optional<ReporteVentas> actualizar(@PathVariable int id, @RequestBody ReporteVentas nuevo) {
        return service.actualizar(id, nuevo);
    }

    @Operation(summary = "Eliminar un reporte", description = "Elimina un reporte de ventas según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reporte eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        boolean eliminado = service.eliminar(id);
        return eliminado ? "Reporte eliminado correctamente" : "Reporte no encontrado";
    }
}
