package org.aerolineas.usecase;

import java.time.LocalDateTime;
import java.util.List;
import org.aerolineas.entity.bean.Vuelo;
import org.springframework.stereotype.Component;

@Component
public interface VueloUseCase {
    Vuelo Registrar(Vuelo v);
    Vuelo Actualizar(Vuelo v);
    void Eliminar(Integer id);
    Vuelo BuscarPorId(Integer id);
    List<Vuelo> Listar();

   
    List<Vuelo> BuscarPorRuta(String origen, String destino);
    List<Vuelo> BuscarEntreFechas(LocalDateTime desde, LocalDateTime hasta);
}
