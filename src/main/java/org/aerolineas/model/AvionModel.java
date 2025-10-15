package org.aerolineas.model;

import java.util.List;
import org.aerolineas.entity.bean.Avion;
import org.aerolineas.persistence.AvionRepository;
import org.aerolineas.usecase.AvionUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvionModel implements AvionUseCase {

    @Autowired
    private AvionRepository repo;

    @Override
    public Avion Registrar(Avion a) {
        //registrar avion
        return repo.save(a);
    }

    @Override
    public Avion Actualizar(Avion a) {
        //actualizar avion
        return repo.save(a);
    }

    @Override
    public void Eliminar(Integer id) {
        //eliminar avion
        repo.deleteById(id);
    }

    @Override
    public Avion BuscarPorId(Integer id) {
        //buscar avion por id
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Avion> Listar() {
        //listar aviones
        return repo.findAll();
    }
}
