package org.aerolineas.usecase;

import java.util.List;
import org.aerolineas.entity.bean.Ruta;
import org.springframework.stereotype.Component;

@Component
public interface RutaUseCase {
    Ruta Registrar(Ruta r);
    Ruta Actualizar(Ruta r);
    void Eliminar(Integer id);
    Ruta BuscarPorId(Integer id);
    List<Ruta> Listar();
}
