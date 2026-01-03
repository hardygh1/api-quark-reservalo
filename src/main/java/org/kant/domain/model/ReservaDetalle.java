package org.kant.domain.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserva_detalle")
public class ReservaDetalle extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "fecha_hora_inicio")
    public LocalDateTime fechaHoraInicio;

    @Column(name = "fecha_hora_fin")
    public LocalDateTime fechaHoraFin;

    @Column(name = "precio_final")
    public BigDecimal precioFinal;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    @JsonIgnore
    public Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    public Servicio servicio;
}