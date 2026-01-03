package org.kant.infrastructure.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DetalleRequestDTO {
    public LocalDateTime fechaHoraInicio;
    public LocalDateTime fechaHoraFin;
    public BigDecimal precioFinal;
    public Long servicio_id;
}