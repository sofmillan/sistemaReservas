package com.example.workshopLHotelAshir.service;

import com.example.workshopLHotelAshir.model.Habitacion;
import com.example.workshopLHotelAshir.repository.RepositoryHabitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceHabitacion {
    private RepositoryHabitacion repositoryHabitacion;

    @Autowired
    public ServiceHabitacion(RepositoryHabitacion repositoryHabitacion) {
        this.repositoryHabitacion = repositoryHabitacion;
    }

    public Habitacion crear(Habitacion habitacion){
        this.repositoryHabitacion.save(habitacion);
        return habitacion;
    }
}
