package com.online.merchant.model.read;

import com.online.merchant.model.entity.TransactionEntity;

@FunctionalInterface
public interface ReadTransaction {

    TransactionEntity read(String id);
}
