package com.online.merchant.model.delete;

import com.online.merchant.view.delete.DeleteTransactionResponse;

@FunctionalInterface
public interface DeleteTransaction {

    DeleteTransactionResponse delete(String id);
}
