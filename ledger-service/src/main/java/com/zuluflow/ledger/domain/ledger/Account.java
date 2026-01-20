package com.zuluflow.ledger.domain.ledger;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String currency; // e.g., "ZAR", "USD"

    @Column(nullable = false)
    private BigDecimal balance; // NEVER use Double for money!

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    // OPTIMISTIC LOCKING
    // This prevents two people from swiping the card at the exact same millisecond.
    // If version changes while we are working, the transaction fails.
    @Version
    private Long version;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}

enum AccountStatus { ACTIVE, FROZEN, CLOSED }