package org.kant.domain.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "servicio")
public class Servicio extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 100)
    public String tipo;

    @Column(name = "precio_base")
    public BigDecimal precioBase;

    @ManyToOne
    @JoinColumn(name = "profesional_id")
    public Usuario profesional;
}
