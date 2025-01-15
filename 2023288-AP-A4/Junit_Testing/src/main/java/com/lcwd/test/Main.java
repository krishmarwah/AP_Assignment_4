package com.lcwd.test;

import java.io.*;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Admin> Admins = new ArrayList<>();
    static ArrayList<Customer> Customers = new ArrayList<>();
    static ArrayList<Order> Orders = new ArrayList<>();
    static ArrayList<FoodItem> Menu = new ArrayList<>();
    static ArrayList<Order> pendingOrders = new ArrayList<>();
    static ArrayList<Order> completedOrders = new ArrayList<>();
    static int orderCounter = 1;

    public static Order findOrderById(int orderId) {
        for (Order order : Orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public static int generateOrderId() {
        return orderCounter++;
    }

    public static FoodItem findItemInMenu(String name) {
        for (FoodItem item : Menu) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        prepopulate();

        while (true) {
            System.out.println("-----Byte Me----- ");
            System.out.println("Login as:");
            System.out.println("1. Administrator");
            System.out.println("2. Customer");
            System.out.println("To exit, enter any other choice.");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            if (choice == 1 || choice == 2) {
                System.out.println("1. Log into an existing account:");
                System.out.println("2. Sign up for a new account:");
                System.out.println("To go back, enter any other choice.");
                System.out.println();
                System.out.print("Enter your choice: ");
                int c = scanner.nextInt();
                scanner.nextLine();
                System.out.println();

                if (c == 1) {
                    System.out.print("Enter your Email Address: ");
                    String email = scanner.next();
                    System.out.print("Enter your password: ");
                    String password = scanner.next();
                    scanner.nextLine();
                    System.out.println();

                    if (!logIn(email, password, choice)) {
                        System.out.println("Invalid email or password. Try again.");
                    }
                } else if (c == 2) {
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.next();
                    System.out.print("Enter your password: ");
                    String password = scanner.next();
                    scanner.nextLine();

                    if (signUp(name, email, password, choice)) {
                        System.out.println("Account created successfully! You can now log in.");
                    } else {
                        System.out.println("Sign-up failed. Try again.");
                    }
                } else {
                    System.out.println("Going back!");
                }
            } else {
                System.out.println("Exiting Byte-Me. Goodbye!");

                // Save data to files before exiting
                saveMenuToFile();
                savePendingOrdersToFile();

                scanner.close();
                break;
            }
        }
    }

    public static boolean signUp(String name, String email, String password, int choice) {
        if (choice == 1) {
            Admin newAdmin = new Admin(name, email, password);
            Admins.add(newAdmin);
            return true;
        } else if (choice == 2) {
            Customer newCustomer = new Customer(name, email, password);
            Customers.add(newCustomer);
            return true;
        }
        return false;
    }

    public static boolean logIn(String email, String password, int choice) {
        if (choice == 1) {
            for (Admin admin : Admins) {
                if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                    admin.Display_Options();
                    return true;
                }
            }
        } else if (choice == 2) {
            for (Customer customer : Customers) {
                if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                    customer.Display_Options();
                    return true;
                }
            }
        }
        return false;
    }

    public static void prepopulate() {
        if (Admins.isEmpty()) {
            Admins.add(new Admin("Alice", "alice@byte.com", "admin123"));
        }
        if (Customers.isEmpty()) {
            Customers.add(new Customer("Bob", "bob@gmail.com", "bob123"));
        }
        if (Menu.isEmpty()) {
            Menu.add(new FoodItem("Burger", 50, "Fast Food", true));
            Menu.add(new FoodItem("Coke", 50, "Beverages", true));
        }
    }

    // Save Menu to file
    public static void saveMenuToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu.txt"))) {
            for (FoodItem item : Menu) {
                writer.write(item.getName() + "," + item.getPrice() + "," + item.getCategory() + "," + (item.isAvailable() ? "Yes" : "No"));
                writer.newLine();
            }
            System.out.println("Menu saved to menu.txt");
        } catch (IOException e) {
            System.err.println("Error saving menu to file: " + e.getMessage());
        }
    }

    // Save Pending Orders to file
    public static void savePendingOrdersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pending_orders.txt"))) {
            for (Order order : pendingOrders) {
                writer.write(order.toStringforGUI());
                writer.newLine();
            }
            System.out.println("Pending orders saved to pending_orders.txt");
        } catch (IOException e) {
            System.err.println("Error saving pending orders to file: " + e.getMessage());
        }
    }
}
