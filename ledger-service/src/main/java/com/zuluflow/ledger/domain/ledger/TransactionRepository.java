package com.zuluflow.ledger.domain.ledger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // See history for a specific account (Debit OR Credit side)
    List<Transaction> findByDebitAccountNumberOrCreditAccountNumberOrderByTimestampDesc(
            String debitAccountNumber,
            String creditAccountNumber
    );
}