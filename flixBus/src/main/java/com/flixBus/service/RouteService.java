package com.flixBus.service;

import com.flixBus.dto.RouteDTO;

import java.util.List;

public interface RouteService {
    RouteDTO createRoute(RouteDTO routeDTO);
    RouteDTO getRouteById(Long id);
    List<RouteDTO> getAllRoutes();
    RouteDTO updateRoute(Long id, RouteDTO routeDTO);
    void deleteRoute(Long id);
}