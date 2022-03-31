package com.online.merchant.view.delete;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteTransactionResponse {

    private boolean success;
    private String errorMsg;
}
