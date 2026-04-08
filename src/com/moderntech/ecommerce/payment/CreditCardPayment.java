package com.moderntech.ecommerce.payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public final class CreditCardPayment implements PaymentMethod, Payment {
    private final String cardNumber;
    private final String cardHolderName;
    private final LocalDate expiryDate;
    private final String cvv;
    private String transactionId;
    private PaymentStatus status;

    public CreditCardPayment(String cardNumber, String cardHolderName, LocalDate expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.status = PaymentStatus.PENDING;
    }

    @Override
    public void pay(BigDecimal amount) {
        if (validatePaymentDetails()) {
            this.transactionId = "CC_" + UUID.randomUUID().toString().substring(0, 8);
            this.status = PaymentStatus.SUCCESS;
            System.out.println("Оплата картой " + maskCardNumber() + " на сумму " + amount + " руб. УСПЕШНА");
        } else {
            this.status = PaymentStatus.FAILED;
            System.out.println("Ошибка валидации карты");
        }
    }

    @Override
    public String getMethodName() {
        return "Банковская карта";
    }

    @Override
    public boolean validatePaymentDetails() {
        return cardNumber != null && cardNumber.length() == 16 &&
                cvv != null && cvv.length() == 3 &&
                expiryDate != null && expiryDate.isAfter(LocalDate.now());
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
            System.out.println("  ↩️ Возврат " + amount + " руб. на карту " + maskCardNumber());
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

    private String maskCardNumber() {
        return "**** **** **** " + cardNumber.substring(12);
    }
}