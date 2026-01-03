package org.kant.infrastructure.persistence;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.kant.domain.model.Reserva;
import org.kant.domain.repository.ReservaRepository;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PanacheReservaRepository implements ReservaRepository, PanacheRepository<Reserva> {

    @Override
    public Uni<List<Reserva>> buscarTodas() {
        return find("from Reserva r left join fetch r.detalles").list();
    }

    @Override
    public Uni<Reserva> buscarPorId(Long id) {
        return find("from Reserva r left join fetch r.detalles where r.id = ?1", id).firstResult();
    }

    @Override
    public Uni<Reserva> guardar(Reserva reserva) {
        return persist(reserva).replaceWith(reserva);
    }

    @Override
    public Uni<Boolean> eliminar(Long id) {
        return Panache.withTransaction(() -> deleteById(id));
    }

    @Override
    public Uni<List<Reserva>> buscarPorProfesionalYFecha(Long profesionalId, LocalDateTime inicio, LocalDateTime fin) {
        return find("SELECT DISTINCT r FROM Reserva r JOIN r.detalles d " +
                        "WHERE d.servicio.profesional.id = ?1 " +
                        "AND r.fechaInicio >= ?2 AND r.fechaFin <= ?3",
                profesionalId, inicio, fin)
                .list();
    }
}