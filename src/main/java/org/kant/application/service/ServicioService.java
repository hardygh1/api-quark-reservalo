package org.kant.application.service;

import io.smallrye.mutiny.Uni;
import org.kant.domain.model.Servicio;
import org.kant.infrastructure.rest.dto.ServicioRequestDTO;

public interface ServicioService {
    Uni<Servicio> crearServicio(ServicioRequestDTO servicio);
}
