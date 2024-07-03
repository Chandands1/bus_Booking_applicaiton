package com.flixBus.service.impl;

import com.flixBus.dto.BookingDTO;
import com.flixBus.entity.Booking;
import com.flixBus.exception.BookingNotFoundException;
import com.flixBus.repository.BookingRepository;
import com.flixBus.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        bookingDTO.setBookingTime(LocalDateTime.now()); // Set current booking time
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingDTO.class);
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));
        return modelMapper.map(booking, BookingDTO.class);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));
        booking.setUserId(bookingDTO.getUserId());
        booking.setBusId(bookingDTO.getBusId());
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setTotalPrice(bookingDTO.getTotalPrice());
        Booking updatedBooking = bookingRepository.save(booking);
        return modelMapper.map(updatedBooking, BookingDTO.class);
    }

    @Override
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));
        bookingRepository.delete(booking);
    }
}