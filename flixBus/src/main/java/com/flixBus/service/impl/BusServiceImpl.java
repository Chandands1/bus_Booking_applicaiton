package com.flixBus.service.impl;

import com.flixBus.dto.BusDTO;
import com.flixBus.entity.Bus;
import com.flixBus.exception.BusNotFoundException;
import com.flixBus.repository.BusRepository;
import com.flixBus.service.BusService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {
    private final BusRepository busRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public BusDTO createBus(BusDTO busDTO) {
        Bus bus = modelMapper.map(busDTO, Bus.class);
        Bus savedBus = busRepository.save(bus);
        return modelMapper.map(savedBus, BusDTO.class);
    }

    @Override
    public BusDTO getBusById(Long id) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new BusNotFoundException("Bus not found with id: " + id));
        return modelMapper.map(bus, BusDTO.class);
    }

    @Override
    public List<BusDTO> getAllBuses() {
        return busRepository.findAll().stream()
                .map(bus -> modelMapper.map(bus, BusDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BusDTO updateBus(Long id, BusDTO busDTO) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new BusNotFoundException("Bus not found with id: " + id));
        bus.setBusNumber(busDTO.getBusNumber());
        bus.setBusType(busDTO.getBusType());
        bus.setCapacity(busDTO.getCapacity());
        bus.setRoute(busDTO.getRoute());
        Bus updatedBus = busRepository.save(bus);
        return modelMapper.map(updatedBus, BusDTO.class);
    }

    @Override
    public void deleteBus(Long id) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new BusNotFoundException("Bus not found with id: " + id));
        busRepository.delete(bus);
    }
}
