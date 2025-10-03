package se.iths.cecilia.webshopadmin.DAO;

import se.iths.cecilia.webshopadmin.DAO.factory.CandyFactory;
import se.iths.cecilia.webshopadmin.DAO.factory.MovieFactory;
import se.iths.cecilia.webshopadmin.DAO.factory.ProductFactory;
import se.iths.cecilia.webshopadmin.DAO.factory.StuffedAnimalFactory;
import se.iths.cecilia.webshopadmin.DAO.filehandler.JSONFileHandler;
import se.iths.cecilia.webshopadmin.controller.Errorcheck;
import se.iths.cecilia.webshopadmin.models.Product;

import java.util.List;
import java.util.Scanner;

public class ProductDAOScanner implements ProductDAOInterface {
    Scanner sc = new Scanner(System.in);
    private List<Product> products;
    Errorcheck errorcheck;
    JSONFileHandler jsonFileHandler;

    public ProductDAOScanner() {
        this.errorcheck = new Errorcheck();
        this.jsonFileHandler = new JSONFileHandler();
    }

    @Override
    public void addProduct() {
        ProductFactory factory;
        int userInput = -1;
        do {
            System.out.println("""
                    What type of product do you want to add?
                    [1] Movie
                    [2] Candy
                    [3] Stuffed animal""");

            userInput = errorcheck.checkIntegerInput();
            if (userInput < 1 || userInput > 3) {
                System.out.println("Input must be between 1 and 3");
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
        System.out.println("Product has been added.");
    }

    @Override
    public void listAllProducts() {
        products = jsonFileHandler.retrieveAllItemsInJsonFile();
        System.out.println("Below are our currents products: ");

        for (int i = 0; i < products.toArray().length; i++) {
            System.out.println("""                    
                    ------------
                    """ + products.get(i).toString());
        }
        products.clear();
    }

    @Override
    public void searchForProduct() {
        int userInput = -1;
        Product productReturned = null;

        System.out.println("Enter the articlenumber of the item you wish to find:");
        do {
            userInput = errorcheck.checkIntegerInput();
        } while (userInput == -1);

        products = jsonFileHandler.retrieveAllItemsInJsonFile();
        for (int i = 0; i < products.toArray().length; i++) {
            if (products.get(i).getArticleNumber() == userInput) {
                productReturned = products.get(i);
            }
        }
        if (productReturned != null) {
            System.out.println("Your product: \n" + productReturned.toString() + "\n----------");
        } else {
            System.out.println("No product with articlenumber " + userInput + " was found.");
        }
        products.clear();
    }
}
