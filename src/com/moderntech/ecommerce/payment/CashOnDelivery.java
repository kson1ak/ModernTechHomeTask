package com.moderntech.ecommerce.payment;

import java.math.BigDecimal;
import java.util.UUID;

public final class CashOnDelivery implements PaymentMethod, Payment {
    private String transactionId;
    private PaymentStatus status;
    private final String deliveryAddress;

    public CashOnDelivery(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        this.status = PaymentStatus.PENDING;
    }

    @Override
    public void pay(BigDecimal amount) {
        this.transactionId = "COD_" + UUID.randomUUID().toString().substring(0, 8);
        this.status = PaymentStatus.PENDING;
        System.out.println("Наложенный платеж на сумму " + amount + " руб. (оплата при доставке по адресу: " + deliveryAddress + ")");
    }

    @Override
    public String getMethodName() {
        return "Наложенный платеж";
    }

    @Override
    public boolean validatePaymentDetails() {
        return deliveryAddress != null && !deliveryAddress.isBlank();
    }

    @Override
    public boolean processPayment(BigDecimal amount) {
        pay(amount);
        return true;
    }

    @Override
    public boolean refundPayment(BigDecimal amount) {
        if (status == PaymentStatus.SUCCESS) {
            this.status = PaymentStatus.REFUNDED;
            System.out.println("Возврат наличными " + amount + " руб.");
            return true;
        }
        return false;
    }

    public void confirmDeliveryPayment() {
        this.status = PaymentStatus.SUCCESS;
        System.out.println("Оплата наличными подтверждена при доставке");
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public PaymentStatus getStatus() {
        return status;
    }
}