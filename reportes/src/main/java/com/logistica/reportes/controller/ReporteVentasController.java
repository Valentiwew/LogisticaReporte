package com.logistica.reportes.controller;

import com.logistica.reportes.model.ReporteVentas;
import com.logistica.reportes.service.ReporteVentasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reportes/ventas")
public class ReporteVentasController {

    private final ReporteVentasService service;

    public ReporteVentasController(ReporteVentasService service) {
        this.service = service;
    }

    @GetMapping
    public List<ReporteVentas> obtenerTodos() {
        return service.obtenerTodos();
    }

    @PostMapping
    public ReporteVentas guardar(@RequestBody ReporteVentas reporte) {
        return service.guardar(reporte);
    }

    @GetMapping("/{id}")
    public Optional<ReporteVentas> buscarPorId(@PathVariable int id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Optional<ReporteVentas> actualizar(@PathVariable int id, @RequestBody ReporteVentas nuevo) {
        return service.actualizar(id, nuevo);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        boolean eliminado = service.eliminar(id);
        return eliminado ? "Reporte eliminado correctamente" : "Reporte no encontrado";
    }
}
