package com.example.workshopLHotelAshir.model;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="reserva")
public class Booking {
    @Id
    @Column(name="codigo")
    private UUID codigo;
    @Column(name="fechaReserva")
    private String fechaReserva;

    @ManyToOne
    @JoinColumn(name="cedula")
    private Client client;
    @ManyToOne
    @JoinColumn(name="numero")
    private Room room;

    @Column(name="total")
    private Double total;

    public Booking() {
    }

    public Booking(Client client, Room room, String fechaReserva) {
        this.codigo = UUID.randomUUID();
        this.fechaReserva = fechaReserva;
        this.client = client;
        this.room = room;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public Client getCliente() {
        return client;
    }

    public Room getHabitacion() {
        return room;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
