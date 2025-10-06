package se.iths.cecilia.webshopadmin.DAO.filehandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.cecilia.webshopadmin.models.Candy;
import se.iths.cecilia.webshopadmin.models.Movie;
import se.iths.cecilia.webshopadmin.models.Product;
import se.iths.cecilia.webshopadmin.models.StuffedAnimal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONFileHandler {

    private ObjectMapper mapper;
    private final String filePath = "products.json";
    private List<Product> productList;
    File file;
    String mockProduct;

    public JSONFileHandler() {
        this.mapper = new ObjectMapper();
        this.productList = new ArrayList<>();
        this.file = new File(filePath);
    }

    public String retrieveItemFromJsonFile(int articlenumber) {
        productList = retrieveAllItemsInJsonFile();
        if (!productList.isEmpty()) {
            for (int i = 0; i < productList.toArray().length; i++) {
                if (productList.get(i).getArticleNumber() == articlenumber) {
                    mockProduct = productList.get(i).toString();
                }
            }
        } else {
            return "List is empty";
        }
        productList.clear();
        if (mockProduct.equals("null") || mockProduct.isBlank()) {
            return "Product with articlenumber " + articlenumber + " does not exist.";
        }
        return mockProduct;
    }

    public List<Product> retrieveAllItemsInJsonFile() {
        try {
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
}
