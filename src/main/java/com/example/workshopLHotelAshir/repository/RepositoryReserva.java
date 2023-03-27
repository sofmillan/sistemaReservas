package com.example.workshopLHotelAshir.repository;

import com.example.workshopLHotelAshir.model.Habitacion;
import com.example.workshopLHotelAshir.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositoryReserva extends CrudRepository<Reserva, UUID> {
    @Query("SELECT r FROM Reserva r WHERE r.cliente.cedula = ?1")
    public List<Reserva> findAllById(Long keyword);

    @Query("Select  h FROM Habitacion h, Reserva r WHERE h.numero not in(SELECT habitacion.numero from Reserva) " +
            "OR h.numero not in(select distinct r.habitacion.numero from  Reserva r where r.fechaReserva= ?1)")
    public List<Habitacion> findByDate(String fecha);
}
