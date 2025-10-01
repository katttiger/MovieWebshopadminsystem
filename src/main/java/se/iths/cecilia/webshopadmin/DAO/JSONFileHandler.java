package se.iths.cecilia.webshopadmin.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.cecilia.webshopadmin.models.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONFileHandler {

    private ObjectMapper mapper;
    private String filePath = "products.json";
    List<Product> products;

    public JSONFileHandler() {
        this.mapper = new ObjectMapper();
        this.products = new ArrayList<>();
    }

    public String retrieveItemFromJsonFile(int articlenumber) {
        String product = null;
        Path path = Paths.get(filePath);
        try {
            File file = new File(path.toString());
            Product[] productArray = mapper.readValue(file, Product[].class);
            products = Arrays.stream(productArray).toList();
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
        return product;
    }

    public List<String> retrieveAllItemsInJsonFile() {
        List<String> items = new ArrayList<>();
        try {
            mapper.readValue(filePath, Product.class);
            System.out.println("All is well");

        } catch (IOException e) {
            System.out.println("The stream is wrong: " + e.getMessage()
                    + "\n" + e.getStackTrace().toString());
        } catch (NoClassDefFoundError e) {
            System.out.println("I could not find the proper class at "
                    + e.getMessage() + "\n"
                    + e.getStackTrace().toString());
        } catch (NullPointerException e) {
            System.out.println("The list is null or empty: "
                    + e.getMessage() + "\n"
                    + e.getStackTrace().toString());
        }
        return items;
    }

    public void fileWriter(Product product) {
        try {
            mapper.writeValue(new File(filePath), product);
            System.out.println("Product has been written to " + filePath);
        } catch (IOException e) {
            System.out.println("Something went wrong when adding your product.");
            System.out.println(e.getMessage());
        }
    }

    public void checkThatFileExists() {
        try {
            Path file = Path.of(filePath);
            if (!Files.exists(file)) {
                Files.createDirectories(file.getParent());
            }
            Files.createFile(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    * public void addNewProductToJsonFile(Product product) {
        try {
            String jsonItem = mapper.writeValueAsString(product);
            mapper.writeValue(new File("products.json"), jsonItem);

        } catch (Exception e) {

        }
    }
    *
    *  public void createJsonFile() {
        File jsonFile = new File("products.json");
        try {
            if (!jsonFile.exists()) {
                Files.createDirectories(jsonFile.getParentFile().toPath());
            }
            jsonFile.createNewFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }*/

}
