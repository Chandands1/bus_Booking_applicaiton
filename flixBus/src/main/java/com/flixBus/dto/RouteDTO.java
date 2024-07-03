package com.flixBus.dto;

import lombok.Data;

@Data
public class RouteDTO {
    private Long id;
    private String routeName;
    private String origin;
    private String destination;
    private double fare;
}
