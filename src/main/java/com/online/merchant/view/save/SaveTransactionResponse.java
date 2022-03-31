package com.online.merchant.view.save;

import com.online.merchant.model.entity.TransactionEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SaveTransactionResponse {

    private TransactionEntity transactionEntity;
    private boolean success;
    private String errorMsg;
}
