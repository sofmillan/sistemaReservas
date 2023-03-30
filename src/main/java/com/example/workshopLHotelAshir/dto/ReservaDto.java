package com.example.workshopLHotelAshir.dto;


import com.example.workshopLHotelAshir.model.Cliente;
import com.example.workshopLHotelAshir.model.Habitacion;

import java.util.UUID;
    public class ReservaDto {
        private UUID numReserva;
        private String fecha;
        private Integer numHabitacion;
        private String nombre;
        private Double total;

        public ReservaDto(){
        }
        public ReservaDto(UUID numReserva, String fecha, Integer numHabitacion, String nombre, Double total) {
            this.numReserva = numReserva;
            this.fecha = fecha;
            this.numHabitacion = numHabitacion;
            this.nombre = nombre;
            this.total = total;
        }
        public UUID getNumReserva() {
            return numReserva;
        }
        public String getFecha() {
            return fecha;
        }
        public Integer getNumHabitacion() {
            return numHabitacion;
        }
        public String getNombre() {
            return nombre;
        }
        public Double getTotal() {
            return total;
        }
    }