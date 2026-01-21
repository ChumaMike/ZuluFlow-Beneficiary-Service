package com.zuluflow.beneficiary.api.controller;

import com.zuluflow.beneficiary.api.dto.BeneficiaryRequest;
import com.zuluflow.beneficiary.domain.beneficiary.AccountType; // Import
import com.zuluflow.beneficiary.domain.beneficiary.Beneficiary;
import com.zuluflow.beneficiary.domain.beneficiary.BeneficiaryStatus; // Import
import com.zuluflow.beneficiary.domain.beneficiary.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beneficiaries")
@RequiredArgsConstructor
public class BeneficiaryController {

    private final BeneficiaryService service;

    @PostMapping
    public ResponseEntity<Beneficiary> createBeneficiary(@RequestBody BeneficiaryRequest request) {

        Beneficiary beneficiary = Beneficiary.builder()
                .name(request.name())
                .accountNumber(request.accountNumber())
                .bankCode(request.bankCode())
                .clientId(request.clientId())
                // 👇 THIS IS THE MISSING LINE 👇
                .accountType(AccountType.valueOf(request.accountType()))
                // 👇 THIS IS ALSO IMPORTANT 👇
                .status(BeneficiaryStatus.ACTIVE)
                .build();

        Beneficiary created = service.createBeneficiary(beneficiary);
        return ResponseEntity.created(URI.create("/api/v1/beneficiaries/" + created.getId()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<Beneficiary>> getBeneficiaries(@RequestParam String clientId) {
        return ResponseEntity.ok(service.getBeneficiaries(clientId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiary> getBeneficiary(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBeneficiary(id));
    }
}