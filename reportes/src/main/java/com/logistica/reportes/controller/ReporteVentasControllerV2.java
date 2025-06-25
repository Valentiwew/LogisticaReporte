package com.logistica.reportes.controller;

import com.logistica.reportes.assembler.ReporteVentasModelAssembler;
import com.logistica.reportes.model.ReporteVentas;
import com.logistica.reportes.service.ReporteVentasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/reportesVentas")
@Tag(name = "ReporteVentas V2", description = "Operaciones con reportes de ventas usando HATEOAS")
public class ReporteVentasControllerV2 {

    @Autowired
    private ReporteVentasService service;

    @Autowired
    private ReporteVentasModelAssembler assembler;

    @Operation(summary = "Obtener todos los reportes de ventas", description = "Devuelve todos los reportes con enlaces HATEOAS")
    @ApiResponse(responseCode = "200", description = "Reportes encontrados")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<ReporteVentas>> getAllReportes() {
        List<EntityModel<ReporteVentas>> reportes = service.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(reportes,
                linkTo(methodOn(ReporteVentasControllerV2.class).getAllReportes()).withSelfRel());
    }

    @Operation(summary = "Obtener un reporte de ventas por ID", description = "Busca un reporte por su ID con enlaces HATEOAS")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reporte encontrado"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<ReporteVentas> getReporteById(@PathVariable int id) {
        ReporteVentas reporte = service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con ID: " + id));
        return assembler.toModel(reporte);
    }

    @Operation(summary = "Crear un nuevo reporte de ventas", description = "Agrega un nuevo reporte a la base de datos")
    @ApiResponse(responseCode = "201", description = "Reporte creado correctamente")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ReporteVentas>> createReporte(@RequestBody ReporteVentas reporte) {
        ReporteVentas nuevoReporte = service.guardar(reporte);
        return ResponseEntity
                .created(linkTo(methodOn(ReporteVentasControllerV2.class).getReporteById(nuevoReporte.getId())).toUri())
                .body(assembler.toModel(nuevoReporte));
    }

    @Operation(summary = "Actualizar un reporte de ventas", description = "Modifica un reporte existente por su ID")
    @ApiResponse(responseCode = "200", description = "Reporte actualizado correctamente")
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ReporteVentas>> updateReporte(@PathVariable int id, @RequestBody ReporteVentas nuevo) {
        ReporteVentas actualizado = service.actualizar(id, nuevo)
                .orElseThrow(() -> new RuntimeException("No se pudo actualizar el reporte con ID: " + id));
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @Operation(summary = "Eliminar un reporte de ventas", description = "Elimina un reporte por su ID")
    @ApiResponse(responseCode = "204", description = "Reporte eliminado correctamente")
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteReporte(@PathVariable int id) {
        boolean eliminado = service.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
