package se.iths.cecilia.webshopadmin.DAO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.cecilia.webshopadmin.models.Candy;
import se.iths.cecilia.webshopadmin.models.Movie;
import se.iths.cecilia.webshopadmin.models.Product;
import se.iths.cecilia.webshopadmin.models.StuffedAnimal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JSONFileHandler {

    private ObjectMapper mapper;
    private final String filePath = "products.json";
    private List<Product> productList;
    File file;

    public JSONFileHandler() {
        this.mapper = new ObjectMapper();
        this.productList = new ArrayList<>();
        this.file = new File(filePath);
    }

    public String retrieveItemFromJsonFile(int articlenumber) {
        Product product = null;
        checkThatFileExists();
        productList = retrieveAllItemsInJsonFile();

        for (int i = 0; i < productList.toArray().length; i++) {
            if (productList.get(i).getArticleNumber() == articlenumber) {
                return productList.get(i).toString();
            } else {
                return "Product with articlenumber " + articlenumber + " does not exist";
            }
        }
        productList.clear();
        return product.toString();
    }

    public List<Product> retrieveAllItemsInJsonFile() {
        try {
            checkThatFileExists();
            productList.clear();
            List<Product> loadedList = mapper.readValue(
                    file,
                    new TypeReference<>() {
                    });
            productList.addAll(loadedList);

        } catch (IOException e) {
            System.out.println("The stream is wrong: " + e.getMessage()
                    + "\n" + e.getStackTrace());
        }
        return productList;
    }

    public <T> void addNewProductToJsonFile(T product) {
        try {
            checkThatFileExists();
            productList = retrieveAllItemsInJsonFile();
            if (product instanceof Movie) {
                productList.add((Movie) product);
            } else if (product instanceof Candy) {
                productList.add((Candy) product);
            } else {
                productList.add((StuffedAnimal) product);
            }
            mapper.writeValue(file, productList);
            productList.clear();
        } catch (IOException e) {
            System.out.println("Could not save product to JSON file: " + e.getMessage());
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
