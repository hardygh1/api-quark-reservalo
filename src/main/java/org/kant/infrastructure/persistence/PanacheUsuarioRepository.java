package org.kant.infrastructure.persistence;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.kant.domain.model.Usuario;
import org.kant.domain.repository.UsuarioRepository;
import java.util.List;

@ApplicationScoped
public class PanacheUsuarioRepository implements UsuarioRepository, PanacheRepository<Usuario> {

    @Override
    public Uni<Usuario> guardar(Usuario usuario) {
        return persist(usuario).replaceWith(usuario);
    }

    @Override
    public Uni<Usuario> buscarPorId(Long id) {
        return find("from Usuario u left join fetch u.rol where u.id = ?1", id).firstResult();
    }

    @Override
    public Uni<List<Usuario>> listarTodos() {
        return listAll();
    }

    @Override
    public Uni<Usuario> buscarPorEmail(String email) {
        return find("email", email).firstResult();
    }

    @Override
    public Uni<Boolean> eliminar(Long id) {
        return deleteById(id);
    }
}