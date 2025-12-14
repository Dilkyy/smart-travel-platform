
package com.smarttravel.paymentservice.controller;

import com.smarttravel.paymentservice.dto.PaymentRequestDTO;
import com.smarttravel.paymentservice.dto.PaymentResponseDTO;
import com.smarttravel.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
        import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO request) {
        PaymentResponseDTO response = paymentService.processPayment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
