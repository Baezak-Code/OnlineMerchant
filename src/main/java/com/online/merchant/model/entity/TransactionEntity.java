package com.online.merchant.model.entity;

import com.online.merchant.model.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TransactionEntity {

    @Id
    private String id;
    private String transactionDate;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    private double amount;
    private String currency;
    private String description;
}
