package org.aerolineas.entity.dto;

import lombok.Data;

@Data
public class VentaRequestDto {
    private Integer idCliente;
    private Integer idVuelo;
    private Double total; //opcional, si es null se toma precio del vuelo
}
