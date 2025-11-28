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

###📘 OOP Concepts Used
###🟦 Abstraction

Abstract classes: Product, Payment

### 🟩 Inheritance

Electronics, Clothing, Grocery → extend Product

### 🟪 Polymorphism

Runtime polymorphism with payment.pay(amount)

Overridden product details per category

### 🟧 Interfaces

Delivery implemented by FastDelivery, NormalDelivery, StorePickupDelivery

### 🟨 Encapsulation

Private fields + getters in Product class

🧩 Sample Output
Laptop - Lenovo
Warranty: 2 years
UPI payment successfully paid: 20000
Fast Delivery chosen
Order placed successfully!

## 🎯 Why This Project Is Useful

Demonstrates strong understanding of OOP principles

Great portfolio project for freshers

Easy to extend into a multi-file Java application

Perfect for GitHub, resumes, or interviews

## 📌 Future Enhancements (Optional Ideas)

Add coupon system

Add login / register

Add cart functionality

Add file-based order storage

Expand to a GUI / Spring Boot application

### ▶️ How to Run the Project

1️⃣ **Compile the project**
```bash
javac Main.java
```

## 2️⃣ Run the application

java Main

## 👨‍💻 Author

Rajasekar M
Passionate Java & Web Developer

⭐ Support

If you like this project, please ⭐ the repository!


---

If you want, I can also **make it look more modern for GitHub** with **badges for Java, build status, and license**, which really makes it stand out for recruiters.  



