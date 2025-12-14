package com.smarttravel.bookingservice.service.impl;

import com.smarttravel.bookingservice.client.FlightClient;
import com.smarttravel.bookingservice.client.HotelClient;
import com.smarttravel.bookingservice.dto.*;
import com.smarttravel.bookingservice.entity.*;
import com.smarttravel.bookingservice.exception.ResourceNotFoundException;
import com.smarttravel.bookingservice.repository.BookingRepository;
import com.smarttravel.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FlightClient flightClient;
    private final HotelClient hotelClient;

    @Qualifier("userWebClient")
    private final WebClient userWebClient;

    @Qualifier("notificationWebClient")
    private final WebClient notificationWebClient;

    @Qualifier("paymentWebClient")
    private final WebClient paymentWebClient;

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO request) {
        // 1. Validate user via WebClient
        UserDTO user = userWebClient.get()
                .uri("/api/users/{id}", request.getUserId())
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block();

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        // 2. Get flight & hotel via Feign
        FlightDTO flight = flightClient.getFlightById(request.getFlightId());
        HotelDTO hotel = hotelClient.getHotelById(request.getHotelId());

        // 3. Calculate total cost
        double totalCost = flight.getPrice() + hotel.getPricePerNight();

        // 4. Save booking with PENDING
        Booking booking = Booking.builder()
                .userId(request.getUserId())
                .flightId(request.getFlightId())
                .hotelId(request.getHotelId())
                .travelDate(request.getTravelDate())
                .totalCost(totalCost)
                .status(BookingStatus.PENDING)
                .build();

        booking = bookingRepository.save(booking);

        // 5. Call Payment Service
        PaymentRequestDTO paymentRequest = PaymentRequestDTO.builder()
                .bookingId(booking.getId())
                .amount(totalCost)
                .build();

        paymentWebClient.post()
                .uri("/api/payments")
                .bodyValue(paymentRequest)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        return mapToResponse(booking);
    }

    @Override
    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
        return mapToResponse(booking);
    }

    @Override
    public BookingResponseDTO updateBookingStatus(Long id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        booking.setStatus(BookingStatus.valueOf(status));
        booking = bookingRepository.save(booking);

        // If confirmed, send notification
        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            NotificationRequestDTO notification = NotificationRequestDTO.builder()
                    .userId(booking.getUserId())
                    .subject("Booking Confirmed")
                    .message("Your booking " + booking.getId() + " is confirmed.")
                    .build();

            notificationWebClient.post()
                    .uri("/api/notifications")
                    .bodyValue(notification)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        }

        return mapToResponse(booking);
    }

    private BookingResponseDTO mapToResponse(Booking booking) {
        return BookingResponseDTO.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .flightId(booking.getFlightId())
                .hotelId(booking.getHotelId())
                .travelDate(booking.getTravelDate())
                .totalCost(booking.getTotalCost())
                .status(booking.getStatus())
                .build();
    }
}
