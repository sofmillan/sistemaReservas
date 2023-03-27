package com.example.workshopLHotelAshir.service;

import com.example.workshopLHotelAshir.model.Cliente;
import com.example.workshopLHotelAshir.model.Confirmacion;
import com.example.workshopLHotelAshir.model.Habitacion;
import com.example.workshopLHotelAshir.model.Reserva;
import com.example.workshopLHotelAshir.repository.RepositoryCliente;
import com.example.workshopLHotelAshir.repository.RepositoryHabitacion;
import com.example.workshopLHotelAshir.repository.RepositoryReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public Confirmacion reservar(Long cedula, Integer numero, String fecha){
        if (cedula <= 0 || numero <= 0 || fecha == null){
            throw new RuntimeException("Los datos no son válidos");
        }

        Optional<Cliente> cliente = this.clienteRepository.findById(cedula);
        Optional<Habitacion> habitacion = this.habitacionRepository.findById(numero);

        if(cliente.isPresent() && habitacion.isPresent()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate date = LocalDate.parse(fecha, formatter);
            Habitacion hab1 = habitacion.get();
            List<Integer> disponiblesId = this.reservaRepository.getAvailability(fecha);
            if(disponiblesId.contains(hab1.getNumero())){
                if(date.isBefore(LocalDate.now())){
                    throw new RuntimeException("La fecha no puede ser anterior a la actual");
                }
                double descuento =0;
                Reserva reserva = new Reserva(cliente.get(),habitacion.get(),date);
                if(hab1.getTipoHabitacion().equalsIgnoreCase("premium")){
                    descuento = hab1.getPrecioBase() * 0.05;
                }
                reserva.setTotal(hab1.getPrecioBase()-descuento);
                this.reservaRepository.save(reserva);

                Confirmacion confirmacion = new Confirmacion(reserva.getCodigo(),reserva.getFechaReserva(),
                        reserva.getHabitacion().getNumero(), reserva.getCliente().getNombre(),reserva.getTotal());
                return confirmacion;
            }else{
                throw new RuntimeException("Habitación no disponible");
            }

        }


        return new Confirmacion();
    }



    public List<Reserva> getByClient(Long cedula){
        return this.reservaRepository.findAllById(cedula);
    }

    public List<Habitacion> getByDate(String date) {
        return this.reservaRepository.findByDate(date);
    }

    public List<Habitacion> getByDateType(String date, String tipo){
        return this.reservaRepository.findByDateType(date, tipo);
    }
}
