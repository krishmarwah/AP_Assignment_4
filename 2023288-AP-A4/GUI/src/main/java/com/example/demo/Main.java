package com.example.demo;

import java.util.Scanner;
import java.util.ArrayList;

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
                    // Login into an existing account
                    System.out.print("Enter your Email Address: ");
                    String email = scanner.next();
                    System.out.print("Enter your password: ");
                    String password = scanner.next();
                    scanner.nextLine();
                    System.out.println();

                    if (choice == 1) {
                        boolean foundAdmin = false;
                        for (Admin admin : Admins) {
                            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                                foundAdmin = true;
                                admin.Display_Options();
                                break;
                            }
                        }
                        if (!foundAdmin) {
                            System.out.println("Invalid email or password. Try again.");
                        }
                    } else if (choice == 2) {
                        boolean foundCustomer = false;
                        for (Customer customer : Customers) {
                            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                                foundCustomer = true;
                                customer.Display_Options();
                                break;
                            }
                        }
                        if (!foundCustomer) {
                            System.out.println("Invalid email or password. Try again.");
                            System.out.println();
                        }
                    }

                } else if (c == 2) {
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.next();
                    System.out.print("Enter your password: ");
                    String password = scanner.next();
                    scanner.nextLine();

                    if (choice == 1) {
                        Admin newAdmin = new Admin(name, email, password);
                        Admins.add(newAdmin);
                        System.out.println("Admin account created successfully! You can now log in.");
                    } else if (choice == 2) {
                        Customer newCustomer = new Customer(name, email, password);
                        Customers.add(newCustomer);
                        System.out.println("Customer account created successfully! You can now log in.");
                    }
                } else {
                    System.out.println("Going back!");
                }
            } else {
                System.out.println("Exiting Byte-Me. Goodbye!");
                scanner.close();
                break;
            }
        }
    }

    public static void prepopulate(){
        Admins.add(new Admin("Alice","alice@byte.com","admin123"));
        Customers.add(new Customer("Bob","bob@gmail.com","bob123"));
        Menu.add(new FoodItem("Burger",50,"Fast Food",true));
        Menu.add(new FoodItem("Coke",50,"Beverages",true));
    }
}

