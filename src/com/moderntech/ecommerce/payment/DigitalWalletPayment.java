package com.moderntech.ecommerce.payment;

import java.math.BigDecimal;
import java.util.UUID;

public final class DigitalWalletPayment implements PaymentMethod, Payment {
    private final String walletId;
    private final String phoneNumber;
    private String transactionId;
    private PaymentStatus status;

    public DigitalWalletPayment(String walletId, String phoneNumber) {
        this.walletId = walletId;
        this.phoneNumber = phoneNumber;
        this.status = PaymentStatus.PENDING;
    }

    @Override
    public void pay(BigDecimal amount) {
        if (validatePaymentDetails()) {
            this.transactionId = "DW_" + UUID.randomUUID().toString().substring(0, 8);
            this.status = PaymentStatus.SUCCESS;
            System.out.println("Оплата электронным кошельком " + walletId + " на сумму " + amount + " руб. УСПЕШНА");
        } else {
            this.status = PaymentStatus.FAILED;
            System.out.println("Ошибка: неверные данные кошелька");
        }
    }

    @Override
    public String getMethodName() {
        return "Электронный кошелек";
    }

    @Override
    public boolean validatePaymentDetails() {
        return walletId != null && !walletId.isBlank() &&
                phoneNumber != null && phoneNumber.matches("\\+?\\d{10,12}");
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
            System.out.println("Возврат " + amount + " руб. на кошелек " + walletId);
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