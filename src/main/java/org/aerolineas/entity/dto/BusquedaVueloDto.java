package org.aerolineas.entity.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BusquedaVueloDto {
    private String origen;
    private String destino;
    private LocalDateTime desde;  //opcional
    private LocalDateTime hasta;  //opcional
}
