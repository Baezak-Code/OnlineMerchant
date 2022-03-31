package com.online.merchant.model.save;

import com.online.merchant.model.TransactionRepository;
import com.online.merchant.model.entity.TransactionEntity;
import com.online.merchant.view.TransactionRequest;
import com.online.merchant.view.save.SaveTransactionResponse;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SaveTransactionService implements SaveTransaction<TransactionRequest, SaveTransactionResponse> {

    private final TransactionRepository transactionRepository;

    public SaveTransactionService(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public SaveTransactionResponse save(final TransactionRequest transactionRequest) {
        log.info("Creating transaction request `{}`", transactionRequest);
        return SaveTransactionResponse.builder().transactionEntity(
                transactionRepository.save(TransactionEntity.builder().id(transactionRequest.getId())
                        .transactionDate(DateTime.now().toString())
                        .transactionStatus(transactionRequest.getTransactionStatus())
                        .amount(transactionRequest.getAmount())
                        .currency(transactionRequest.getCurrency())
                        .description(transactionRequest.getDescription())
                        .build()))
                .success(true).build();
    }
}
