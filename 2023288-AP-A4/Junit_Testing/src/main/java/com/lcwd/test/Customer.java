package com.lcwd.test;

import java.util.*;

public class Customer implements User {
    private String name;
    private String password;
    private String email;
    protected ArrayList<CartItem> cart;
    private ArrayList<Order> orderHistory;
    private Boolean isVIP;

    public Customer(String name,String email,String password) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.isVIP=false;
        this.cart=new ArrayList<>();
        this.orderHistory=new ArrayList<>();
    }

    @Override
    public void Display_Options(){
        while(true){
            System.out.println("Welcome Customer!");
            System.out.println("1. Browse Menu");
            System.out.println("2. Cart Operations");
            System.out.println("3. Order tracking");
            System.out.println("4. Item Reviews");
            System.out.println("5. Pay to be a VIP");
            System.out.println("To exit,Enter any other choice");
            System.out.println();
            System.out.println("Enter your choice:");
            int c=Main.scanner.nextInt();
            Main.scanner.nextLine();
            System.out.println();
            if(c==1){
                while(true) {
                    System.out.println("1. View All Items in Menu.");
                    System.out.println("2. Search Items by Name.");
                    System.out.println("3. Filter Items based on Category");
                    System.out.println("4. Sort Items by Price");
                    System.out.println("To go back,Enter any other choice");
                    System.out.println();
                    System.out.println("Enter your choice:");
                    int ch = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    System.out.println();
                    if (ch == 1) {
                        viewAllitems();
                    } else if (ch == 2) {
                        searchItemsbyName();
                    } else if (ch == 3) {
                        filterItemsbyCategory();
                    } else if (ch == 4) {
                        sortItemsbyPrice();
                    }else {
                        System.out.println("Returning to main menu");
                        System.out.println();
                        break;
                    }
                    System.out.println();
                }
            }
            else if(c==2){
                while(true) {
                    System.out.println("1. Add items to Cart");
                    System.out.println("2. Modify items in Cart");
                    System.out.println("3. Remove item from Cart");
                    System.out.println("4. View Total in Cart");
                    System.out.println("5. Checkout Items in Cart");
                    System.out.println("To go back,Enter any other choice");
                    System.out.println();
                    System.out.println("Enter your choice:");
                    int ch = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    System.out.println();
                    if (ch == 1) {
                        addItemtoCart();
                    } else if (ch == 2) {
                        modifyCartItem();
                    } else if (ch == 3) {
                        removeItemFromCart();
                    } else if (ch == 4) {
                        viewCartTotal();
                    } else if (ch == 5) {
                        checkoutCart();
                    } else {
                        System.out.println("Returning to main menu");
                        System.out.println();
                        break;
                    }
                    System.out.println();
                }
            }
            else if(c==3){
                while(true) {
                    System.out.println("1. View Order Status");
                    System.out.println("2. Cancel Order");
                    System.out.println("3. View Order History");
                    System.out.println("To go back,Enter any other choice");
                    System.out.println();
                    System.out.println("Enter your choice:");
                    int ch = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    System.out.println();
                    if (ch == 1) {
                        viewOrderStatus();
                    } else if (ch == 2) {
                        cancelOrder();
                    } else if (ch == 3) {
                        viewOrderHistory();
                    } else {
                        System.out.println("Returning to main menu");
                        System.out.println();
                        break;
                    }
                    System.out.println();
                }
            }
            else if(c==4) {
                while (true) {
                    System.out.println("1. Provide Reviews for Items");
                    System.out.println("2. View Reviews for Items");
                    System.out.println("To go back,Enter any other choice");
                    System.out.println();
                    System.out.println("Enter your choice:");
                    int ch = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    System.out.println();
                    if (ch == 1) {
                        provideReview();
                    } else if (ch == 2) {
                        viewItemReviews();
                    } else {
                        System.out.println("Returning to main menu");
                        System.out.println();
                        break;
                    }
                    System.out.println();
                }
            }
            else if (c == 5) {
                System.out.println("Congratulations!You are now a VIP");
                System.out.println();
                isVIP = true;
            }
            else {
                System.out.println("Returning to main menu");
                System.out.println();
                break;
            }
        }
    }

    private void viewAllitems() {
        System.out.println("Displaying all items in the menu:");
        for (FoodItem item : Main.Menu) {
            System.out.println(item);
        }
    }

    private void searchItemsbyName() {
        System.out.println("Enter item name to search:");
        String name = Main.scanner.nextLine();
        for (FoodItem item : Main.Menu) {
            if (item.getName().equalsIgnoreCase(name)) {
                System.out.println(item);
                return;
            }
        }
        System.out.println("Item not found in the menu.");
    }

    private void filterItemsbyCategory() {
        System.out.println("Enter category to filter by:");
        String category = Main.scanner.nextLine();
        for (FoodItem item : Main.Menu) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                System.out.println(item);
            }
        }
    }
    private void sortItemsbyPrice() {
        System.out.println("Sorting items by price in ascending order:");
        Main.Menu.stream()
                .sorted(Comparator.comparingDouble(FoodItem::getPrice))
                .forEach(System.out::println);
    }

    protected void addItemtoCart() {
        System.out.println("Enter the name of the item to add:");
        String itemName = Main.scanner.nextLine();
        FoodItem item = Main.findItemInMenu(itemName);

        if (item != null) {
            if (!item.isAvailable()) {
                System.out.println("Sorry, " + itemName + " is currently unavailable.");
                return;
            }

            System.out.println("Enter quantity:");
            int quantity = Main.scanner.nextInt();
            Main.scanner.nextLine();

            CartItem existingItem = findItemInCart(itemName);
            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                System.out.println("Updated quantity for " + itemName + " to " + existingItem.getQuantity());
            } else {
                cart.add(new CartItem(item, quantity));
                System.out.println("Added " + quantity + " of " + itemName + " to the cart.");
            }
        } else {
            System.out.println("Item not found in the menu.");
        }
    }


    private void modifyCartItem() {
        System.out.println("Enter the name of the item to modify in the cart:");
        String itemName = Main.scanner.nextLine();
        CartItem item = findItemInCart(itemName);

        if (item != null) {
            System.out.println("Enter new quantity:");
            int quantity = Main.scanner.nextInt();
            Main.scanner.nextLine();
            if (quantity > 0) {
                item.setQuantity(quantity);
                System.out.println("Updated quantity for " + itemName + " to " + quantity);
            } else {
                cart.remove(item);
                System.out.println(itemName + " has been removed from the cart as quantity is set to 0.");
            }
        } else {
            System.out.println("Item not found in cart.");
        }
    }


    private void removeItemFromCart() {
        System.out.println("Enter the name of the item to remove from cart:");
        String itemName = Main.scanner.nextLine();
        CartItem item = findItemInCart(itemName);

        if (item != null) {
            cart.remove(item);
            System.out.println(itemName + " has been removed from the cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }


    private void viewCartTotal() {
        double total = cart.stream().mapToDouble(item -> item.getFoodItem().getPrice() * item.getQuantity()).sum();
        System.out.println("Total amount in cart: $" + total);
    }


    private void checkoutCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        ArrayList<CartItem> itemsForOrder = new ArrayList<>(cart);
        double totalAmount = 0.0;

        for (CartItem cartItem : cart) {
            totalAmount += cartItem.getFoodItem().getPrice() * cartItem.getQuantity();
        }

        System.out.print("Do you have any special requests for your order? (Enter Empty if None) ");
        String specialRequest = Main.scanner.nextLine();

        Order newOrder = new Order(Main.generateOrderId(), itemsForOrder, totalAmount);
        newOrder.addSpecialRequest(specialRequest.isEmpty() ? "None" : specialRequest);

        if (this.isVIP) {
            int insertIndex = 0;

            while (insertIndex < Main.pendingOrders.size() && !Main.pendingOrders.get(insertIndex).isVip()) {
                insertIndex++;
            }

            Main.pendingOrders.add(insertIndex, newOrder);
            newOrder.setVip(true);
        } else {
            Main.pendingOrders.add(newOrder);
        }

        Main.Orders.add(newOrder);
        cart.clear();
        orderHistory.add(newOrder);
        System.out.println("Order has been placed. Thank you!");
        System.out.println("The Order Id is: " + newOrder.getOrderId());
    }

    public CartItem findItemInCart(String itemName) {
        for (CartItem item : cart) {
            if (item.getFoodItem().getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    private void viewOrderStatus() {
        System.out.println("Enter Order ID to view status:");
        int orderId = Main.scanner.nextInt();
        Main.scanner.nextLine();

        Order order = Main.findOrderById(orderId);
        if (order != null) {
            System.out.println("Order Status: " + order.getStatus());
        } else {
            System.out.println("Order not found.");
        }
    }

    private void cancelOrder() {
        System.out.println("Enter Order ID to cancel:");
        int orderId = Main.scanner.nextInt();
        Main.scanner.nextLine();

        Order order = Main.findOrderById(orderId);

        if (order != null && order.getStatus().equals("Pending")) {
            order.setStatus("Canceled");

            Main.pendingOrders.remove(order);

            System.out.println("Order has been canceled and removed from pending orders.");
        } else if (order != null) {
            System.out.println("Order cannot be canceled as it is already " + order.getStatus());
        } else {
            System.out.println("Order not found.");
        }
    }


    private void viewOrderHistory() {
        System.out.println("Order History:");
        if (orderHistory.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orderHistory) {
                System.out.println(order);
            }
        }
    }

    private void provideReview() {
        System.out.println("Enter the name of the item to review:");
        String itemName = Main.scanner.nextLine();

        FoodItem item = Main.findItemInMenu(itemName);
        if (item != null) {
            System.out.println("Enter your rating (1-5):");
            int rating = Main.scanner.nextInt();
            Main.scanner.nextLine();
            System.out.println("Enter your review:");
            String reviewText = Main.scanner.nextLine();

            Review review = new Review(rating, reviewText, this.name);
            item.addReview(review);
            System.out.println("Review added for " + itemName);
        } else {
            System.out.println("Item not found in menu.");
        }
    }

    private void viewItemReviews() {
        System.out.println("Enter the name of the item to view reviews:");
        String itemName = Main.scanner.nextLine();

        FoodItem item = Main.findItemInMenu(itemName);
        if (item != null) {
            System.out.println("Reviews for " + itemName + ":");
            List<Review> reviews = item.getReviews();
            if (reviews.isEmpty()) {
                System.out.println("No reviews found for this item.");
            } else {
                for (Review review : reviews) {
                    System.out.println(review);
                }
            }
        } else {
            System.out.println("Item not found in menu.");
        }
    }

    //Getters and Setters
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public ArrayList<CartItem> getCart() {
        return cart;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
