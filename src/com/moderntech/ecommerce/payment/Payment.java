package com.moderntech.ecommerce.payment;

import java.math.BigDecimal;

public interface Payment {
    boolean processPayment(BigDecimal amount);
    boolean refundPayment(BigDecimal amount);
    String getTransactionId();
    PaymentStatus getStatus();
}