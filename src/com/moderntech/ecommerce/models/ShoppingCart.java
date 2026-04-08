package com.moderntech.ecommerce.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<CartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        if (product.stockQuantity() < quantity) {
            System.out.println("Ошибка: Недостаточно товара на складе!");
            return;
        }

        // Проверяем, есть ли уже такой товар в корзине
        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            if (item.product().equals(product)) {
                items.set(i, new CartItem(product, item.quantity() + quantity));
                System.out.println("Добавлено " + quantity + " шт. товара: " + product.name());
                return;
            }
        }

        items.add(new CartItem(product, quantity));
        System.out.println("Добавлено " + quantity + " шт. товара: " + product.name());
    }

    public void removeProduct(Product product) {
        items.removeIf(item -> item.product().equals(product));
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public BigDecimal getTotalWithoutTax() {
        return items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTaxAmount() {
        // НДС 20%
        return getTotalWithoutTax().multiply(new BigDecimal("0.20"));
    }

    public BigDecimal getTotalWithTax() {
        return getTotalWithoutTax().add(getTaxAmount());
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    ВАША КОРЗИНА");
        System.out.println("=".repeat(70));
        System.out.printf("%-25s %-10s %-15s %-15s%n", "Товар", "Кол-во", "Цена за шт.", "Итого");
        System.out.println("-".repeat(70));

        for (CartItem item : items) {
            System.out.printf("%-25s %-10d %-15s %-15s%n",
                    truncate(item.product().name(), 25),
                    item.quantity(),
                    item.product().getFormattedPrice(),
                    formatPrice(item.getTotalPrice())
            );
        }

        System.out.println("-".repeat(70));
        System.out.printf("%-50s %-15s%n", "Сумма без НДС:", formatPrice(getTotalWithoutTax()));
        System.out.printf("%-50s %-15s%n", "НДС (20%):", formatPrice(getTaxAmount()));
        System.out.printf("%-50s %-15s%n", "ИТОГО:", formatPrice(getTotalWithTax()));
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