package org.kant.domain.repository;

import io.smallrye.mutiny.Uni;
import org.kant.domain.model.Servicio;
import java.util.List;

public interface ServicioRepository {
    Uni<Servicio> guardar(Servicio servicio);
    Uni<Servicio> buscarPorId(Long id);
    Uni<List<Servicio>> listarPorProfesional(Long profesionalId);
    Uni<List<Servicio>> listarTodos();
}