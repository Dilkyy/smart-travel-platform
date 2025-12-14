package com.smarttravel.bookingservice.service;


import com.smarttravel.bookingservice.dto.BookingRequestDTO;
import com.smarttravel.bookingservice.dto.BookingResponseDTO;

public interface BookingService {

    BookingResponseDTO createBooking(BookingRequestDTO request);

    BookingResponseDTO getBookingById(Long id);

    BookingResponseDTO updateBookingStatus(Long id, String status);
}

