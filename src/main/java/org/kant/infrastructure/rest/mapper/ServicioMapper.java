package org.kant.infrastructure.rest.mapper;

import org.kant.domain.model.Servicio;
import org.kant.infrastructure.rest.dto.ServicioRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface ServicioMapper {
    @Mapping(target = "profesional.id", source = "profesional_id")
    @Mapping(target = "id", ignore = true)
    Servicio toEntity(ServicioRequestDTO dto);
}