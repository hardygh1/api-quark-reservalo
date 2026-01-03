package org.kant.infrastructure.rest;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.kant.application.service.ServicioService;
import org.kant.domain.model.Servicio;
import org.kant.infrastructure.rest.dto.ServicioRequestDTO;

@Path("/api/v1/servicios")
public class ServicioResource {
    @Inject
    ServicioService service;

    @POST
    public Uni<Servicio> crear(ServicioRequestDTO servicio) {
        return service.crearServicio(servicio);
    }
}