package com.zuluflow.ledger.domain.ledger;

import com.zuluflow.ledger.domain.client.BeneficiaryClient; // <--- This import should work now
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final BeneficiaryClient beneficiaryClient; // <--- The Bridge

    // 1. Create a Wallet
    public Account createAccount(String currency) {
        Account account = Account.builder()
                .accountNumber(generateAccountNumber())
                .currency(currency)
                .balance(BigDecimal.ZERO)
                .status(AccountStatus.ACTIVE)
                .build();
        return accountRepository.save(account);
    }

    // 2. TRANSFER FUNDS (With Beneficiary Check)
    @Transactional
    public Transaction transferFunds(String fromAccountNum, String toAccountNum, BigDecimal amount, String reference) {

        // --- NEW: CONNECTION CHECK ---
        System.out.println("--- CALLING BENEFICIARY SERVICE ---");
        try {
            // We ask: "Does CLIENT-BMW-001 have beneficiaries?"
            // This proves the two services are talking.
            String response = beneficiaryClient.getBeneficiaries("CLIENT-BMW-001");
            System.out.println("Response from Beneficiary Service: " + response);
        } catch (Exception e) {
            System.out.println("WARNING: Could not connect to Beneficiary Service: " + e.getMessage());
        }
        System.out.println("--- CONNECTION CHECK COMPLETE ---");
        // -----------------------------

        // A. Validation
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        if (fromAccountNum.equals(toAccountNum)) {
            throw new IllegalArgumentException("Cannot transfer to self");
        }

        // B. Load Accounts
        Account source = getAccount(fromAccountNum);
        Account target = getAccount(toAccountNum);

        // C. Check Balance
        if (source.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        // D. The Movement
        source.setBalance(source.getBalance().subtract(amount)); // DEBIT
        target.setBalance(target.getBalance().add(amount));      // CREDIT

        // E. Save
        accountRepository.save(source);
        accountRepository.save(target);

        // F. Record Transaction
        Transaction record = Transaction.builder()
                .amount(amount)
                .debitAccountNumber(fromAccountNum)
                .creditAccountNumber(toAccountNum)
                .reference(reference)
                .build();

        return transactionRepository.save(record);
    }

    // Helper: Find or Fail
    public Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("Account not found: " + accountNumber));
    }

    // Helper: Generator
    private String generateAccountNumber() {
        return "ACC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}