package com.smarttravel.paymentservice.service.impl;

import com.smarttravel.paymentservice.dto.PaymentRequestDTO;
import com.smarttravel.paymentservice.dto.PaymentResponseDTO;
import com.smarttravel.paymentservice.entity.Payment;
import com.smarttravel.paymentservice.repository.PaymentRepository;
import com.smarttravel.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final WebClient bookingWebClient;

    @Override
    public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
        // simple fake logic: always success
        Payment payment = Payment.builder()
                .bookingId(request.getBookingId())
                .amount(request.getAmount())
                .status("SUCCESS")
                .paymentTime(LocalDateTime.now())
                .build();

        Payment saved = paymentRepository.save(payment);

        // Notify booking-service that payment succeeded
        bookingWebClient.put()
                .uri("/api/bookings/{id}/status?status=CONFIRMED", request.getBookingId())
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        return PaymentResponseDTO.builder()
                .id(saved.getId())
                .bookingId(saved.getBookingId())
                .amount(saved.getAmount())
                .status(saved.getStatus())
                .build();
    }
}

