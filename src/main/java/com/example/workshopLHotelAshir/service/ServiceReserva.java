package com.example.workshopLHotelAshir.service;

import com.example.workshopLHotelAshir.dto.ReservaDto;
import com.example.workshopLHotelAshir.exceptions.DataNotFoundException;
import com.example.workshopLHotelAshir.exceptions.IncorrectFormatException;
import com.example.workshopLHotelAshir.exceptions.InvalidDataException;
import com.example.workshopLHotelAshir.exceptions.InvalidDateException;
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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public ReservaDto reservar(Long cedula, Integer numero, String fecha){
        if (cedula <= 0 || numero <= 0 || fecha == null){
            throw new InvalidDataException("Los datos no son válidos");
        }

        Optional<Cliente> cliente = this.clienteRepository.findById(cedula);
        Optional<Habitacion> habitacion = this.habitacionRepository.findById(numero);

        if(cliente.isPresent() && habitacion.isPresent()){
            Pattern pattern = Pattern
                    .compile("^\\d{4}-\\d{2}-\\d{2}$");
            Matcher matcher = pattern.matcher(fecha);

            if(!matcher.find()){
            throw new IncorrectFormatException("La fecha no está en formato válido");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate date = LocalDate.parse(fecha, formatter);

            Habitacion habitacion1 = habitacion.get();
                if(date.isBefore(LocalDate.now())){
                    throw new InvalidDateException("La fecha no puede ser anterior a la actual");
                }
                double descuento = 0;
                Reserva reserva = new Reserva(cliente.get(),habitacion.get(),fecha);
                if(habitacion1.getTipoHabitacion().equalsIgnoreCase("premium")){
                    descuento = habitacion1.getPrecioBase() * 0.05;
                }
                reserva.setTotal(habitacion1.getPrecioBase() - descuento);
                this.reservaRepository.save(reserva);

                ReservaDto reservaConfirmada = new ReservaDto(reserva.getCodigo(),reserva.getFechaReserva(), reserva.getHabitacion().getNumero(), reserva.getCliente().getNombre(),reserva.getTotal());
                return reservaConfirmada;
            }

            throw new DataNotFoundException("Habitación y/o cliente no encontrados");
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
