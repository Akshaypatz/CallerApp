package com.example.CallerApp;


import com.billdesk.paymenthsm.client.HSMClient;
import com.billdesk.paymenthsm.client.internal.enums.ACS_BANK;
import com.billdesk.paymenthsm.client.internal.exception.HSMException;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PaymentService {
    private final HSMClient hsmClient;

    public PaymentService(HSMClient hsmClient) {
        this.hsmClient = hsmClient;
    }

    public CompletableFuture<String> processPayment(String panData) throws HSMException {
        return hsmClient.generateVisaCAVV(ACS_BANK.SBI, panData).thenApply(cavv -> "CAVV: " + cavv);
    }

    public CompletableFuture<String> generateSignature(String data) throws HSMException {
        return hsmClient.generateHMAC("BD_HMAC1", data);
    }
}