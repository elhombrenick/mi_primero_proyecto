package org.aerolineas.model;

import java.util.List;
import org.aerolineas.entity.bean.Cliente;
import org.aerolineas.persistence.ClienteRepository;
import org.aerolineas.usecase.ClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteModel implements ClienteUseCase {

    @Autowired
    private ClienteRepository repo;

    @Override
    public Cliente Registrar(Cliente c) {
        //registrar cliente
        return repo.save(c);
    }

    @Override
    public Cliente Actualizar(Cliente c) {
        //actualizar cliente
        return repo.save(c);
    }

    @Override
    public void Eliminar(Integer id) {
        //eliminar cliente
        repo.deleteById(id);
    }

    @Override
    public Cliente BuscarPorId(Integer id) {
        //buscar cliente por id
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> Listar() {
        //listar clientes
        return repo.findAll();
    }
}
