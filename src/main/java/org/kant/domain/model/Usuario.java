package org.kant.domain.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 50, nullable = false)
    public String username;

    @Column(length = 100, nullable = false)
    public String email;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    public Rol rol;
}