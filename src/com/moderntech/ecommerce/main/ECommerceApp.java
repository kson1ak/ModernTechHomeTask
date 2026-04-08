package com.moderntech.ecommerce.main;

import com.moderntech.ecommerce.models.*;
import com.moderntech.ecommerce.enums.*;
import com.moderntech.ecommerce.payment.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ECommerceApp {

    public static void main(String[] args) {
        System.out.println("\n" + "█".repeat(80));
        System.out.println("              ДОБРО ПОЖАЛОВАТЬ В MODERN TECH E-COMMERCE");
        System.out.println("█".repeat(80));

        // ==================== 1. СОЗДАНИЕ КАТАЛОГА ТОВАРОВ ====================
        System.out.println("\nШАГ 1: СОЗДАНИЕ КАТАЛОГА ТОВАРОВ");
        List<Product> catalog = createCatalog();
        displayCatalog(catalog);

        // ==================== 2. СОЗДАНИЕ ПОКУПАТЕЛЯ ====================
        System.out.println("\nШАГ 2: СОЗДАНИЕ ПОКУПАТЕЛЯ");
        Customer customer = new Customer("Анна Иванова", "anna@email.com", "+7 999 123-45-67");
        System.out.println(customer);

        // ==================== 3. КОРЗИНА ====================
        System.out.println("\nШАГ 3: ДОБАВЛЕНИЕ ТОВАРОВ В КОРЗИНУ");
        ShoppingCart cart = new ShoppingCart();

        Product iphone = catalog.get(0);  // iPhone
        Product macbook = catalog.get(1); // MacBook
        Product airpods = catalog.get(3); // AirPods

        cart.addProduct(iphone, 1);
        cart.addProduct(macbook, 1);
        cart.addProduct(airpods, 2);

        cart.displayCart();

        // ==================== 4. ОФОРМЛЕНИЕ ЗАКАЗА ====================
        System.out.println("\nШАГ 4: ОФОРМЛЕНИЕ ЗАКАЗА");

        // Создаем OrderItems из корзины
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            orderItems.add(new OrderItem(
                    item.product().id(),
                    item.product().name(),
                    item.quantity(),
                    item.product().price()
            ));
        }

        Order order = new Order(customer.getId(), orderItems, cart.getTotalWithTax());
        order.displayOrder();

        // Изменяем статус заказа несколько раз
        System.out.println("\nИЗМЕНЕНИЕ СТАТУСА ЗАКАЗА:");
        order.setStatus(OrderStatus.CONFIRMED);
        order.setStatus(OrderStatus.PROCESSING);
        order.setStatus(OrderStatus.SHIPPED);

        // ==================== 5. ПЛАТЕЖНЫЕ СЦЕНАРИИ ====================
        System.out.println("\nШАГ 5: ПЛАТЕЖНЫЕ СЦЕНАРИИ");
        System.out.println("\n" + "═".repeat(80));
        System.out.println("СЦЕНАРИЙ 1: Ozon + Банковская карта");
        System.out.println("═".repeat(80));

        // Сценарий 1: Ozon + банковская карта
        CreditCardPayment creditCard = new CreditCardPayment(
                "1234567812345678", "ANNA IVANOVA", LocalDate.of(2026, 12, 31), "123"
        );
        OzonPayment ozonPayment1 = new OzonPayment("anna@ozon.ru");
        ozonPayment1.setInternalPaymentMethod(creditCard);
        ozonPayment1.pay(order.getTotalAmount());

        System.out.println("\n" + "═".repeat(80));
        System.out.println("СЦЕНАРИЙ 2: Wildberries + Электронный кошелек");
        System.out.println("═".repeat(80));

        // Сценарий 2: Wildberries + электронный кошелек
        DigitalWalletPayment wallet = new DigitalWalletPayment("WB_Wallet_12345", "+79991234567");
        WildberriesPayment wbPayment = new WildberriesPayment("user_wb_anna");
        wbPayment.setInternalPaymentMethod(wallet);
        wbPayment.pay(order.getTotalAmount());

        System.out.println("\n" + "═".repeat(80));
        System.out.println("СЦЕНАРИЙ 3: Ozon + Наложенный платеж");
        System.out.println("═".repeat(80));

        // Сценарий 3: Ozon + наложенный платеж
        CashOnDelivery cod = new CashOnDelivery("г. Москва, ул. Тверская, д. 10, кв. 25");
        OzonPayment ozonPayment2 = new OzonPayment("anna@ozon.ru");
        ozonPayment2.setInternalPaymentMethod(cod);
        ozonPayment2.pay(order.getTotalAmount());

        // ==================== 6. ИТОГОВАЯ СВОДКА ====================
        System.out.println("\nШАГ 6: ИТОГОВАЯ СВОДКА ПО ЗАКАЗУ");
        printOrderSummary(order, customer);

        System.out.println("\n" + "█".repeat(80));
        System.out.println("              СПАСИБО ЗА ПОКУПКУ!");
        System.out.println("█".repeat(80));
    }

    private static List<Product> createCatalog() {
        List<Product> catalog = new ArrayList<>();

        catalog.add(new Product(
                "P001", "iPhone 15 Pro", "Флагманский смартфон Apple",
                new BigDecimal("99900"), ProductCategory.SMARTPHONE, 25, 4.8, "Apple"
        ));
        catalog.add(new Product(
                "P002", "MacBook Pro 14", "Мощный ноутбук для работы",
                new BigDecimal("159900"), ProductCategory.LAPTOP, 15, 4.9, "Apple"
        ));
        catalog.add(new Product(
                "P003", "iPad Air", "Легкий и производительный планшет",
                new BigDecimal("69900"), ProductCategory.TABLET, 30, 4.7, "Apple"
        ));
        catalog.add(new Product(
                "P004", "AirPods Pro", "Беспроводные наушники с шумоподавлением",
                new BigDecimal("24900"), ProductCategory.ACCESSORY, 50, 4.8, "Apple"
        ));
        catalog.add(new Product(
                "P005", "Sony A7 III", "Профессиональная камера",
                new BigDecimal("189900"), ProductCategory.CAMERA, 8, 4.9, "Sony"
        ));

        return catalog;
    }

    private static void displayCatalog(List<Product> catalog) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                    КАТАЛОГ ТОВАРОВ");
        System.out.println("=".repeat(80));
        System.out.printf("%-8s %-20s %-15s %-15s %-8s%n",
                "ID", "Название", "Категория", "Цена", "Рейтинг");
        System.out.println("-".repeat(80));

        for (Product product : catalog) {
            System.out.printf("%-8s %-20s %-15s %-15s %-8.1f%n",
                    product.id(),
                    truncate(product.name(), 25),
                    product.category(),
                    product.getFormattedPrice(),
                    product.rating()
            );
        }
        System.out.println("=".repeat(80));
        System.out.println("Всего товаров в каталоге: " + catalog.size());
    }

    private static void printOrderSummary(Order order, Customer customer) {
        System.out.println("\n" + "═".repeat(80));
        System.out.println("                  ИТОГОВАЯ СВОДКА");
        System.out.println("═".repeat(80));
        System.out.println("Номер заказа:     " + order.getOrderId());
        System.out.println("Покупатель:       " + customer.getName());
        System.out.println("Email:            " + customer.getEmail());
        System.out.println("Телефон:          " + customer.getPhone());
        System.out.println("Статус заказа:    " + order.getStatus());
        System.out.println("═".repeat(80));
        System.out.println("СОСТАВ ЗАКАЗА:");
        System.out.println("-".repeat(80));

        for (OrderItem item : order.getItems()) {
            System.out.printf("  • %s x %d = %,.2f руб.%n",
                    item.productName(),
                    item.quantity(),
                    item.getTotalPrice()
            );
        }

        System.out.println("-".repeat(80));
        System.out.printf("ИТОГО К ОПЛАТЕ:   %,.2f руб.%n", order.getTotalAmount());
        System.out.println("═".repeat(80));
    }

    private static String truncate(String str, int length) {
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }
}