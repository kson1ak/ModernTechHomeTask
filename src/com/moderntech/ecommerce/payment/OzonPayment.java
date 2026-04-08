package com.moderntech.ecommerce.payment;

import java.math.BigDecimal;
import java.util.UUID;

public final class OzonPayment implements PaymentMethod, Payment {
    private final String ozonAccountEmail;
    private String transactionId;
    private PaymentStatus status;
    private PaymentMethod internalPaymentMethod;

    public OzonPayment(String ozonAccountEmail) {
        this.ozonAccountEmail = ozonAccountEmail;
        this.status = PaymentStatus.PENDING;
    }

    public void setInternalPaymentMethod(PaymentMethod paymentMethod) {
        this.internalPaymentMethod = paymentMethod;
    }

    @Override
    public void pay(BigDecimal amount) {
        this.transactionId = "OZON_" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("Обработка платежа через Ozon (аккаунт: " + ozonAccountEmail + ")");

        if (internalPaymentMethod != null) {
            internalPaymentMethod.pay(amount);
            if (internalPaymentMethod instanceof Payment) {
                Payment p = (Payment) internalPaymentMethod;
                this.status = p.getStatus();
            } else {
                this.status = PaymentStatus.SUCCESS;
            }
        } else {
            this.status = PaymentStatus.SUCCESS;
            System.out.println("Оплата через Ozon Pay на сумму " + amount + " руб. УСПЕШНА");
        }
    }

    @Override
    public String getMethodName() {
        return "Ozon Pay";
    }

    @Override
    public boolean validatePaymentDetails() {
        return ozonAccountEmail != null && ozonAccountEmail.contains("@");
    }

    @Override
    public boolean processPayment(BigDecimal amount) {
        pay(amount);
        return status == PaymentStatus.SUCCESS;
    }

    @Override
    public boolean refundPayment(BigDecimal amount) {
        if (status == PaymentStatus.SUCCESS) {
            this.status = PaymentStatus.REFUNDED;
            System.out.println("  ↩️ Возврат " + amount + " руб. на счет Ozon");
            return true;
        }
        return false;
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