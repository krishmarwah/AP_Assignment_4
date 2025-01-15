package com.lcwd.test;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private String status;
    private ArrayList<CartItem> items;
    private double totalAmount;
    private String specialRequest;
    private boolean isVip;

    public Order(int orderId, ArrayList<CartItem> items, double totalAmount) {
        this.orderId = orderId;
        this.status = "Pending";
        this.items = items;
        this.totalAmount = totalAmount;
        this.specialRequest = "None";
        this.isVip = false;
    }

    public boolean containsItem(FoodItem item) {
        for (CartItem cartItem : items) {
            if (cartItem.getFoodItem().equals(item)) {
                return true;
            }
        }
        return false;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void addSpecialRequest(String request) {
        this.specialRequest = request;
    }

    public boolean isVip() {
        return isVip;
    }
    public void setVip(boolean vip) {
        isVip = vip;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public void displayOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Status: " + status);
        System.out.println("Total Amount: $" + totalAmount);
        System.out.println("Items in Order:");

        for (CartItem cartItem : items) {
            FoodItem item = cartItem.getFoodItem();
            System.out.println(" - " + item.getName() + " (Quantity: " + cartItem.getQuantity() + ", Price: $" + item.getPrice() + ")");
        }

        System.out.println("Special Request: " + (specialRequest.isEmpty() ? "None" : specialRequest));
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Status: " + status + ", Total Amount: $" + totalAmount + ", Items: " + items;
    }

    public String toStringforGUI(){
        return orderId + "," + status + "," + totalAmount + "," + items;
    }
}

