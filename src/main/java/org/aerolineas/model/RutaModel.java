package org.aerolineas.model;

import java.util.List;
import org.aerolineas.entity.bean.Ruta;
import org.aerolineas.persistence.RutaRepository;
import org.aerolineas.usecase.RutaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutaModel implements RutaUseCase {

    @Autowired
    private RutaRepository repo;

    @Override
    public Ruta Registrar(Ruta r) {
        //registrar ruta
        return repo.save(r);
    }

    @Override
    public Ruta Actualizar(Ruta r) {
        //actualizar ruta
        return repo.save(r);
    }

    @Override
    public void Eliminar(Integer id) {
        //eliminar ruta
        repo.deleteById(id);
    }

    @Override
    public Ruta BuscarPorId(Integer id) {
        //buscar ruta por id
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Ruta> Listar() {
        //listar rutas
        return repo.findAll();
    }
}
