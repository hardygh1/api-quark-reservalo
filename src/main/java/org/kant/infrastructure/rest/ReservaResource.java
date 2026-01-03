package org.kant.infrastructure.rest;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.kant.application.service.ReservaService;
import org.kant.domain.model.Reserva;
import org.kant.infrastructure.rest.dto.ReservaRequestDTO;

import java.util.List;

@Path("/api/v1/reservas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservaResource {

    @Inject
    ReservaService service;

    @POST
    public Uni<Response> crear(ReservaRequestDTO dto) {
        return service.crearReserva(dto)
                .map(r -> Response.status(201).entity(r).build());
    }

    @GET
    public Uni<List<Reserva>> listar() {
        return service.listarTodas();
    }

    @GET
    @Path("/{id}")
    public Uni<Reserva> buscar(@PathParam("id") Long id) {
        return service.obtenerPorId(id);
    }

    @PATCH
    @Path("/{id}/confirmar")
    public Uni<Response> confirmar(@PathParam("id") Long id) {
        return service.confirmarReserva(id)
                .map(r -> Response.ok(r).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> eliminar(@PathParam("id") Long id) {
        return service.eliminarReserva(id)
                .map(borrado -> borrado
                        ? Response.noContent().build()
                        : Response.status(Response.Status.NOT_FOUND).build());
    }
}