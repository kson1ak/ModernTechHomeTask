package com.moderntech.ecommerce.models;

import com.moderntech.ecommerce.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final String customerId;
    private final List<OrderItem> items;
    private final BigDecimal totalAmount;
    private OrderStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(String customerId, List<OrderItem> items, BigDecimal totalAmount) {
        this.orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = OrderStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
        System.out.println("Заказ " + orderId + " -> Статус изменен на: " + status);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void displayOrder() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    ЗАКАЗ #" + orderId);
        System.out.println("=".repeat(70));
        System.out.println("Статус: " + status);
        System.out.println("Дата создания: " + createdAt);
        System.out.println("-".repeat(70));
        System.out.printf("%-30s %-10s %-15s%n", "Товар", "Кол-во", "Цена");
        System.out.println("-".repeat(70));

        for (OrderItem item : items) {
            System.out.printf("%-30s %-10d %-15s%n",
                    truncate(item.productName(), 30),
                    item.quantity(),
                    formatPrice(item.getTotalPrice())
            );
        }

        System.out.println("-".repeat(70));
        System.out.printf("%-50s %-15s%n", "ОБЩАЯ СУММА:", formatPrice(totalAmount));
        System.out.println("=".repeat(70));
    }

    private String formatPrice(BigDecimal price) {
        return String.format("%,.2f руб.", price);
    }

    private String truncate(String str, int length) {
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }
}