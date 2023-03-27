package com.example.workshopLHotelAshir.controller;

import com.example.workshopLHotelAshir.model.Habitacion;
import com.example.workshopLHotelAshir.model.Reserva;
import com.example.workshopLHotelAshir.service.ServiceReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ControllerReserva {
    private ServiceReserva reservaService;

    @Autowired
    public ControllerReserva(ServiceReserva reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/reserva")
    public ResponseEntity<Reserva> add(@RequestBody Reserva reserva){
        Reserva reserva1 = reservaService.reservar(reserva);
        return ResponseEntity.ok(reserva1);
    }
    @GetMapping("/cliente/{cedula}")
    public List<Reserva> getByClient(@PathVariable Long cedula){
        return this.reservaService.getByClient(cedula);
    }
}
