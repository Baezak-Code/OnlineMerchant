package com.online.merchant.controller;

import com.online.merchant.model.delete.DeleteTransactionService;
import com.online.merchant.model.entity.TransactionEntity;
import com.online.merchant.model.read.ReadTransactionService;
import com.online.merchant.model.save.SaveTransactionService;
import com.online.merchant.view.TransactionRequest;
import com.online.merchant.view.delete.DeleteTransactionResponse;
import com.online.merchant.view.save.SaveTransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping(path = "/transaction/v1")
@RestController
public class TransactionController {

    private final SaveTransactionService saveTransactionService;
    private final ReadTransactionService readTransactionService;
    private final DeleteTransactionService deleteTransactionService;

    public TransactionController(@Autowired final SaveTransactionService saveTransactionService,
                                 @Autowired final ReadTransactionService readTransactionService,
                                 @Autowired final DeleteTransactionService deleteTransactionService) {
        this.saveTransactionService = saveTransactionService;
        this.readTransactionService = readTransactionService;
        this.deleteTransactionService = deleteTransactionService;
    }

    @Operation(summary = "Creates a new transaction in the system, with the specified attributes provided at call time.")
    @ApiResponse(responseCode = "200", description = "The transaction has successfully been added to the system.",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SaveTransactionResponse.class))})
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SaveTransactionResponse createTransaction(@Valid @RequestBody final TransactionRequest transactionRequest) {
        return saveTransactionService.save(transactionRequest);
    }

    @Operation(summary = "Returns the transaction that corresponds to the id provided at call time.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The transaction has successfully been found.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TransactionEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid/non-existent ID input into the system."),
            @ApiResponse(responseCode = "404", description = "Transaction cannot be found using the ID provided.")})
    @GetMapping(path = "/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionEntity readTransaction(@RequestParam final String id) {
        return readTransactionService.read(id);
    }

    @Operation(summary = "Updates an existing transaction in the system, with the specified attributes provided at call time.")
    @ApiResponse(responseCode = "200", description = "The transaction has successfully been updated in the system.",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SaveTransactionResponse.class))})
    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SaveTransactionResponse updateTransaction(@Valid @RequestBody final TransactionRequest transactionRequest) {
        return createTransaction(transactionRequest);
    }

    @Operation(summary = "Deletes the transaction that corresponds to the id provided at call time.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The transaction has successfully been deleted from the system.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DeleteTransactionResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid/non-existent ID input into the system.")})
    @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeleteTransactionResponse deleteTransaction(@RequestParam final String id) {
        return deleteTransactionService.delete(id);
    }
}
