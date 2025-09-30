package se.iths.cecilia.webshopadmin.DAO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.cecilia.webshopadmin.models.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JSONFileHandler {

    private ObjectMapper mapper;
    final private String filePath = "data/products.json";

    public JSONFileHandler() {
        this.mapper = new ObjectMapper();
    }

    public String retrieveItemFromJsonFile(int articlenumber) {
        String product = null;
        try {
            product = mapper.readValue(filePath, Product.class).toString();
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
        return product;
    }

    public List<Product> retrieveAllItemsInJsonFile() {
        List<Product> products = null;
        try {
            products = mapper.readValue(filePath, new TypeReference<List<Product>>() {
            });
        } catch (IOException | NullPointerException e) {
            System.out.println("The stream is wrong or the list is null.");
        } catch (NoClassDefFoundError e) {
            System.out.println("I could not find the proper class.");
        }
        return products;
    }

    public void fileWriter(Product product) {
        try {
            mapper.writeValue(new File(filePath), product);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage().toString());
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
