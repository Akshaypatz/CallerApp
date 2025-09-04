package com.example.CallerApp;

import com.billdesk.paymenthsm.client.internal.exception.HSMException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public CompletableFuture<String> processPayment(@RequestBody String panData) throws HSMException {
        return paymentService.processPayment(panData);
    }

    @PostMapping("/sign")
    public CompletableFuture<String> generateSignature(@RequestBody String data) throws HSMException {
        return paymentService.generateSignature(data);
    }
}

