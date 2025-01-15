package com.example.demo;

import java.util.*;

public class Admin implements User {
    private String name;
    private String password;
    private String email;

    public Admin(String Name,String email, String password){
        this.name=Name;
        this.email=email;
        this.password=password;
    }

    @Override
    public void Display_Options(){
        while(true) {
            System.out.println("Welcome Administrator");
            System.out.println("1.Menu Management");
            System.out.println("2.Order Management");
            System.out.println("3.Report Generation");
            System.out.println("To Exit,enter any other choice.");
            System.out.println();
            System.out.println("Enter your choice:");
            int c = Main.scanner.nextInt();
            Main.scanner.nextLine();
            System.out.println();
            if (c == 1) {
                while(true) {
                    System.out.println("1.Add new Item");
                    System.out.println("2.Update Item");
                    System.out.println("3.Remove Item");
                    System.out.println("To Go back,enter any other choice");
                    int ch = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    if (ch == 1) {
                        add_new_Item();
                    } else if (ch == 2) {
                        update_Item();
                    } else if (ch == 3) {
                        remove_Item();
                    } else {
                        System.out.println();
                        break;
                    }
                    System.out.println();
                }
            } else if (c == 2) {
                while(true) {
                    System.out.println("1.View Pending Orders");
                    System.out.println("2.Update Order Status");
                    System.out.println("3.Process Refunds");
                    System.out.println("4.Handle Special Requests");
                    System.out.println("To Go back,enter any other choice");
                    System.out.println();
                    System.out.println("Enter your choice:");
                    int ch = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    if (ch == 1) {
                        view_pending_orders();
                    } else if (ch == 2) {
                        update_Order_Status();
                    } else if (ch == 3) {
                        process_refunds();
                    } else if (ch == 4) {
                        handle_special_requests();
                    } else {
                        System.out.println();
                        break;
                    }
                    System.out.println();
                }
            } else if (c == 3) {
                while (true) {
                    System.out.println("1.Generate Daily Sales Report");
                    System.out.println("To Go back,enter any other choice");
                    int ch = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    if (ch == 1) {
                        generate_report();
                    } else {
                        System.out.println();
                        break;
                    }
                    System.out.println();
                }
            }
            else{
                System.out.println("Logging out!");
                System.out.println();
                break;
            }
        }
    }

    public void add_new_Item() {
        System.out.println("Enter item name:");
        String name = Main.scanner.nextLine();
        System.out.println("Enter item price:");
        double price = Main.scanner.nextDouble();
        Main.scanner.nextLine();  // Clear buffer
        System.out.println("Enter item category:");
        String category = Main.scanner.nextLine();
        System.out.println("Is item available (true/false):");
        boolean available = Main.scanner.nextBoolean();
        Main.scanner.nextLine();

        // Check if item already exists in the menu
        for (FoodItem item : Main.Menu) {
            if (item.getName().equalsIgnoreCase(name) && item.getCategory().equalsIgnoreCase(category)) {
                System.out.println("Item already exists in the menu.");
                return;
            }
        }

        FoodItem newItem = new FoodItem(name, price, category, available);
        Main.Menu.add(newItem);
        System.out.println("Item added successfully!");
    }

    public void update_Item() {
        System.out.println("Enter item name to update:");
        String name = Main.scanner.nextLine();
        FoodItem itemToUpdate = null;

        for (FoodItem foodItem : Main.Menu) {
            if (foodItem.getName().equalsIgnoreCase(name)) {
                itemToUpdate = foodItem;
                break;
            }
        }

        if (itemToUpdate != null) {
            System.out.println("Current details of the item:");
            itemToUpdate.displayItem();

            System.out.println("Enter new name (or press Enter to keep current name):");
            String newName = Main.scanner.nextLine();
            if (!newName.isEmpty()) {
                itemToUpdate.setName(newName);
            }

            System.out.println("Enter new price (or press Enter to keep current price):");
            String priceInput = Main.scanner.nextLine();
            if (!priceInput.isEmpty()) {
                try {
                    double newPrice = Double.parseDouble(priceInput);
                    itemToUpdate.setPrice(newPrice);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price input. Keeping the current price.");
                }
            }

            System.out.println("Enter new category (or press Enter to keep current category):");
            String newCategory = Main.scanner.nextLine();
            if (!newCategory.isEmpty()) {
                itemToUpdate.setCategory(newCategory);
            }

            System.out.println("Is the item available? (true/false or press Enter to keep current status):");
            String availabilityInput = Main.scanner.nextLine();
            if (!availabilityInput.isEmpty()) {
                itemToUpdate.setAvailable(Boolean.parseBoolean(availabilityInput));
            }

            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Item not found in the menu.");
        }
    }


    public void remove_Item() {
        System.out.println("Enter the name of the item to remove:");
        String name = Main.scanner.nextLine();
        FoodItem itemToRemove = null;

        for (FoodItem foodItem : Main.Menu) {
            if (foodItem.getName().equalsIgnoreCase(name)) {
                itemToRemove = foodItem;
                break;
            }
        }

        if (itemToRemove != null) {
            Main.Menu.remove(itemToRemove);
            System.out.println("Item '" + itemToRemove.getName() + "' has been removed from the menu.");

            for (Order order : Main.pendingOrders) {
                if (order.containsItem(itemToRemove)) {
                    order.setStatus("Denied");
                }
            }
            System.out.println("All pending orders containing this item have been updated to 'Denied'.");
        } else {
            System.out.println("Item not found in the menu.");
        }
    }


    public void view_pending_orders() {
        if (Main.pendingOrders.isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }

        System.out.println("Pending Orders:");
        for (Order order : Main.pendingOrders) {
            order.displayOrderDetails();
        }
    }


    public void update_Order_Status() {
        System.out.println("Enter the ID of the order to update:");
        int orderId = Main.scanner.nextInt();
        Main.scanner.nextLine();

        Order orderToUpdate = null;
        for (Order order : Main.pendingOrders) {
            if (order.getOrderId() == orderId) {
                orderToUpdate = order;
                break;
            }
        }

        if (orderToUpdate != null) {
            System.out.println("Current status: " + orderToUpdate.getStatus());
            System.out.println("Enter new status (e.g., 'Completed', 'Canceled'):");
            String newStatus = Main.scanner.nextLine();
            orderToUpdate.setStatus(newStatus);
            System.out.println("Order status updated successfully.");
        } else {
            System.out.println("Order not found.");
        }
    }

    public void process_refunds() {
        System.out.println("Enter the ID of the order to process a refund for:");
        int orderId = Main.scanner.nextInt();
        Main.scanner.nextLine();

        Order orderToRefund = null;
        for (Order order : Main.Orders) {
            if (order.getOrderId() == orderId) {
                orderToRefund = order;
                break;
            }
        }
        if (orderToRefund != null &&
                (orderToRefund.getStatus().equalsIgnoreCase("Canceled") ||
                        orderToRefund.getStatus().equalsIgnoreCase("Denied"))) {
            System.out.println("Processing refund for Order ID: " + orderId);
            System.out.println("Refund processed successfully.");
        } else {
            System.out.println("Order not found or not eligible for a refund.");
        }
    }

    public void handle_special_requests() {
        System.out.println("Enter the ID of the order with a special request:");
        int orderId = Main.scanner.nextInt();
        Main.scanner.nextLine();

        Order orderWithRequest = null;
        for (Order order : Main.pendingOrders) {
            if (order.getOrderId() == orderId) {
                orderWithRequest = order;
                break;
            }
        }

        if (orderWithRequest != null) {
            System.out.println("Special Request for Order ID " + orderId + ":");
            orderWithRequest.displayOrderDetails();
        } else {
            System.out.println("Order not found.");
        }
    }


    public void generate_report() {
        int totalOrders = Main.Orders.size();
        int completedOrders = 0;
        int pendingOrders = 0;
        double totalSales = 0.0;
        Map<String, Integer> itemPopularity = new HashMap<>();

        for (Order order : Main.Orders) {
            if (order.getStatus().equalsIgnoreCase("Completed")) {
                completedOrders++;
                totalSales += order.getTotalAmount(); // Assuming there's a method to get total amount
            } else if (order.getStatus().equalsIgnoreCase("Pending")) {
                pendingOrders++;
            }

            for (CartItem cartItem : order.getItems()) {
                String itemName = cartItem.getFoodItem().getName();
                int quantity = cartItem.getQuantity();
                itemPopularity.put(itemName, itemPopularity.getOrDefault(itemName, 0) + quantity);
            }
        }

        String mostPopularItem = null;
        int maxQuantity = 0;
        for (Map.Entry<String, Integer> entry : itemPopularity.entrySet()) {
            if (entry.getValue() > maxQuantity) {
                mostPopularItem = entry.getKey();
                maxQuantity = entry.getValue();
            }
        }

        System.out.println("Sales Report:");
        System.out.println("Total Orders: " + totalOrders);
        System.out.println("Completed Orders: " + completedOrders);
        System.out.println("Pending Orders: " + pendingOrders);
        System.out.println("Total Sales: $" + totalSales);
        if (mostPopularItem != null) {
            System.out.println("Most Popular Item: " + mostPopularItem + " (Demand for Quantity: " + maxQuantity + ")");
        } else {
            System.out.println("Most Popular Item: N/A");
        }
    }


    //Getters And Setters
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setEmail(String email){
        this.email=email;
    }
}

