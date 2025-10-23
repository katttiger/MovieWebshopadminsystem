package se.iths.cecilia.webshopadmin.DAO;

import se.iths.cecilia.webshopadmin.DAO.filehandler.JSONFileHandler;
import se.iths.cecilia.webshopadmin.controller.Errorcheck;
import se.iths.cecilia.webshopadmin.models.Product;
import se.iths.cecilia.webshopadmin.models.factory.CandyFactory;
import se.iths.cecilia.webshopadmin.models.factory.MovieFactory;
import se.iths.cecilia.webshopadmin.models.factory.ProductFactory;
import se.iths.cecilia.webshopadmin.models.factory.StuffedAnimalFactory;
import se.iths.cecilia.webshopadmin.view.UI;

import java.util.List;

public class ProductDAOScanner implements ProductDAOInterface {
    private List<Product> products;
    JSONFileHandler jsonFileHandler;

    public ProductDAOScanner() {
        this.jsonFileHandler = JSONFileHandler.getInstance();
    }

    @Override
    public void addProduct() {
        ProductFactory factory;
        int userInput = -1;
        do {
            UI.info("""
                    What type of product do you want to add?
                    [1] Movie
                    [2] Candy
                    [3] Stuffed animal""");

            userInput = Errorcheck.checkIntegerInput();
            if (userInput < 1 || userInput > 3) {
                UI.info("Input must be between 1 and 3");
            }

            switch (userInput) {
                case 1 -> {
                    factory = new MovieFactory();
                    jsonFileHandler.addNewProductToJsonFile(factory.create());
                }
                case 2 -> {
                    factory = new CandyFactory();
                    jsonFileHandler.addNewProductToJsonFile(factory.create());
                }
                case 3 -> {
                    factory = new StuffedAnimalFactory();
                    jsonFileHandler.addNewProductToJsonFile(factory.create());
                }
            }
        } while (userInput < 1 || userInput > 3);
    }

    @Override
    public void listAllProducts() {
        products = jsonFileHandler.retrieveAllItemsInJsonFile();
        UI.info("Below are our currents products: ");

        for (int i = 0; i < products.toArray().length; i++) {
            UI.info("""                    
                    ------------
                    """ + products.get(i).toString());
        }
        products.clear();
    }

    @Override
    public void searchForProduct() {
        int userInput = -1;
        Product productReturned = null;

        UI.info("Enter the articlenumber of the item you wish to find:");

        do {
            userInput = Errorcheck.checkIntegerInput();
        } while (userInput == -1);

        products = jsonFileHandler.retrieveAllItemsInJsonFile();
        for (int i = 0; i < products.toArray().length; i++) {
            if (products.get(i).getArticleNumber() == userInput) {
                productReturned = products.get(i);
            }
        }

        if (productReturned != null) {
            UI.info("Your product: \n" + productReturned.toString() + "\n----------");
        } else {
            UI.info("No product with articlenumber " + userInput + " was found.");
        }
        products.clear();
    }
}
