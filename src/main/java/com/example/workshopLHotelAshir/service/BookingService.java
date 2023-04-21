package com.example.workshopLHotelAshir.service;

import com.example.workshopLHotelAshir.dto.BookingDTO;
import com.example.workshopLHotelAshir.exceptions.*;
import com.example.workshopLHotelAshir.model.Client;
import com.example.workshopLHotelAshir.model.Room;
import com.example.workshopLHotelAshir.model.Booking;
import com.example.workshopLHotelAshir.repository.ClientRepository;
import com.example.workshopLHotelAshir.repository.RoomRepository;
import com.example.workshopLHotelAshir.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BookingService {
    private RoomRepository habitacionRepository;
    private ClientRepository clienteRepository;
    private BookingRepository reservaRepository;

    @Autowired
    public BookingService(RoomRepository habitacionRepository, ClientRepository clienteRepository, BookingRepository reservaRepository) {
        this.habitacionRepository = habitacionRepository;
        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
    }

    public BookingDTO reservar(Long cedula, Integer numero, String fecha){
        if (cedula <= 0 || numero <= 0 || fecha == null){
            throw new InvalidDataException("Los datos no son válidos");
        }

        Optional<Client> cliente = this.clienteRepository.findById(cedula);
        Optional<Room> habitacion = this.habitacionRepository.findById(numero);

        if(cliente.isPresent() && habitacion.isPresent()){

            Pattern pattern = Pattern
                    .compile("^\\d{4}-\\d{2}-\\d{2}$");
            Matcher matcher = pattern.matcher(fecha);

            if(!matcher.find()){
                throw new InvalidDataException("La fecha no está en formato válido");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate date = LocalDate.parse(fecha, formatter);

            if(date.isBefore(LocalDate.now())){
                throw new InvalidDataException("La fecha no puede ser anterior a la actual");
            }

            List<Integer> disponiblesId = this.reservaRepository.getAvailability(fecha);
            boolean habitacionDisponible = disponiblesId.contains(numero);
            boolean noReservas = this.reservaRepository.cantidadReservas()==0;

            if(noReservas || (disponiblesId.size()!=0 && habitacionDisponible)){
                Room room1 = habitacion.get();
                double descuento = 0;
                Booking booking = new Booking(cliente.get(),habitacion.get(),fecha);
                if(room1.getTipoHabitacion().equalsIgnoreCase("premium")){
                    descuento = room1.getPrecioBase() * 0.05;
                }
                booking.setTotal(room1.getPrecioBase() - descuento);
                this.reservaRepository.save(booking);
                return new BookingDTO(booking.getCodigo(), booking.getFechaReserva(), booking.getHabitacion().getNumero(), booking.getTotal());
            }else{
                throw new BookedRoomException("La habitación ya esta reservada");
            }
        }
        throw new DataNotFoundException("Habitación y/o cliente no encontrados");
    }




    public List<Booking> getByClient(Long cedula){
        return this.reservaRepository.findAllById(cedula);
    }

    public Set<Room> getByDate(String date) {
        return this.reservaRepository.findByDate(date);
    }

    public List<Room> getByDateType(String date, String tipo){
        return this.reservaRepository.findByDateType(date, tipo);
    }
}
