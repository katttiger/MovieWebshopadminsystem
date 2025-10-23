package se.iths.cecilia.webshopadmin.DAO.filehandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.cecilia.webshopadmin.models.Candy;
import se.iths.cecilia.webshopadmin.models.Movie;
import se.iths.cecilia.webshopadmin.models.Product;
import se.iths.cecilia.webshopadmin.models.StuffedAnimal;
import se.iths.cecilia.webshopadmin.view.UI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONFileHandler {
    private static JSONFileHandler instance;
    private ObjectMapper mapper;
    private List<Product> productList;
    private final File file;

    public JSONFileHandler() {
        this.mapper = new ObjectMapper();
        this.productList = new ArrayList<>();
        String filePath = "products.json";
        this.file = new File(filePath);
    }

    public static JSONFileHandler getInstance() {
        if (instance == null) {
            instance = new JSONFileHandler();
        }
        return instance;
    }

    public String retrieveItemFromJsonFile(int articlenumber) {
        String productRetrieved = "List is empty";
        try {
            productList = retrieveAllItemsInJsonFile();
            if (!productList.isEmpty()) {
                for (int i = 0; i < productList.toArray().length; i++) {
                    if (productList.get(i).getArticleNumber() == articlenumber) {
                        productRetrieved = productList.get(i).toString();
                    }
                }
            }
            productList.clear();
        } catch (NullPointerException e) {
            return "Product with articlenumber " + articlenumber + " does not exist.";
        }
        return productRetrieved;
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
            } else if (product instanceof StuffedAnimal) {
                productList.add((StuffedAnimal) product);
            } else {
                throw new IOException();
            }
            UI.info("Product has been added.");
            mapper.writeValue(file, productList);
            productList.clear();

        } catch (IOException e) {
            System.out.println("Could not save product to JSON file: ");
        }
    }
}
