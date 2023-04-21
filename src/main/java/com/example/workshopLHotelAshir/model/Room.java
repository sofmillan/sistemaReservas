package com.example.workshopLHotelAshir.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rooms")
public class Room {

    @Id
    @Column(name="bookNumber")
    private Integer bookNumber;

    @Column(name="type")
    private String type;

    @Column(name="basePrice")
    private Double basePrice;

    public Room(){}

    public Room(Integer bookNumber, String type, Double basePrice) {
        this.bookNumber = bookNumber;
        this.type = type;
        this.basePrice = basePrice;
    }

    public Integer getBookNumber() {
        return bookNumber;
    }

    public String getType() {
        return type;
    }

    public Double getBasePrice() {
        return basePrice;
    }
}
