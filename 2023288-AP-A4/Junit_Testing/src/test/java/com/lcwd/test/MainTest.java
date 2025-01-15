package com.lcwd.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MainTest {
    @BeforeAll
    public static void setUp() {
        // Prepopulate the data
        Main.Admins.add(new Admin("Alice", "alice@byte.com", "admin123"));
        Main.Customers.add(new Customer("Bob", "bob@gmail.com", "bob123"));
    }

    @Test
    public void testInvalidLoginAdmin_WrongEmail() {
        boolean result = Main.logIn("invalid@byte.com", "admin123", 1); // Invalid email for Admin
        assertFalse(result, "Admin login should fail with incorrect email");
    }

    @Test
    public void testInvalidLoginAdmin_WrongPassword() {
        boolean result = Main.logIn("alice@byte.com", "wrongpass", 1); // Invalid password for Admin
        assertFalse(result, "Admin login should fail with incorrect password");
    }

    @Test
    public void testInvalidLoginCustomer_WrongEmail() {
        boolean result = Main.logIn("wrong@gmail.com", "bob123", 2); // Invalid email for Customer
        assertFalse(result, "Customer login should fail with incorrect email");
    }

    @Test
    public void testInvalidLoginCustomer_WrongPassword() {
        boolean result = Main.logIn("bob@gmail.com", "wrongpass", 2); // Invalid password for Customer
        assertFalse(result, "Customer login should fail with incorrect password");
    }
}
