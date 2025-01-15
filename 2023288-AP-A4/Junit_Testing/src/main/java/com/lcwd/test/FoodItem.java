package com.lcwd.test;

import java.util.ArrayList;

public class FoodItem {
    private String name;
    private double price;
    private String category;
    private boolean available;
    private ArrayList<Review> reviews;

    public FoodItem(String name, double price, String category, boolean available) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
        this.reviews = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void displayItem() {
        System.out.printf("Name: %s | Price: %.2f | Category: %s | Available: %s%n",
                name, price, category, available ? "Yes" : "No");
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f - %s - %s", name, price, category, available ? "Available" : "Unavailable");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FoodItem foodItem = (FoodItem) obj;
        return name.equals(foodItem.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
