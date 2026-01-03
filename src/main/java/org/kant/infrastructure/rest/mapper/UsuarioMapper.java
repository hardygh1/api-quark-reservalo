package org.kant.infrastructure.rest.mapper;

import org.kant.domain.model.Usuario;
import org.kant.infrastructure.rest.dto.UsuarioRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface UsuarioMapper {
    @Mapping(target = "rol.id", source = "rol_id")
    @Mapping(target = "id", ignore = true)
    Usuario toEntity(UsuarioRequestDTO dto);
}