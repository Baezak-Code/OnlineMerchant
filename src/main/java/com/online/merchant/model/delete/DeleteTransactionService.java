package com.online.merchant.model.delete;

import com.online.merchant.model.TransactionRepository;
import com.online.merchant.model.exception.IdNotPresentException;
import com.online.merchant.view.delete.DeleteTransactionResponse;
import org.springframework.stereotype.Service;

@Service
public class DeleteTransactionService implements DeleteTransaction {

    private final TransactionRepository transactionRepository;

    public DeleteTransactionService(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public DeleteTransactionResponse delete(final String id) {
        if (id == null) {
            throw new IdNotPresentException();
        }
        transactionRepository.deleteById(id);
        return DeleteTransactionResponse.builder().success(true).build();
    }
}
