package com.example.workshopLHotelAshir.controller;

import com.example.workshopLHotelAshir.model.Confirmacion;
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

    @PostMapping("/cliente/{cedula}/habitacion/{numero}/fecha/{fechaReserva}/reservar")
    public ResponseEntity<Confirmacion> add(@PathVariable ("cedula") Long cedula,
                                            @PathVariable ("numero") Integer numero,
                                            @PathVariable ("fechaReserva") String fechaReserva){
        Confirmacion confirmacion1 = reservaService.reservar(cedula, numero, fechaReserva);
        return ResponseEntity.ok(confirmacion1);
    }




    @GetMapping("/cliente/{cedula}")
    public List<Reserva> getByClient(@PathVariable Long cedula){
        return this.reservaService.getByClient(cedula);
    }

    @GetMapping("/disponible")
    public List<Habitacion> getByDate(@RequestParam String fecha){
        return this.reservaService.getByDate(fecha);
    }
    @GetMapping("/disponibilidad")
    public List<Habitacion> getByDateType(@RequestParam String fecha, @RequestParam String tipo){
        return this.reservaService.getByDateType(fecha,tipo);
    }
}
