package com.logistica.reportes.service;

import com.logistica.reportes.model.ReporteVentas;
import com.logistica.reportes.repository.ReporteVentasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteVentasService {

    private final ReporteVentasRepository repository;

    public ReporteVentasService(ReporteVentasRepository repository) {
        this.repository = repository;
    }

    public List<ReporteVentas> obtenerTodos() {
        return repository.findAll();
    }

    public ReporteVentas guardar(ReporteVentas reporte) {
        return repository.save(reporte);
    }

    public Optional<ReporteVentas> buscarPorId(int id) {
        return repository.findById(id);
    }

    public Optional<ReporteVentas> actualizar(int id, ReporteVentas nuevo) {
        return repository.findById(id).map(reporte -> {
            reporte.setFechaInicio(nuevo.getFechaInicio());
            reporte.setFechaFin(nuevo.getFechaFin());
            reporte.setTotalVentas(nuevo.getTotalVentas());
            return repository.save(reporte);
        });
    }

    public boolean eliminar(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
