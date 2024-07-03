package com.flixBus.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long busId;
    private LocalDateTime bookingTime;
    private int numberOfSeats;
    private double totalPrice;
}