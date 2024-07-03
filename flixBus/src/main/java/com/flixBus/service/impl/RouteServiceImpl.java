package com.flixBus.service.impl;

import com.flixBus.dto.RouteDTO;
import com.flixBus.entity.Route;
import com.flixBus.exception.RouteNotFoundException;
import com.flixBus.repository.RouteRepository;
import com.flixBus.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public RouteDTO createRoute(RouteDTO routeDTO) {
        Route route = modelMapper.map(routeDTO, Route.class);
        Route savedRoute = routeRepository.save(route);
        return modelMapper.map(savedRoute, RouteDTO.class);
    }

    @Override
    public RouteDTO getRouteById(Long id) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new RouteNotFoundException("Route not found with id: " + id));
        return modelMapper.map(route, RouteDTO.class);
    }

    @Override
    public  List<RouteDTO> getAllRoutes() {
        return routeRepository.findAll().stream()
                .map(route -> modelMapper.map(route, RouteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RouteDTO updateRoute(Long id, RouteDTO routeDTO) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new RouteNotFoundException("Route not found with id: " + id));
        route.setRouteName(routeDTO.getRouteName());
        route.setOrigin(routeDTO.getOrigin());
        route.setDestination(routeDTO.getDestination());
        route.setFare(routeDTO.getFare());
        Route updatedRoute = routeRepository.save(route);
        return modelMapper.map(updatedRoute, RouteDTO.class);
    }

    @Override
    public void deleteRoute(Long id) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new RouteNotFoundException("Route not found with id: " + id));
        routeRepository.delete(route);
    }
}
