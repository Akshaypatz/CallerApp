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
    public String processPayment(@RequestBody String panData) {
        return paymentService.processPayment(panData);
    }

    @PostMapping("/sign")
    public CompletableFuture<String> generateHmac(@RequestBody String data) {
        return paymentService.generateHmac(data);
    }
}

