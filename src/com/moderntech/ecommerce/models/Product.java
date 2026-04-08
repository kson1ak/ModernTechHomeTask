package com.moderntech.ecommerce.models;

import com.moderntech.ecommerce.enums.ProductCategory;
import java.math.BigDecimal;

public record Product(
        String id,
        String name,
        String description,
        BigDecimal price,
        ProductCategory category,
        int stockQuantity,
        double rating,
        String manufacturer
) {

    // Compact constructor for validation
    public Product {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
    }

    // Check if product is in stock
    public boolean isInStock() {
        return stockQuantity > 0;
    }

    // Format price for display
    public String getFormattedPrice() {
        return String.format("%,.2f руб.", price);
    }

    @Override
    public String toString() {
        return String.format("%s - %s: %s (Остаток: %d)",
                id, name, getFormattedPrice(), stockQuantity);
    }
}