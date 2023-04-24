package com.example.workshopLHotelAshir.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rooms")
public class Room {

    @Id
    @Column(name="roomNumber")
    private Integer roomNumber;

    @Column(name="type")
    private String type;

    @Column(name="basePrice")
    private Double basePrice;

    public Room(){}

    public Room(Integer roomNumber, String type, Double basePrice) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.basePrice = basePrice;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public Double getBasePrice() {
        return basePrice;
    }
}
