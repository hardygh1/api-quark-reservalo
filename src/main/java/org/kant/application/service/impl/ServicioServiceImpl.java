package org.kant.application.service.impl;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.kant.application.service.ServicioService;
import org.kant.domain.model.Servicio;
import org.kant.domain.model.Usuario;
import org.kant.domain.repository.ServicioRepository;
import org.kant.infrastructure.rest.dto.ServicioRequestDTO;
import org.kant.infrastructure.rest.mapper.ServicioMapper;

@ApplicationScoped
public class ServicioServiceImpl implements ServicioService {

    @Inject
    ServicioMapper mapper;

    @Inject
    ServicioRepository repository;

    @Override
    public Uni<Servicio> crearServicio(ServicioRequestDTO dto) {
        return Panache.withTransaction(() -> {
            Servicio servicio = mapper.toEntity(dto);
            return Usuario.<Usuario>findById(servicio.profesional.id)
                    .onItem().ifNull().failWith(() -> new NotFoundException("Profesional no encontrado"))
                    .onItem().invoke(proDb -> servicio.profesional = proDb)
                    .replaceWith(servicio)
                    .onItem().transformToUni(s -> repository.guardar(s));
        });
    }
}
