package com.lcwd.test;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class CustomerTest {

    private Customer customer;
    private FoodItem outOfStockItem;
    private FoodItem inStockItem;

    @BeforeEach
    void setUp() {
        // Initialize customer
        customer = new Customer("John Doe", "john.doe@example.com", "password123");

        // Clear the static Menu and cart
        Main.Menu = new ArrayList<>();
        customer.cart = new ArrayList<>();

        // Add out-of-stock and in-stock items to the menu
        outOfStockItem = new FoodItem("Burger", 50,"Fast Food",  false); // Out of stock
        inStockItem = new FoodItem("Pizza",100, "Fast Food",  true); // In stock

        Main.Menu.add(outOfStockItem);
        Main.Menu.add(inStockItem);
    }

    @Test
    void testAddOutOfStockItemToCart() {
        // Simulate adding an out-of-stock item
        Main.scanner = new java.util.Scanner("Burger\n"); // Simulates user entering "Burger"

        customer.addItemtoCart();

        // Verify cart is still empty (item should not be added)
        assertTrue(customer.getCart().isEmpty(), "Cart should remain empty when adding an out-of-stock item.");
    }

    @Test
    void testAddInStockItemToCart() {
        // Simulate adding an in-stock item
        Main.scanner = new java.util.Scanner("Pizza\n2\n"); // Simulates user entering "Pizza" and quantity 2

        customer.addItemtoCart();

        // Verify cart has the item
        assertEquals(1, customer.getCart().size(), "Cart should have one item.");
        CartItem cartItem = customer.getCart().get(0);
        assertEquals(inStockItem, cartItem.getFoodItem(), "Cart should contain the correct item.");
        assertEquals(2, cartItem.getQuantity(), "Cart should have the correct quantity.");
    }

    @Test
    void testUpdateQuantityInCart() {
        // Simulate adding in-stock item to the cart
        Main.scanner = new java.util.Scanner("Pizza\n2\n"); // Add 2 pizzas
        customer.addItemtoCart();

        // Verify cart has the item with correct quantity
        assertEquals(1, customer.getCart().size(), "Cart should have one item.");
        CartItem cartItem = customer.getCart().get(0);
        assertEquals(2, cartItem.getQuantity(), "Cart should have 2 pizzas.");

        // Now add more of the same item (Pizza)
        Main.scanner = new java.util.Scanner("Pizza\n3\n"); // Add 3 more pizzas
        customer.addItemtoCart();

        // Verify quantity is updated to 5
        assertEquals(1, customer.getCart().size(), "Cart should still have one item.");
        cartItem = customer.getCart().get(0);
        assertEquals(5, cartItem.getQuantity(), "Cart should have updated quantity of 5 pizzas.");
    }

    @Test
    void testItemNotFoundInMenu() {
        // Simulate adding an item that is not in the menu
        Main.scanner = new java.util.Scanner("Sushi\n"); // "Sushi" is not in the menu
        customer.addItemtoCart();

        // Verify cart is still empty (item should not be added)
        assertTrue(customer.getCart().isEmpty(), "Cart should remain empty when an item is not found in the menu.");
    }
}
