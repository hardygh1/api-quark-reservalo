package org.kant.infrastructure.persistence;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.kant.domain.model.Servicio;
import org.kant.domain.repository.ServicioRepository;
import java.util.List;

@ApplicationScoped
public class PanacheServicioRepository implements ServicioRepository, PanacheRepository<Servicio> {

    @Override
    public Uni<Servicio> guardar(Servicio servicio) {
        return persist(servicio).replaceWith(servicio);
    }

    @Override
    public Uni<Servicio> buscarPorId(Long id) {
        return find("from Servicio s left join fetch s.profesional where s.id = ?1", id).firstResult();
    }

    @Override
    public Uni<List<Servicio>> listarPorProfesional(Long profesionalId) {
        return list("profesional.id", profesionalId);
    }

    @Override
    public Uni<List<Servicio>> listarTodos() {
        return find("from Servicio s left join fetch s.profesional").list();
    }
}