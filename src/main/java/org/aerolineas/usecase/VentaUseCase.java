package org.aerolineas.usecase;

import java.util.List;
import org.aerolineas.entity.bean.Venta;
import org.springframework.stereotype.Component;

@Component
public interface VentaUseCase {
    Venta Generar(Venta v);
    Venta Anular(Integer id);
    Venta BuscarPorId(Integer id);
    List<Venta> ListarPorCliente(Integer idCliente);
}
