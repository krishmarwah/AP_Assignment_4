AP Assignment-4


3 folders have been submitted,1 for each part of the assignment.

1.GUI

GUI Features of BYTE-ME:

Home Screen:

The main entry point of the application.
Provides three options:
View Canteen Menu: Redirects to the menu view screen.
View Pending Orders: Redirects to the pending orders screen.
Exit: Closes the application.

Canteen Menu Screen:

Displays all menu items in a structured table format.
Columns include:
Item Name
Price
Category
Availability
Reads data dynamically from the menu.txt file, ensuring flexibility to update menu items without modifying the code.
Includes a Back to Home button to navigate back to the Home Screen.

Pending Orders Screen:

Displays all pending orders in a table format.
Columns include:
Order ID
Status
Total Amount
Items
Reads data dynamically from the pending_orders.txt file.
Includes a Back to Home button for navigation.


Dynamic Data Handling:

The application reads menu and order data from external text files (menu.txt and pending_orders.txt).
This ensures the application can adapt to changes in the menu or pending orders without requiring code changes.
Responsive and User-Friendly Layout:

Uses JavaFX for creating visually appealing and easy-to-navigate screens.
Simple and clean design with appropriate spacing between elements.
All buttons and tables are centrally aligned for ease of access.


Error Handling:

If menu.txt or pending_orders.txt is not found, the application gracefully starts with an empty list and logs a message to the console.








2.I/O Stream Management 

The Byte Me! food ordering system uses efficient I/O stream handling to ensure smooth data storage and retrieval. Here's a summary of how I/O operations were implemented:

Data Persistence:

Files Used:
Customers.txt for storing customer details.
Admins.txt for storing admin details.
orderHistory.txt for saving customers' order history.
Techniques:
Data is written using BufferedWriter and FileWriter in append mode for incremental updates.
The BufferedReader is used to load data line by line into memory, splitting each record using delimiters (,) for processing.
Order History Management:

Customers' order histories include order IDs, item details (food name, quantity, price), total price, and special requests.
Before saving, the orderHistory.txt file is cleared, and updated records for all customers are appended.
Error Handling:

I/O operations are wrapped in try-catch blocks to handle issues like missing files or read/write errors gracefully.

3.JUnit Testing

JUnit testing was implemented to validate key functionalities of the Byte Me! food ordering system:

@Beforeall @Test were 2 annotations used heavily.

Customer Cart Functionality:

Verified that out-of-stock items are not added to the cart.
Ensured in-stock items are added with correct quantity.
Confirmed that re-adding the same item updates the total quantity instead of creating duplicates.
Tested that items not in the menu are ignored.
Login Validation:

Tested invalid Admin login scenarios (wrong email/password).
Verified invalid Customer login scenarios to ensure proper authentication.

Efforts by
Krish Marwah
2023288

