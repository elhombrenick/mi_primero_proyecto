package org.aerolineas.persistence;

import java.time.LocalDateTime;
import java.util.List;
import org.aerolineas.entity.bean.Vuelo;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Integer> {

    
    @Query("SELECT v FROM Vuelo v WHERE LOWER(v.ruta.origen)=LOWER(:origen) AND LOWER(v.ruta.destino)=LOWER(:destino)")
    List<Vuelo> buscarPorRuta(@Param("origen") String origen, @Param("destino") String destino);

    
    @Query(value = "SELECT * FROM vuelo WHERE salida >= :desde AND llegada <= :hasta ORDER BY salida ASC", nativeQuery = true)
    List<Vuelo> buscarEntreFechas(@Param("desde") LocalDateTime desde, @Param("hasta") LocalDateTime hasta);
}
