package com.moderntech.ecommerce.payment;

import java.math.BigDecimal;

public sealed interface PaymentMethod permits CreditCardPayment, DigitalWalletPayment, CashOnDelivery, OzonPayment, WildberriesPayment {

    void pay(BigDecimal amount);
    String getMethodName();
    boolean validatePaymentDetails();
}