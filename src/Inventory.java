import model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Inventory {
    private List<Product> productList;

    public Inventory() {
        productList = new ArrayList<>();
    }

    public void loadInventory(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String category = parts[2];
                double price = Double.parseDouble(parts[3]);
                int quantity = Integer.parseInt(parts[4]);
                Product product = new Product(id, name, category, price, quantity);
                productList.add(product);
            }
        }
    }

    public ArrayList<Product> searchProduct(String keyword, String category) {
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : productList) {
            if ((product.getName().contains(keyword) || Integer.toString(product.getId()).contains(keyword))
                    && product.getCategory().equals(category)) {
                results.add(product);
            }
        }
        return results;
    }

    public void sortInventory(String sortBy) {
        switch (sortBy.toLowerCase()) {
            case "name":
                productList.sort(Comparator.comparing(Product::getName));
                break;
            case "price":
                productList.sort(Comparator.comparing(Product::getPrice));
                break;
            case "quantity":
                productList.sort(Comparator.comparing(Product::getQuantity));
                break;
            default:
                System.out.println("Invalid sort option.");
                break;
        }
    }

    // Getter method for productList

    public List<Product> getProductList() {
        return productList;
    }

}
