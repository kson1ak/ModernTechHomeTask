# Консольное приложение электронной коммерции 

## Дисциплина
Современные технологии программирования

## Группа: ПИ24-1в

## Студенты
| № | ФИО | Порядковый номер |
|---|-----|------------------|
| 1 | Костикова Софья Павловна | 6 |
| 2 | Хечикян Маргарита Сергеевна | 20 |
---
### Требования
- IntelliJ IDEA Community Edition
- Java 17 или выше
---

## Цель работы

Спроектировать и реализовать **консольное** приложение магазина, демонстрирующее объектно-ориентированный дизайн на современном Java. Типы данных реализуются **с нуля**. В методе `main` создаются объекты и показываются **несколько сценариев оплаты** с использованием паттерна **«Стратегия»** для провайдеров платежей.

---

## Функциональных требования

| Функция | Описание | Статус |
|---------|----------|--------|
| **Product catalog** | Вывод списка товаров с категорией, ценой и остатком | ✅ Реализовано |
| **Shopping cart** | Добавление/удаление товаров, расчёт итога с НДС (20%) | ✅ Реализовано |
| **Order** | Создание заказа из корзины, вывод деталей заказа | ✅ Реализовано |
| **Payments** | Оплата через Ozon или Wildberries с разными PaymentMethod | ✅ Реализовано |
| **Order status** | Отображение изменений статуса заказа | ✅ Реализовано |


---

## Использование средства языка Java

| Средство | Где применено |
|----------|----------------|
| **Classes** | `Customer`, `Order`, `ShoppingCart`, `CreditCardPayment`, `DigitalWalletPayment`, `CashOnDelivery`, `OzonPayment`, `WildberriesPayment`, `ECommerceApp` |
| **Records** | `Product`, `CartItem`, `OrderItem` |
| **Interface** | `Payment` |
| **Sealed interface** | `PaymentMethod` |
| **Enums** | `OrderStatus`, `ProductCategory`, `PaymentStatus` |
| **Collections** | `ShoppingCart` (`List<CartItem>`), `Order` (`List<OrderItem>`), `ECommerceApp` (`List<Product>`), `createCatalog()` (`ArrayList`) |
---

## Структура пакетов 

Корень: `com.moderntech.ecommerce`

```text
com/moderntech/ecommerce/
├── main/
│   └── ECommerceApp.java
├── models/
│   ├── Product.java              (record)
│   ├── Customer.java             (класс)
│   ├── ShoppingCart.java         (класс)
│   ├── Order.java                (класс)
│   ├── CartItem.java             (record)
│   └── OrderItem.java            (record)
├── payment/
│   ├── Payment.java
│   ├── PaymentMethod.java        (sealed interface + permits)
│   ├── CreditCardPayment.java
│   ├── DigitalWalletPayment.java
│   ├── CashOnDelivery.java
│   ├── OzonPayment.java
│   ├── WildberriesPayment.java
│   └── PaymentStatus.java        (enum)
└── enums/
    ├── OrderStatus.java
    └── ProductCategory.java
```

---

## Спецификация 


**Перечисления**

- `OrderStatus`: PENDING, CONFIRMED, PROCESSING, SHIPPED, DELIVERED, CANCELLED  
- `ProductCategory`: SMARTPHONE, LAPTOP, TABLET, ACCESSORY, CAMERA  
- `PaymentStatus`: PENDING, SUCCESS, FAILED, REFUNDED, PROCESSING  
---

## Запуск программы

1) File -> Open -> выбрать папку E-commerce

2) Убедиться, что Project SDK установлен на OpenJDK 25 (File -> Project Structure -> Project)

3) Открыть src/com/moderntech/ecommerce/main/ECommerceApp.java

4) Нажать Run (Shift+F10) или кликнуть зеленую стрелку рядом с main

---

## ERD диаграмма

<img width="1179" height="1258" alt="image" src="https://github.com/user-attachments/assets/beca7043-b028-45ad-ad54-1fef3efc7f9f" />

---
## Скриншоты
<img width="1000" height="722" alt="image" src="https://github.com/user-attachments/assets/854c34b1-00d5-4ceb-aa07-f42264bce018" />
<img width="889" height="615" alt="image" src="https://github.com/user-attachments/assets/24d4837a-dd91-404e-9fce-adde91024aad" />
<img width="894" height="713" alt="image" src="https://github.com/user-attachments/assets/d30277d2-1694-4dd6-b66c-3259b454c9ea" />
<img width="1375" height="663" alt="image" src="https://github.com/user-attachments/assets/97654d2d-c6e9-4155-b5ee-aec16e9b831d" />
<img width="1011" height="793" alt="image" src="https://github.com/user-attachments/assets/a97dc743-d85d-43b5-a043-afab96e8b658" />

---





