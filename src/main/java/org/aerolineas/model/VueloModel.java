package org.aerolineas.model;

import java.time.LocalDateTime;
import java.util.List;
import org.aerolineas.entity.bean.Vuelo;
import org.aerolineas.persistence.VueloRepository;
import org.aerolineas.usecase.VueloUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VueloModel implements VueloUseCase {

    @Autowired
    private VueloRepository repo;

    @Override
    public Vuelo Registrar(Vuelo v) {
        //registrar vuelo
        return repo.save(v);
    }

    @Override
    public Vuelo Actualizar(Vuelo v) {
        //actualizar vuelo
        return repo.save(v);
    }

    @Override
    public void Eliminar(Integer id) {
        //eliminar vuelo
        repo.deleteById(id);
    }

    @Override
    public Vuelo BuscarPorId(Integer id) {
        //buscar vuelo por id
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Vuelo> Listar() {
        //listar vuelos
        return repo.findAll();
    }

    @Override
    public List<Vuelo> BuscarPorRuta(String origen, String destino) {
        //buscar vuelos por ruta
        return repo.buscarPorRuta(origen, destino);
    }

    @Override
    public List<Vuelo> BuscarEntreFechas(LocalDateTime desde, LocalDateTime hasta) {
        //buscar vuelos entre fechas
        return repo.buscarEntreFechas(desde, hasta);
    }
}
