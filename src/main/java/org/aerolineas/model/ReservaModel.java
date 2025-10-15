package org.aerolineas.model;

import java.util.List;
import org.aerolineas.entity.bean.Reserva;
import org.aerolineas.entity.bean.Vuelo;
import org.aerolineas.persistence.ClienteRepository;
import org.aerolineas.persistence.ReservaRepository;
import org.aerolineas.persistence.VueloRepository;
import org.aerolineas.usecase.ReservaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaModel implements ReservaUseCase {

    @Autowired
    private ReservaRepository repo;

    @Autowired
    private VueloRepository vueloRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Override
    public Reserva Crear(Reserva r) {
        
        r.setEstado("PENDIENTE");
        r.setFechaReserva(java.time.LocalDateTime.now());

        
        if (r.getCliente() != null && r.getCliente().getIdCliente() != null) {
            var c = clienteRepo.findById(r.getCliente().getIdCliente()).orElse(null);
            r.setCliente(c);
        }

        
        if (r.getVuelo() != null && r.getVuelo().getIdVuelo() != null) {
            var v = vueloRepo.findById(r.getVuelo().getIdVuelo()).orElse(null);
            r.setVuelo(v);
        }

        return repo.save(r);
    }

    @Override
    public Reserva Confirmar(Integer id) {
        
        Reserva r = repo.findById(id).orElse(null);
        if (r != null) {
            r.setEstado("CONFIRMADA");
            repo.save(r);
        }
        return r;
    }

    @Override
    public Reserva Cancelar(Integer id) {
        
        Reserva r = repo.findById(id).orElse(null);
        if (r != null) {
            r.setEstado("CANCELADA");
            repo.save(r);
        }
        return r;
    }

    @Override
    public Reserva CambiarVuelo(Integer idReserva, Integer idVueloNuevo) {
        
        Reserva r = repo.findById(idReserva).orElse(null);
        Vuelo v = vueloRepo.findById(idVueloNuevo).orElse(null);
        if (r != null && v != null) {
            r.setVuelo(v);
            repo.save(r);
        }
        return r;
    }

    @Override
    public Reserva BuscarPorId(Integer id) {
        
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Reserva> ListarPorCliente(Integer idCliente) {
        
        return repo.listarPorCliente(idCliente);
    }
}
