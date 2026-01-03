package org.kant.application.service.impl;

import io.smallrye.mutiny.Multi;
import org.kant.domain.model.Reserva;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.kant.application.service.ReservaService;
import org.kant.domain.model.Servicio;
import org.kant.domain.model.Usuario;
import org.kant.domain.repository.ReservaRepository;
import org.kant.infrastructure.rest.dto.ReservaRequestDTO;
import org.kant.infrastructure.rest.mapper.ReservaMapper;
import java.util.List;

@ApplicationScoped
public class ReservaServiceImpl implements ReservaService {

    @Inject
    ReservaRepository repository;

    @Inject
    ReservaMapper mapper;

    @Override
    public Uni<Reserva> crearReserva(ReservaRequestDTO dto) {
        return Panache.withTransaction(() -> {
            Reserva nuevaReserva = mapper.toEntity(dto);

            return validarCliente(nuevaReserva)
                    .onItem().transformToUni(cliente -> validarServicios(nuevaReserva))
                    .onItem().transformToUni(v -> repository.guardar(nuevaReserva));
        });
    }

    private Uni<Usuario> validarCliente(Reserva reserva) {
        return Usuario.<Usuario>findById(reserva.cliente.id)
                .onItem().ifNull().failWith(() -> new NotFoundException("Cliente no encontrado"))
                .onItem().invoke(clienteDb -> reserva.cliente = clienteDb);
    }

    private Uni<Void> validarServicios(Reserva reserva) {
        return Multi.createFrom().iterable(reserva.detalles)
                .onItem().transformToUniAndConcatenate(detalle -> {
                    detalle.reserva = reserva;
                    return Servicio.<Servicio>findById(detalle.servicio.id)
                            .onItem().ifNull().failWith(() ->
                                    new NotFoundException("Servicio ID " + detalle.servicio.id + " no existe"))
                            .onItem().invoke(servDb -> detalle.servicio = servDb)
                            .replaceWithVoid();
                })
                .collect().asList()
                .replaceWithVoid();
    }

    @Override
    public Uni<List<Reserva>> listarTodas() {
        return repository.buscarTodas()
                .onItem().transform(list -> {
                    return list;
                });
    }

    @Override
    public Uni<Reserva> obtenerPorId(Long id) {
        return repository.buscarPorId(id)
                .onItem().ifNull().failWith(() -> new NotFoundException("Reserva con ID " + id + " no encontrada"));
    }

    @Override
    public Uni<Reserva> confirmarReserva(Long id) {
        return Panache.withTransaction(() ->
                repository.buscarPorId(id)
                        .onItem().ifNull().failWith(() -> new NotFoundException("No se puede confirmar una reserva inexistente"))
                        .onItem().invoke(r -> r.estado = "CONFIRMADA")
        );
    }

    @Override
    public Uni<Boolean> eliminarReserva(Long id) {
        return repository.eliminar(id);
    }
}