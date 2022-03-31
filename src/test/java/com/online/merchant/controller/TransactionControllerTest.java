package com.online.merchant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.merchant.Application;
import com.online.merchant.model.TransactionStatus;
import com.online.merchant.view.TransactionRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser(value = "username")
    @Test
    public void testCreatingTransactionReturns200WithValidRequestBody() throws Exception {
        performPostRequest("/transaction/v1/create",
                MediaType.APPLICATION_JSON,
                createTransactionRequest("Test1", TransactionStatus.PENDING,
                        100, "GBP", "This is a test."),
                status().isOk());
    }

    @WithMockUser(value = "username")
    @Test
    public void testCreatingTransactionReturns400WithInvalidRequestBodyIdAttribute() throws Exception {
        performPostRequest("/transaction/v1/create",
                MediaType.APPLICATION_JSON,
                createTransactionRequest(null, TransactionStatus.PENDING,
                        100, "GBP", "This is a test."),
                status().isBadRequest());
    }

    @WithMockUser(value = "username")
    @Test
    public void testCreatingTransactionReturns400WithInvalidRequestBodyAmountAttribute() throws Exception {
        performPostRequest("/transaction/v1/create",
                MediaType.APPLICATION_JSON,
                createTransactionRequest("Test1", TransactionStatus.PENDING,
                        -100, "GBP", "This is a test."),
                status().isBadRequest());
    }

    @WithMockUser(value = "username")
    @Test
    public void testCreatingTransactionReturns400WithInvalidRequestBodyCurrencyAttribute() throws Exception {
        performPostRequest("/transaction/v1/create",
                MediaType.APPLICATION_JSON,
                createTransactionRequest("Test1", TransactionStatus.PENDING,
                        100, null, "This is a test."),
                status().isBadRequest());
    }

    @WithMockUser(value = "username")
    @Test
    public void testReadingTransactionReturns200WithValidId() throws Exception {
        testCreatingTransactionReturns200WithValidRequestBody();
        performGetRequest("/transaction/v1/read", "Test1", status().isOk());
    }

    @WithMockUser(value = "username")
    @Test
    public void testReadingTransactionReturns400WithInvalidId() throws Exception {
        testCreatingTransactionReturns200WithValidRequestBody();
        performGetRequest("/transaction/v1/read", null, status().isBadRequest());
    }

    @WithMockUser(value = "username")
    @Test
    public void testReadingTransactionReturns404WhenTransactionCannotBeFound() throws Exception {
        testCreatingTransactionReturns200WithValidRequestBody();
        performGetRequest("/transaction/v1/read", "Test2", status().isNotFound());
    }

    @WithMockUser(value = "username")
    @Test
    public void testUpdatingTransactionReturns200WithValidRequestBody() throws Exception {
        testCreatingTransactionReturns200WithValidRequestBody();
        performGetRequest("/transaction/v1/read", "Test1", status().isOk());
        performPutRequest("/transaction/v1/update",
                MediaType.APPLICATION_JSON,
                createTransactionRequest("Test1", TransactionStatus.COMPLETE,
                        300, "GBP", "This is a test."),
                status().isOk());
        performGetRequest("/transaction/v1/read", "Test1", status().isOk());
    }

    @WithMockUser(value = "username")
    @Test
    public void testDeletingTransactionReturns200WithValidId() throws Exception {
        testCreatingTransactionReturns200WithValidRequestBody();
        performDeleteRequest("/transaction/v1/delete", "Test1", status().isOk());
    }

    @WithMockUser(value = "username")
    @Test
    public void testDeletingTransactionReturns400WithInvalidId() throws Exception {
        testCreatingTransactionReturns200WithValidRequestBody();
        performDeleteRequest("/transaction/v1/delete", null, status().isBadRequest());
    }

    private void performDeleteRequest(final String urlTemplate,
                                   final String id,
                                   final ResultMatcher resultMatcher) throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(urlTemplate)
                        .param("id", id))
                .andExpect(resultMatcher);
    }

    private void performGetRequest(final String urlTemplate,
                                final String id,
                                final ResultMatcher resultMatcher) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(urlTemplate)
                        .param("id", id))
                .andExpect(resultMatcher);
    }

    private void performPutRequest(final String urlTemplate,
                                    final MediaType mediaType,
                                    final TransactionRequest transactionRequest,
                                    final ResultMatcher resultMatcher) throws Exception {
        mvc.perform(MockMvcRequestBuilders.put(urlTemplate)
                        .contentType(mediaType)
                        .accept(mediaType)
                        .content(objectMapper.writeValueAsString(transactionRequest)))
                .andExpect(resultMatcher);
    }

    private void performPostRequest(final String urlTemplate,
                                final MediaType mediaType,
                                final TransactionRequest transactionRequest,
                                final ResultMatcher resultMatcher) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(urlTemplate)
                        .contentType(mediaType)
                        .accept(mediaType)
                        .content(objectMapper.writeValueAsString(transactionRequest)))
                .andExpect(resultMatcher);
    }

    private TransactionRequest createTransactionRequest(final String id,
                                                        final TransactionStatus transactionStatus,
                                                        final double amount,
                                                        final String currency,
                                                        final String description) {
        final TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setId(id);
        transactionRequest.setTransactionStatus(transactionStatus);
        transactionRequest.setAmount(amount);
        transactionRequest.setCurrency(currency);
        transactionRequest.setDescription(description);
        return transactionRequest;
    }
}
