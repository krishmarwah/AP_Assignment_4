Byte Me! - CLI-Based Food Ordering System

Overview:
Byte Me! is a command-line interface (CLI) based food ordering system designed for a college canteen. The system allows users to manage food items, place orders, and manage customer accounts. It supports two types of users: Administrators and Customers, each with distinct functionalities.

Features:

For Administrators:
Log in to manage the food menu.
Add new food items to the menu.
Update existing menu items.
Remove items from the menu.
Generate reports for order management.


For Customers:
Log in to place orders for food items.
Browse the menu to view available food items.
Add items to the cart and place orders.
Track orders and provide reviews for items.

Prepopulated Data
The system comes preloaded with sample data:

One Admin account:
Email: alice@byte.com, Password: admin123
One Customer account:
Email: bob@gmail.com, Password: bob123
Menu items:
Burger: $50 (Available)
Coke: $50 (Available)

Few Points to keep in Mind:
1. Array List is the only Collection used throughout the assignment,even for placing Orders.
2. Whenever faced with a higher priority order,the order is added to the very front of the ArrayList,thus the stisfying the logic for the bonus VIP.
   In a case of an existing VIP order,the newer VIP order is added after exisiting VIP orders.
   (A thing like Priority Queue could have been also used but the instructions allow us to use Collections based upon our wish)
3. A Hash Map<String,Integer> has been used to see the popularities in generate_reports function.
4. Orders are hanled based on their generated Order IDs.
5. Sales reprt is generated based upon the entirety of the Program execution,keeping in mind Program runs newly on each day.
6. Order Statuses are kept as "Pending","Completed","Canceled","Denied".
7. Bonuses have been done.

Efforts by:
Krish Marwah
2023288

