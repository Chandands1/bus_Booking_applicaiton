package com.flixBus.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Long id;
    private Long bookingId;
    private LocalDateTime paymentTime;
    private double amount;
    private String paymentStatus;
}