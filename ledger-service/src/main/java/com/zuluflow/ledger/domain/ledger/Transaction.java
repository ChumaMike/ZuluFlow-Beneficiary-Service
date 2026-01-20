package com.zuluflow.ledger.domain.ledger;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount; // Always positive here. Logic handles direction.

    // Who sent it?
    @Column(nullable = false)
    private String debitAccountNumber;

    // Who got it?
    @Column(nullable = false)
    private String creditAccountNumber;

    @Column(nullable = false)
    private String reference; // e.g., "Payment for Invoice #101"

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;
}