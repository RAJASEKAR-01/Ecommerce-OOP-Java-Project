# 🛒 E-Commerce Console Application (Java OOP Project)

A fully functional **console-based E-Commerce system** built using **Java OOP principles**.  
This project demonstrates **Abstraction, Inheritance, Polymorphism, Encapsulation, Interfaces, Composition**, and **basic exception handling**.

---

## 🚀 Features

### 🛍 Product Management
- Electronics, Clothing, Grocery (OOP-based categories)
- Product details (name, price, brand, warranty, size, expiry, weight)
- Clean separation using:
  - Abstract `Product` class
  - Child classes overriding `Productdetails()`

### 💳 Payment System
- Abstract `Payment` class
- UPI, Card, NetBanking payment implementations
- Supports dynamic amount handling

### 🚚 Delivery Options
- Interface-based delivery system
- FastDelivery, NormalDelivery, StorePickupDelivery
- Demonstrates polymorphism with interface implementations

### 📦 Order Processing
- Class `Order` uses **composition**
- Combines Product + Payment + Delivery
- Prints detailed order summary

---

## 🧱 Project Structure

