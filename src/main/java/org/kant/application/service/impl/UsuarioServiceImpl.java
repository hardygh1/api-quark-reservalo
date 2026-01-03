package org.kant.application.service.impl;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.kant.application.service.UsuarioService;
import org.kant.domain.model.Rol;
import org.kant.domain.model.Usuario;
import org.kant.domain.repository.UsuarioRepository;
import org.kant.infrastructure.rest.dto.UsuarioRequestDTO;
import org.kant.infrastructure.rest.mapper.UsuarioMapper;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioMapper mapper;

    @Inject
    UsuarioRepository repository;

    @Transactional
    @Override
    public Uni<Usuario> crearUsuario(UsuarioRequestDTO dto) {
        return Panache.withTransaction(() -> {
            Usuario usuario = mapper.toEntity(dto);
            return Rol.<Rol>findById(usuario.rol.id)
                    .onItem().ifNull().failWith(() -> new NotFoundException("Rol no encontrado"))
                    .onItem().invoke(rolDb -> usuario.rol = rolDb)
                    .replaceWith(usuario)
                    .onItem().transformToUni(u -> repository.guardar(u));
        });
    }
}
