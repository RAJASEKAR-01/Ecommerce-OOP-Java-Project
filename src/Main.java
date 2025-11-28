import java.util.*;

/*
 * EcommerceApp.java
 * Single-file console-based E-Commerce application demonstrating OOP:
 * - Abstraction, Inheritance, Polymorphism
 * - Interfaces, Encapsulation, Composition
 * - Method overloading, static order counter, basic exception handling
 *
 * To run:
 * 1) Save as EcommerceApp.java
 * 2) javac EcommerceApp.java
 * 3) java EcommerceApp
 */

abstract class Product {
    private final String name;
    private final double price; // base price (without GST)

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getProductName() { return name; }
    public double getProductPrice() { return price; }

    // each subclass defines its GST amount (absolute rupees)
    abstract double gstAmount(double quantity);

    // show product-specific details
    abstract void productDetails();
}

class Electronics extends Product {
    private final String brand;
    private final int warrantyYears;

    Electronics(String name, double price, String brand, int warrantyYears) {
        super(name, price);
        this.brand = brand;
        this.warrantyYears = warrantyYears;
    }

    @Override
    double gstAmount(double quantity) {
        // 18% GST on price * quantity
        return getProductPrice() * quantity * 0.18;
    }

    @Override
    void productDetails() {
        System.out.println("Type     : Electronics");
        System.out.println("Brand    : " + brand);
        System.out.println("Warranty : " + warrantyYears + " year(s)");
    }
}

class Clothing extends Product {
    private final String clothType;
    private final int size;

    Clothing(String name, double price, String clothType, int size) {
        super(name, price);
        this.clothType = clothType;
        this.size = size;
    }

    @Override
    double gstAmount(double quantity) {
        // 12% GST for clothing (common in many places) on price * quantity
        return getProductPrice() * quantity * 0.12;
    }

    @Override
    void productDetails() {
        System.out.println("Type     : Clothing");
        System.out.println("Cloth    : " + clothType);
        System.out.println("Size     : " + size);
    }
}

class Grocery extends Product {
    private final int expiryYear;
    private final double weightInGrams;

    Grocery(String name, double price, int expiryYear, double weightInGrams) {
        super(name, price);
        this.expiryYear = expiryYear;
        this.weightInGrams = weightInGrams;
    }

    @Override
    double gstAmount(double quantity) {
        // Grocery often has low or zero GST; use 5% here (adjustable)
        return getProductPrice() * quantity * 0.05;
    }

    @Override
    void productDetails() {
        System.out.println("Type       : Grocery");
        System.out.println("Expiry Year: " + expiryYear);
        System.out.println("Weight     : " + weightInGrams + " g");
    }
}

// Payment abstraction
abstract class Payment {
    abstract void pay(double amount);

    // Overloaded method: pay with coupon code — default behavior is no coupon logic
    void pay(double amount, String coupon) {
        // default: ignore coupon - child classes may override
        pay(amount);
    }
}

class UpiPayment extends Payment {
    @Override
    void pay(double amount) {
        System.out.printf("UPI payment successful. Paid: Rs %.2f%n", amount);
    }

    @Override
    void pay(double amount, String coupon) {
        double finalAmount = applyCoupon(amount, coupon);
        System.out.printf("UPI payment successful with coupon '%s'. Paid: Rs %.2f%n", coupon, finalAmount);
    }

    private double applyCoupon(double amount, String coupon) {
        if (coupon == null) return amount;
        switch (coupon.toUpperCase()) {
            case "SAVE10": return amount * 0.90;     // 10% off
            case "FLAT200": return Math.max(0, amount - 200); // flat ₹200 off
            default: return amount;
        }
    }
}

class CardPayment extends Payment {
    @Override
    void pay(double amount) {
        System.out.printf("Card payment successful. Paid: Rs %.2f%n", amount);
    }

    @Override
    void pay(double amount, String coupon) {
        double finalAmount = applyCoupon(amount, coupon);
        System.out.printf("Card payment successful with coupon '%s'. Paid: Rs %.2f%n", coupon, finalAmount);
    }

    private double applyCoupon(double amount, String coupon) {
        if (coupon == null) return amount;
        switch (coupon.toUpperCase()) {
            case "SAVE10": return amount * 0.90;
            case "FLAT200": return Math.max(0, amount - 200);
            default: return amount;
        }
    }
}

class NetBankingPayment extends Payment {
    @Override
    void pay(double amount) {
        System.out.printf("NetBanking payment successful. Paid: Rs %.2f%n", amount);
    }

    @Override
    void pay(double amount, String coupon) {
        double finalAmount = applyCoupon(amount, coupon);
        System.out.printf("NetBanking payment successful with coupon '%s'. Paid: Rs %.2f%n", coupon, finalAmount);
    }

    private double applyCoupon(double amount, String coupon) {
        if (coupon == null) return amount;
        switch (coupon.toUpperCase()) {
            case "SAVE10": return amount * 0.90;
            case "FLAT200": return Math.max(0, amount - 200);
            default: return amount;
        }
    }
}

// Delivery interface
interface Deliverable {
    void deliver();
}

class FastDelivery implements Deliverable {
    @Override
    public void deliver() {
        System.out.println("Delivery: Fast Delivery (arrives within 1 day)");
    }
}

class NormalDelivery implements Deliverable {
    @Override
    public void deliver() {
        System.out.println("Delivery: Normal Delivery (3–4 days)");
    }
}

class StorePickupDelivery implements Deliverable {
    @Override
    public void deliver() {
        System.out.println("Delivery: Store Pickup - collect from nearest store");
    }
}

// Discount interface and implementations
interface Discount {
    double applyDiscount(double amount);
    String getName();
}

class NoDiscount implements Discount {
    @Override public double applyDiscount(double amount) { return amount; }
    @Override public String getName() { return "No Discount"; }
}

class FestivalDiscount implements Discount {
    @Override public double applyDiscount(double amount) { return amount * 0.90; } // 10% off
    @Override public String getName() { return "Festival Discount (10%)"; }
}

class ClearanceSaleDiscount implements Discount {
    @Override public double applyDiscount(double amount) { return amount * 0.75; } // 25% off
    @Override public String getName() { return "Clearance Sale (25%)"; }
}

// CartItem to hold product + quantity
class CartItem {
    private final Product product;
    private int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = Math.max(1, quantity);
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int q) { this.quantity = Math.max(1, q); }
    public double itemSubtotal() { return product.getProductPrice() * quantity; }
    public double itemGST() { return product.gstAmount(quantity); }
}

// Order class with cart and order flow
class Order {
    private static int orderCounter = 0;

    private final int orderId;
    private final List<CartItem> cart;
    private final Payment paymentMethod;
    private final Deliverable deliveryMethod;
    private final Discount discount;

    Order(List<CartItem> cart, Payment paymentMethod, Deliverable deliveryMethod, Discount discount) {
        this.orderId = ++orderCounter;
        this.cart = new ArrayList<>(cart);
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.discount = discount;
    }

    void printInvoiceAndPlaceOrder(String couponCode) {
        System.out.println("\n================= INVOICE =================");
        System.out.println("Order ID: " + orderId);
        double subtotal = 0;
        double totalGST = 0;

        System.out.println("\nItems:");
        for (CartItem ci : cart) {
            Product p = ci.getProduct();
            int qty = ci.getQuantity();
            double price = p.getProductPrice();
            double itemSub = price * qty;
            double itemGst = p.gstAmount(qty);
            subtotal += itemSub;
            totalGST += itemGst;

            System.out.printf("- %s x%d   @ Rs %.2f each   Subtotal: Rs %.2f   GST: Rs %.2f%n",
                    p.getProductName(), qty, price, itemSub, itemGst);
            p.productDetails();
            System.out.println("--------------------------------------");
        }

        System.out.printf("Subtotal: Rs %.2f%n", subtotal);
        System.out.printf("Total GST: Rs %.2f%n", totalGST);

        double total = subtotal + totalGST;
        System.out.printf("Total before discount: Rs %.2f%n", total);

        if (discount != null) {
            total = discount.applyDiscount(total);
            System.out.println("Discount applied: " + discount.getName());
        }

        // Coupon codes handled at payment stage via overloaded pay
        System.out.printf("Final amount to pay: Rs %.2f%n", total);

        // do payment (with or without coupon)
        if (couponCode != null && !couponCode.trim().isEmpty()) {
            paymentMethod.pay(total, couponCode);
        } else {
            paymentMethod.pay(total);
        }

        deliveryMethod.deliver();

        System.out.println("Order placed successfully. Thank you!");
        System.out.println("===========================================\n");
    }
}

// Main application
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Product> catalog = new ArrayList<>();
    private static final List<CartItem> cart = new ArrayList<>();
    private static final List<String> orderHistory = new ArrayList<>();

    public static void main(String[] args) {
        seedCatalog();
        System.out.println("Welcome to Console E-Commerce App (Mini Project)");
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1 : showCatalog(); break;
                case 2 : addToCartFlow(); break;
                case 3 : removeFromCartFlow(); break;
                case 4 : viewCart(); break;
                case 5 : checkoutFlow();break;
                case 6 : viewOrderHistory();break;
                case 0 : {
                    System.out.println("Exiting — Goodbye!");
                    running = false;
                    break;
                }
                default : System.out.println("Invalid option. Try again."); break;
            }
        }
    }

    // initial products
    private static void seedCatalog() {
        catalog.add(new Electronics("Laptop", 45000, "Lenovo", 2));
        catalog.add(new Electronics("Smartphone", 22000, "Samsung", 1));
        catalog.add(new Clothing("T-Shirt", 799, "Cotton", 40));
        catalog.add(new Clothing("Jeans", 1499, "Denim", 32));
        catalog.add(new Grocery("Rice", 1200, 2026, 5000));
        catalog.add(new Grocery("Face Wash", 250, 2025, 100));
    }

    private static void printMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Show catalog");
        System.out.println("2. Add product to cart");
        System.out.println("3. Remove product from cart");
        System.out.println("4. View cart");
        System.out.println("5. Checkout");
        System.out.println("6. View order history");
        System.out.println("0. Exit");
    }

    private static void showCatalog() {
        System.out.println("\n--- CATALOG ---");
        for (int i = 0; i < catalog.size(); i++) {
            Product p = catalog.get(i);
            System.out.printf("%d) %s - Rs %.2f%n", i + 1, p.getProductName(), p.getProductPrice());
            p.productDetails();
            System.out.println("--------------------------------");
        }
    }

    private static void addToCartFlow() {
        showCatalog();
        int idx = readInt("Enter product number to add to cart (0 to cancel): ");
        if (idx <= 0 || idx > catalog.size()) {
            System.out.println("Cancelled or invalid product number.");
            return;
        }
        int qty = readInt("Enter quantity: ");
        if (qty <= 0) {
            System.out.println("Quantity must be at least 1.");
            return;
        }
        Product selected = catalog.get(idx - 1);
        // if exists in cart, increment quantity
        CartItem existing = findCartItemByProduct(selected);
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + qty);
        } else {
            cart.add(new CartItem(selected, qty));
        }
        System.out.println("Added to cart: " + selected.getProductName() + " x" + qty);
    }

    private static void removeFromCartFlow() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        viewCart();
        int idx = readInt("Enter cart item number to remove (0 to cancel): ");
        if (idx <= 0 || idx > cart.size()) {
            System.out.println("Cancelled or invalid number.");
            return;
        }
        CartItem ci = cart.remove(idx - 1);
        System.out.println("Removed: " + ci.getProduct().getProductName());
    }

    private static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("\n--- CART ---");
        for (int i = 0; i < cart.size(); i++) {
            CartItem ci = cart.get(i);
            System.out.printf("%d) %s x%d  @ Rs %.2f each  Subtotal: Rs %.2f  GST: Rs %.2f%n",
                    i + 1,
                    ci.getProduct().getProductName(),
                    ci.getQuantity(),
                    ci.getProduct().getProductPrice(),
                    ci.itemSubtotal(),
                    ci.itemGST());
        }
        double subtotal = cart.stream().mapToDouble(CartItem::itemSubtotal).sum();
        double gst = cart.stream().mapToDouble(CartItem::itemGST).sum();
        System.out.printf("Cart total (without discount): Rs %.2f (GST: Rs %.2f)%n", subtotal + gst, gst);
    }

    private static void checkoutFlow() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Add products before checkout.");
            return;
        }

        // Choose discount
        System.out.println("\nChoose discount option:");
        System.out.println("1) No Discount");
        System.out.println("2) Festival Discount (10%)");
        System.out.println("3) Clearance Sale (25%)");
        int dChoice = readInt("Select option: ");
        Discount discount;
        switch (dChoice) {
            case 2 : discount = new FestivalDiscount(); break;
            case 3 : discount = new ClearanceSaleDiscount(); break;
            default : discount = new NoDiscount(); break;
        }

        // Choose delivery
        System.out.println("\nChoose delivery method:");
        System.out.println("1) Fast Delivery");
        System.out.println("2) Normal Delivery");
        System.out.println("3) Store Pickup");
        int delChoice = readInt("Select option: ");
        Deliverable delivery;
        switch (delChoice) {
            case 1 : delivery = new FastDelivery(); break;
            case 3 : delivery = new StorePickupDelivery(); break;
            default : delivery = new NormalDelivery(); break;
        }

        // Choose payment
        System.out.println("\nChoose payment method:");
        System.out.println("1) UPI");
        System.out.println("2) Card");
        System.out.println("3) NetBanking");
        int payChoice = readInt("Select option: ");
        Payment payment;
        switch (payChoice) {
            case 2 : payment = new CardPayment(); break;
            case 3 : payment = new NetBankingPayment(); break;
            default : payment = new UpiPayment(); break;
        }

        // coupon code (optional for extra discount at payment stage)
        System.out.print("Enter coupon code (SAVE10 / FLAT200) or press Enter to skip: ");
        String coupon = scanner.nextLine().trim();
        if (coupon.isEmpty()) coupon = null;

        // Prepare cart for order
        Order order = new Order(cart, payment, delivery, discount);
        order.printInvoiceAndPlaceOrder(coupon);

        // Save a simple order summary in history (you could save full details)
        orderHistory.add("Order ID " + getLastOrderId() + " - Rs " + String.format("%.2f", calculateCartTotalWithGST()));

        // clear cart after order
        cart.clear();
    }

    private static CartItem findCartItemByProduct(Product p) {
        for (CartItem ci : cart) {
            if (ci.getProduct().getProductName().equals(p.getProductName())) return ci;
        }
        return null;
    }

    private static void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No orders placed yet.");
            return;
        }
        System.out.println("\n--- Order History ---");
        for (String s : orderHistory) {
            System.out.println(s);
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double calculateCartTotalWithGST() {
        double subtotal = cart.stream().mapToDouble(CartItem::itemSubtotal).sum();
        double gst = cart.stream().mapToDouble(CartItem::itemGST).sum();
        return subtotal + gst;
    }


    private static int getLastOrderId() {
        try {
            java.lang.reflect.Field f = Order.class.getDeclaredField("orderCounter");
            f.setAccessible(true);
            return f.getInt(null);
        } catch (Exception e) {
            return -1;
        }
    }
}
