package com.online.merchant.model.read;

import com.online.merchant.model.TransactionRepository;
import com.online.merchant.model.exception.IdNotPresentException;
import com.online.merchant.model.exception.ReadTransactionFailedException;
import com.online.merchant.model.entity.TransactionEntity;
import org.springframework.stereotype.Service;

@Service
public class ReadTransactionService implements ReadTransaction {

    private final TransactionRepository transactionRepository;

    public ReadTransactionService(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionEntity read(final String id) {
        if (id == null) {
            throw new IdNotPresentException();
        }
        return transactionRepository.findById(id).orElseThrow(() ->
                new ReadTransactionFailedException("Transaction could not be found with id '" + id + "'."));
    }
}
