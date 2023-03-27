package com.example.workshopLHotelAshir.service;

import com.example.workshopLHotelAshir.model.Cliente;
import com.example.workshopLHotelAshir.model.Habitacion;
import com.example.workshopLHotelAshir.model.Reserva;
import com.example.workshopLHotelAshir.repository.RepositoryCliente;
import com.example.workshopLHotelAshir.repository.RepositoryHabitacion;
import com.example.workshopLHotelAshir.repository.RepositoryReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceReserva {
    private RepositoryHabitacion habitacionRepository;
    private RepositoryCliente clienteRepository;
    private RepositoryReserva reservaRepository;

    @Autowired
    public ServiceReserva(RepositoryHabitacion habitacionRepository, RepositoryCliente clienteRepository, RepositoryReserva reservaRepository) {
        this.habitacionRepository = habitacionRepository;
        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
    }

    public Reserva reservar(Long cedula, Integer numero, String fecha){
        System.out.println("entró a service reservar");

        Optional<Cliente> cliente = this.clienteRepository.findById(cedula);
        Optional<Habitacion> habitacion = this.habitacionRepository.findById(numero);

        if(cliente.isPresent() && habitacion.isPresent()){
            System.out.println("Encontrados");
            Reserva reserva = new Reserva(cliente.get(),habitacion.get(),fecha);
            this.reservaRepository.save(reserva);
            System.out.println("Nueva reserva hecha");
            return reserva;
        }

        System.out.println("No creó la reserva");
        return new Reserva();
    }

    public List<Reserva> getByClient(Long cedula){
        return this.reservaRepository.findAllById(cedula);
    }

    public List<Habitacion> getByDate(String date){
        return this.reservaRepository.findByDate(date);
    }
}
