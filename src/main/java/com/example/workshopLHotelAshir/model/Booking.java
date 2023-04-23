package com.example.workshopLHotelAshir.model;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="reserva")
public class Booking {
    @Id
    @Column(name="codigo")
    private UUID code;
    @Column(name="fechaReserva")
    private String bookingDate;

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

    public Booking( String bookingDate, Client client, Room room) {
        this.code = UUID.randomUUID();
        this.bookingDate = bookingDate;
        this.client = client;
        this.room = room;
        this.total = total;
    }

    public UUID getCode() {
        return code;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public Client getClient() {
        return client;
    }

    public Room getRoom() {
        return room;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
