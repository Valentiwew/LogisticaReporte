package com.logistica.reportes.assembler;

import com.logistica.reportes.controller.ReporteVentasControllerV2;
import com.logistica.reportes.model.ReporteVentas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/* Esto hace que la clase este lista para usarse sin tener que crearla manualmente */
@Component
public class ReporteVentasModelAssembler implements RepresentationModelAssembler<ReporteVentas, EntityModel<ReporteVentas>> {

    @Override /* Convierte ReporteVentas en un modelo con enlaces (EntityModel) */
    @NonNull /* Este parámetro y retorno NO pueden ser nulos para evitar errores */
    public EntityModel<ReporteVentas> toModel(@NonNull ReporteVentas reporte) {
        /* Crea un modelo con ReporteVentas y sus enlaces HATEOAS */
        return EntityModel.of(reporte,
                /* Enlace a este mismo ReporteVentas (self = link al recurso actual) */
                linkTo(methodOn(ReporteVentasControllerV2.class).getReporteById(reporte.getId())).withSelfRel(),
                /* Enlace para ir a la lista completa de ReporteVentas */
                linkTo(methodOn(ReporteVentasControllerV2.class).getAllReportes()).withRel("reportes"));
    }
}
