package com.example.workshopLHotelAshir.dto;

import java.util.UUID;
public class BookingByClientDTO {
    private Long clientId;
    private String clientFullName;
    private UUID bookingCode;
    private String bookingDate;
    private Integer roomNumber;
    private String roomType;
    private Double total;

    public BookingByClientDTO(){}

    public BookingByClientDTO(Long clientId, String clientFullName, UUID bookingCode,
                              String bookingDate, Integer roomNumber, String roomType, Double total) {
        this.clientId = clientId;
        this.clientFullName = clientFullName;
        this.bookingCode = bookingCode;
        this.bookingDate = bookingDate;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.total = total;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public UUID getBookingCode() {
        return bookingCode;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public Double getTotal() {
        return total;
    }
}
