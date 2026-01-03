package org.kant.domain.repository;

import io.smallrye.mutiny.Uni;
import org.kant.domain.model.Reserva;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository {

    Uni<Reserva> guardar(Reserva reserva);

    Uni<List<Reserva>> buscarTodas();

    Uni<Reserva> buscarPorId(Long id);

    Uni<Boolean> eliminar(Long id);

    Uni<List<Reserva>> buscarPorProfesionalYFecha(Long profesionalId, LocalDateTime inicio, LocalDateTime fin);
}
