package com.zuluflow.ledger.domain.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// "name" is the internal ID. "url" is where it lives (Beneficiary Service is on 8080)
@FeignClient(name = "beneficiary-service", url = "http://localhost:8080")
public interface BeneficiaryClient {

    // We only need to check if they exist.
    // We are calling the GET /api/v1/beneficiaries endpoint we built earlier.
    @GetMapping("/api/v1/beneficiaries")
    String getBeneficiaries(@RequestParam("clientId") String clientId);
}