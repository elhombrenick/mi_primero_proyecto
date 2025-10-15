package org.aerolineas.persistence;

import java.util.List;
import org.aerolineas.entity.bean.Venta;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

   
    @Query(value = "SELECT * FROM venta WHERE id_cliente=:id ORDER BY fecha_venta DESC", nativeQuery = true)
    List<Venta> listarPorCliente(@Param("id") Integer idCliente);
}
