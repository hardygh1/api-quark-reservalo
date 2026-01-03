package org.kant.application.service;

import io.smallrye.mutiny.Uni;
import jakarta.transaction.Transactional;
import org.kant.domain.model.Usuario;
import org.kant.infrastructure.rest.dto.UsuarioRequestDTO;

public interface UsuarioService {

    @Transactional
    Uni<Usuario> crearUsuario(UsuarioRequestDTO dto);
}
