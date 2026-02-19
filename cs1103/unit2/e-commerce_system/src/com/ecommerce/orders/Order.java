package com.ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;

import java.util.List;

public class Order {

    private String orderID;
    private Customer customer;
    private List<Product> products;
    private double orderTotal;
    private String status;

    public Order(String orderID, Customer customer, List<Product> products) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Cannot place an order with empty cart.");
        }

        this.orderID = orderID;
        this.customer = customer;
        this.products = products;
        this.orderTotal = calculateTotal();
        this.status = "Pending";
    }

    private double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void updateStatus(String newStatus) {
        if (newStatus == null || newStatus.isEmpty()) {
            throw new IllegalArgumentException("Invalid order status.");
        }
        this.status = newStatus;
    }

    public void generateOrderSummary() {
        System.out.println("Order ID: " + orderID);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Products:");
        for (Product product : products) {
            System.out.println("- " + product.getName() + " ($" + product.getPrice() + ")");
        }
        System.out.println("Total: $" + orderTotal);
        System.out.println("Status: " + status);
        System.out.println("=======================");
    }
}