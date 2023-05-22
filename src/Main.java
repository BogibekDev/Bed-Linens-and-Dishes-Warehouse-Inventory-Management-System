import model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // Display the application menu and execute the selected command
        Menu();
    }

    public static void Menu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        // Load the inventory data
        String fileName = "src/resource/products.txt";
        inventory.loadInventory(fileName);

        // Display the application name and developer information
        System.out.println("Welcome to Bed Linens and Dishes Warehouse Inventory Management System");
        System.out.println("Developed by Bogibek Matyaqubov");

        boolean exit = false;
        while (!exit) {
            // Display the available commands
            System.out.println("\nPlease select a command:");
            System.out.println("1. Search for a product");
            System.out.println("2. Display all products");
            System.out.println("3. Exit the application");

            // Read the user's input
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // Search for a product
                    System.out.println("Please enter the keyword:");
                    String keyword = scanner.nextLine();

                    System.out.println("Please enter the category:");
                    String category = scanner.nextLine();

                    ArrayList<Product> results = inventory.searchProduct(keyword, category);

                    if (results.isEmpty()) {
                        System.out.println("No products found.");
                    } else {
                        System.out.println("Matching products:");
                        for (Product product : results) {
                            System.out.println(product.toString());
                        }
                    }
                    break;

                case "2":
                    // Display all products

                    System.out.println("Please select the sorting order:");
                    System.out.println("1. Sort by name");
                    System.out.println("2. Sort by price");
                    System.out.println("3. Sort by quantity");

                    // Read the user's input
                    String orderInput = scanner.nextLine();

                    switch (orderInput) {
                        case "1":
                            inventory.sortInventory("name");
                            break;

                        case "2":
                            inventory.sortInventory("price");
                            break;

                        case "3":
                            inventory.sortInventory("quantity");
                            break;

                        default:
                            System.out.println("Invalid sorting order.");
                            break;
                    }

                    System.out.println("All products:");
                    for (Product product : inventory.getProductList()) {
                        System.out.println(product.toString());
                    }

                    break;

                case "3":
                    // Exit the application
                    exit = true;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
}