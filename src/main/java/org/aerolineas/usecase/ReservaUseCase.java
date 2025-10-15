package org.aerolineas.usecase;

import java.util.List;
import org.aerolineas.entity.bean.Reserva;
import org.springframework.stereotype.Component;

@Component
public interface ReservaUseCase {
    Reserva Crear(Reserva r);
    Reserva Confirmar(Integer id);
    Reserva Cancelar(Integer id);
    Reserva CambiarVuelo(Integer idReserva, Integer idVueloNuevo);
    Reserva BuscarPorId(Integer id);
    List<Reserva> ListarPorCliente(Integer idCliente);
}
