package com.zuluflow.beneficiary.api.dto;

public record BeneficiaryRequest(
        String name,
        String accountNumber,
        String bankCode,
        String clientId,
        String accountType
) {}