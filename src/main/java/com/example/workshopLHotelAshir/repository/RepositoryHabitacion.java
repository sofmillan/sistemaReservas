package com.example.workshopLHotelAshir.repository;

import com.example.workshopLHotelAshir.model.Habitacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryHabitacion extends CrudRepository<Habitacion, Integer> {
}
