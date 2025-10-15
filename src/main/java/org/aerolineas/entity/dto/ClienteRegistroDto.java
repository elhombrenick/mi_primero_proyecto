package org.aerolineas.entity.dto;

import lombok.Data;

@Data
public class ClienteRegistroDto {
    private String nombre;
    private String direccion;
    private String email; //opcional
}
