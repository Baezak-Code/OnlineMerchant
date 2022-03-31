package com.online.merchant.model;

public enum TransactionStatus {
    /**
     * This is a payment that has begun, but is not complete.
     */
    PENDING,

    /**
     * This is a payment that has been paid and the product delivered to the customer.
     */
    COMPLETE,

    /**
     * This is a payment where money has been transferred back to the customer and the
     * customer no longer has access to the product.
     */
    REFUNDED,

    /**
     * This is a payment where the payment process failed, whether it be a credit card
     * rejection or some other error.
     */
    FAILED,

    /**
     * If a Pending payment is never completed it becomes Abandoned after a week.
     */
    ABANDONED,

    /**
     * Revoked payments restrict access to the product without refunding money.
     */
    REVOKED,

    /**
     * A preapproved payment is one where the customer has approved the payment, but it
     * hasn't been processed yet. It'll be processed at a later date.
     */
    PREAPPROVED,

    /**
     * Cancelled is used in two different scenarios. One deals with  Recurring Payments.
     * When a subscription is cancelled then the original payment gets set to cancelled
     * as well. Cancelled is also used with preapprovals. A preapproval may be cancelled
     * before payment is made.
     */
    CANCELLED,

    /**
     * This refers to a renewal payment for a subscription that uses recurring billing.
     * This payment status will be present if Recurring Payments is active.
     */
    SUBSCRIPTION
}
