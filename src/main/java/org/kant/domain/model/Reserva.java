package org.kant.domain.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "reserva")
public class Reserva extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 20)
    public String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    public Usuario cliente;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    public List<ReservaDetalle> detalles;
}
