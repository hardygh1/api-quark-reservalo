package org.kant.infrastructure.rest.dto;

import java.util.List;

public class ReservaRequestDTO {
    public String estado;
    public Long cliente_id;
    public List<DetalleRequestDTO> detalles;
}
