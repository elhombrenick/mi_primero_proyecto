package org.aerolineas.usecase;

import java.util.List;
import org.aerolineas.entity.bean.Avion;
import org.springframework.stereotype.Component;

@Component
public interface AvionUseCase {
    Avion Registrar(Avion a);
    Avion Actualizar(Avion a);
    void Eliminar(Integer id);
    Avion BuscarPorId(Integer id);
    List<Avion> Listar();
}
