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
        loadCustomersFromFile(); // Load customers from file
        loadAdminsFromFile();    // Load admins from file

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
                        saveAdminToFile(newAdmin);  // Save the new admin to file
                        System.out.println("Admin account created successfully! You can now log in.");
                    } else if (choice == 2) {
                        Customer newCustomer = new Customer(name, email, password);
                        Customers.add(newCustomer);
                        saveCustomerToFile(newCustomer); // Save the new customer to file
                        System.out.println("Customer account created successfully! You can now log in.");
                    }
                } else {
                    System.out.println("Going back!");
                }
            } else {
                System.out.println("Exiting Byte-Me. Goodbye!");

                // Save data to files before exiting
                saveMenuToFile();
                savePendingOrdersToFile();
                saveAllCustomersOrderHistoryToFile();
                scanner.close();
                break;
            }
        }
    }

    public static void prepopulate() {
        Admins.add(new Admin("Alice", "alice@byte.com", "admin123"));
        Customers.add(new Customer("Bob", "bob@gmail.com", "bob123"));
        Menu.add(new FoodItem("Burger", 50, "Fast Food", true));
        Menu.add(new FoodItem("Coke", 50, "Beverages", true));
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

    // Save a newly signed-up Customer to file (append mode)
    public static void saveCustomerToFile(Customer customer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Customers.txt", true))) {
            writer.write(customer.getName() + "," + customer.getEmail() + "," + customer.getPassword());
            writer.newLine();
            System.out.println("New customer saved to Customers.txt");
        } catch (IOException e) {
            System.err.println("Error saving customer to file: " + e.getMessage());
        }
    }

    // Save a newly signed-up Admin to file (append mode)
    public static void saveAdminToFile(Admin admin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Admins.txt", true))) {
            writer.write(admin.getName() + "," + admin.getEmail() + "," + admin.getPassword());
            writer.newLine();
            System.out.println("New admin saved to Admins.txt");
        } catch (IOException e) {
            System.err.println("Error saving admin to file: " + e.getMessage());
        }
    }

    // Load Customers from file
    public static void loadCustomersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    Customers.add(new Customer(data[0], data[1], data[2]));
                }
            }
            System.out.println("Customers loaded from Customers.txt");
        } catch (IOException e) {
            System.err.println("Error loading customers from file: " + e.getMessage());
        }
    }

    // Load Admins from file
    public static void loadAdminsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Admins.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    Admins.add(new Admin(data[0], data[1], data[2]));
                }
            }
            System.out.println("Admins loaded from Admins.txt");
        } catch (IOException e) {
            System.err.println("Error loading admins from file: " + e.getMessage());
        }
    }
    public static void saveAllCustomersOrderHistoryToFile() {
        String fileName = "orderHistory.txt";

        // Clear the file content before appending
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(""); // Overwrites and clears the file
        } catch (IOException e) {
            System.out.println("Error clearing file: " + e.getMessage());
            return; // Exit if file clearing fails
        }

        // Append order history for each customer
        for (Customer customer : Customers) {
            customer.saveOrderHistoryToFile();
        }
        System.out.println("Customers' order history saved to orderHistory.txt");
    }

}
