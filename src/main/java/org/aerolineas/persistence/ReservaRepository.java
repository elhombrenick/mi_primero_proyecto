package org.aerolineas.persistence;

import java.util.List;
import org.aerolineas.entity.bean.Reserva;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    //jpql listar reservas por cliente
    @Query("SELECT r FROM Reserva r WHERE r.cliente.idCliente=:idCliente ORDER BY r.fechaReserva DESC")
    List<Reserva> listarPorCliente(@Param("idCliente") Integer idCliente);
}
