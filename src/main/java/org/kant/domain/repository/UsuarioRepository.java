package org.kant.domain.repository;

import io.smallrye.mutiny.Uni;
import org.kant.domain.model.Usuario;
import java.util.List;

public interface UsuarioRepository {
    Uni<Usuario> guardar(Usuario usuario);
    Uni<Usuario> buscarPorId(Long id);
    Uni<List<Usuario>> listarTodos();
    Uni<Usuario> buscarPorEmail(String email);
    Uni<Boolean> eliminar(Long id);
}