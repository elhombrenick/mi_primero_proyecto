package org.aerolineas.usecase;

import java.util.List;
import org.aerolineas.entity.bean.Cliente;
import org.springframework.stereotype.Component;

@Component
public interface ClienteUseCase {
    Cliente Registrar(Cliente c);
    Cliente Actualizar(Cliente c);
    void Eliminar(Integer id);
    Cliente BuscarPorId(Integer id);
    List<Cliente> Listar();
}
