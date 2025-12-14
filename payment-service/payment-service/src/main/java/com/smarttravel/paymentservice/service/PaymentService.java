
package com.smarttravel.paymentservice.service;

import com.smarttravel.paymentservice.dto.PaymentRequestDTO;
import com.smarttravel.paymentservice.dto.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO processPayment(PaymentRequestDTO request);
}
