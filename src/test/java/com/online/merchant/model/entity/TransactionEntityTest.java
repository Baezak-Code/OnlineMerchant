package com.online.merchant.model.entity;

import com.online.merchant.model.TransactionStatus;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransactionEntityTest {

    @Test
    public void testCreatingTransactionEntity() {
        final String transactionDate = DateTime.now().toString();
        final TransactionEntity transactionEntity = TransactionEntity.builder().id("Test1")
                .transactionDate(transactionDate)
                .transactionStatus(TransactionStatus.COMPLETE)
                .amount(100)
                .currency("GBP")
                .description("")
                .build();
        assertNotNull(transactionEntity);
        assertEquals("Test1", transactionEntity.getId());
        assertEquals(transactionDate, transactionEntity.getTransactionDate());
        assertEquals(TransactionStatus.COMPLETE, transactionEntity.getTransactionStatus());
        assertEquals(100, transactionEntity.getAmount(), 0);
        assertEquals("GBP", transactionEntity.getCurrency());
    }
}