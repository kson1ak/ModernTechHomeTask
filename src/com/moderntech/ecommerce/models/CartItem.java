package com.moderntech.ecommerce.models;

import java.math.BigDecimal;

public record CartItem(Product product, int quantity) {

    public CartItem {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше 0");
        }
    }

    public BigDecimal getTotalPrice() {
        return product.price().multiply(BigDecimal.valueOf(quantity));
    }
}