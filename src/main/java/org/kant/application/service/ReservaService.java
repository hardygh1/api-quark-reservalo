package org.kant.application.service;

import io.smallrye.mutiny.Uni;
import org.kant.domain.model.Reserva;
import org.kant.infrastructure.rest.dto.ReservaRequestDTO;

import java.util.List;

public interface ReservaService {
    Uni<Reserva> crearReserva(ReservaRequestDTO dto);
    Uni<List<Reserva>> listarTodas();
    Uni<Reserva> obtenerPorId(Long id);
    Uni<Reserva> confirmarReserva(Long id);
    Uni<Boolean> eliminarReserva(Long id);
}