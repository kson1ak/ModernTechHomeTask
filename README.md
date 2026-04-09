# Консольное приложение электронной коммерции 

## Дисциплина
Современные технологии программирования

## Группа: [ПИ24-1в]

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
| **Classes** | `Customer`, `Order`, `ShoppingCart` |
| **Records** | `Product`, `CartItem`, `OrderItem` |
| **Interface** | `Payment` (marketplace / provider) |
| **Sealed interface** | `PaymentMethod`  |
| **Enums** | `OrderStatus`, `ProductCategory`, `PaymentStatus` |
| **Collections** | `ArrayList` and `HashMap` used meaningfully (catalog,

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

## Чек-лист студента

- [x] ERD подготовлена и отражена в материалах сдачи  
- [x] Все требуемые типы и пакеты присутствуют  
- [x] Использованы records, sealed `PaymentMethod`, перечисления, `ArrayList`, `HashMap`  
- [x] Реализованы `Payment`, `OzonPayment`, `WildberriesPayment` (Стратегия)  
- [x] Проект компилируется и запускается; в README есть **скриншот**  
- [x] Указаны **группа**, **команда**, **ФИО**, **порядковые номера** в письме и README  
- [x] Сдано **до дедлайна**  

---

## Запуск приложения
[инструкция]

## ERD диаграмма


## Скриншоты




