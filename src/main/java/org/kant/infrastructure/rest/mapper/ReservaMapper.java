package org.kant.infrastructure.rest.mapper;

import org.kant.domain.model.Reserva;
import org.kant.domain.model.ReservaDetalle;
import org.kant.infrastructure.rest.dto.ReservaRequestDTO;
import org.kant.infrastructure.rest.dto.DetalleRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface ReservaMapper {

    @Mapping(target = "cliente.id", source = "cliente_id")
    @Mapping(target = "id", ignore = true)
    Reserva toEntity(ReservaRequestDTO dto);

    @Mapping(target = "servicio.id", source = "servicio_id")
    @Mapping(target = "id", ignore = true)
    ReservaDetalle toEntity(DetalleRequestDTO dto);
}