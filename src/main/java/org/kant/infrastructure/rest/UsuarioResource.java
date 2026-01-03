package org.kant.infrastructure.rest;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.kant.application.service.UsuarioService;
import org.kant.domain.model.Usuario;
import org.kant.infrastructure.rest.dto.UsuarioRequestDTO;

@Path("/api/v1/usuarios")
public class UsuarioResource {
    @Inject
    UsuarioService service;

    @POST
    public Uni<Usuario> crear(UsuarioRequestDTO usuario) {
        return service.crearUsuario(usuario);
    }
}
