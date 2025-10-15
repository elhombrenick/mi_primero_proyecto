package org.aerolineas.persistence;

import java.util.List;
import org.aerolineas.entity.bean.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Integer> {

    
    @Query("SELECT DISTINCT r.destino FROM Ruta r ORDER BY r.destino ASC")
    List<String> destinosDisponibles();
}
