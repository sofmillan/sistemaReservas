package com.example.workshopLHotelAshir.dto;


import java.util.UUID;
    public class BookingConfirmationDTO {
        private UUID bookingCode;
        private String bookingDate;
        private Integer roomNumber;
        private Double total;

        public BookingConfirmationDTO(){
        }
        public BookingConfirmationDTO(UUID bookingCode, String bookingDate, Integer roomNumber, Double total) {
            this.bookingCode = bookingCode;
            this.bookingDate = bookingDate;
            this.roomNumber = roomNumber;
            this.total = total;
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

        public Double getTotal() {
            return total;
        }
    }