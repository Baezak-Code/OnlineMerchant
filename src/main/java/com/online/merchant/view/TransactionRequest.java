package com.online.merchant.view;

import com.online.merchant.model.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class TransactionRequest {

    @NotBlank(message = "The transaction id must be specified.")
    private String id;
    private TransactionStatus transactionStatus;
    @Min(value = 0, message = "Amount specified can't be smaller than 0.")
    private double amount;
    @NotBlank(message = "The transaction currency must be specified.")
    private String currency;
    private String description;
}
