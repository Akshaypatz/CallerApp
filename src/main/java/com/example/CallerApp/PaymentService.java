package com.example.CallerApp;


import com.billdesk.paymenthsm.client.HSMClient;
import com.billdesk.paymenthsm.client.internal.enums.ACS_BANK;
import com.billdesk.paymenthsm.client.internal.exception.HSMException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class PaymentService {
    private final HSMClient hsmClient;

    public PaymentService(HSMClient hsmClient) {
        this.hsmClient = hsmClient;
    }

    public String processPayment(String data) {
        try {

            String op =  hsmClient.generateVisaCAVV(ACS_BANK.SBI, data)
                    .get(500, TimeUnit.MILLISECONDS);
            log.info("OP {}",op);
            return op;
        } catch (TimeoutException e) {
            log.error("HSM call timed out after 500ms for data: {}", data, e);
            return "TIMEOUT";
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            log.error("HSM call failed for data: {}", data, cause);

            if (cause instanceof HSMException) {
                return "HSM_ERROR";
            } else {
                return "UNKNOWN_ERROR";
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while waiting for HSM", e);
        }
    }

    public CompletableFuture<String> generateHmac(String data) {
       return  hsmClient.generateHMAC("BD_HMAC1", data)
                .orTimeout(500, TimeUnit.MILLISECONDS)
                .thenApply(hmac -> "HMAC: " + hmac)
                .exceptionally(ex -> {
                    log.error("HSM call failed for hmac generation for data: {}", data, ex);
                    return null;
                });
    }
}