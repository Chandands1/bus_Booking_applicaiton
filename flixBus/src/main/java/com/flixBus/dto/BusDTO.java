package com.flixBus.dto;

import lombok.Data;

@Data
public class BusDTO {
    private Long id;
    private String busNumber;
    private String busType;
    private int capacity;
    private String route;
}
