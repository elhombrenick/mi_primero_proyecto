package org.aerolineas.model;

import java.util.List;
import org.aerolineas.entity.bean.Venta;
import org.aerolineas.persistence.ClienteRepository;
import org.aerolineas.persistence.VentaRepository;
import org.aerolineas.persistence.VueloRepository;
import org.aerolineas.usecase.VentaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaModel implements VentaUseCase {

    @Autowired
    private VentaRepository repo;

    @Autowired
    private VueloRepository vueloRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Override
    public Venta Generar(Venta v) {
        
        v.setFechaVenta(java.time.LocalDateTime.now());

      
        if (v.getCliente() != null && v.getCliente().getIdCliente() != null) {
            var c = clienteRepo.findById(v.getCliente().getIdCliente()).orElse(null);
            v.setCliente(c);
        }

        
        if (v.getVuelo() != null && v.getVuelo().getIdVuelo() != null) {
            var vue = vueloRepo.findById(v.getVuelo().getIdVuelo()).orElse(null);
            v.setVuelo(vue);
            if (v.getTotal() == null && vue != null) {
                v.setTotal(vue.getPrecio());
            }
        }

        return repo.save(v);
    }

    @Override
    public Venta Anular(Integer id) {
      
        Venta v = repo.findById(id).orElse(null);
        if (v != null) {
            repo.deleteById(id);
        }
        return v;
    }

    @Override
    public Venta BuscarPorId(Integer id) {
        
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Venta> ListarPorCliente(Integer idCliente) {
        
        return repo.listarPorCliente(idCliente);
    }
}
