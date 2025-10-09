package se.iths.cecilia.webshopadmin.DAO.factory;

import se.iths.cecilia.webshopadmin.DAO.filehandler.JSONFileHandler;
import se.iths.cecilia.webshopadmin.controller.Errorcheck;
import se.iths.cecilia.webshopadmin.models.Product;
import se.iths.cecilia.webshopadmin.models.StuffedAnimal;
import se.iths.cecilia.webshopadmin.view.UI;

import java.util.List;
import java.util.Scanner;

public class StuffedAnimalFactory implements ProductFactory {
    Scanner sc = new Scanner(System.in);
    JSONFileHandler jsonFileHandler;
    List<Product> products;

    public StuffedAnimalFactory() {
        this.jsonFileHandler = new JSONFileHandler();
    }

    @Override
    public StuffedAnimal create() {
        StuffedAnimal newProduct = new StuffedAnimal();
        do {
            UI.info("Enter name: ");
            newProduct.setName(UI.prompt(sc.nextLine()));
        } while (newProduct.getName().isBlank());

        do {
            UI.info("Enter description: ");
            newProduct.setDescription(UI.prompt(sc.nextLine()));
        } while (newProduct.getDescription().isBlank());

        do {
            newProduct.setArticleNumber(determineArticleNumberIsValid());
        } while (newProduct.getArticleNumber() == -1);

        do {
            newProduct.setPrice(determinePriceIsValid());
        } while (newProduct.getPrice() < 0);
        return newProduct;
    }

    public double determinePriceIsValid() {
        UI.info("Enter price (SEK): ");
        double pendingPrice = 0;
        try {
            pendingPrice = sc.hasNextDouble() ? sc.nextDouble() : -1;
            sc.nextLine();
            if (pendingPrice < 0) {
                UI.info("Price must be a positive number.");
            }
        } catch (Exception e) {
            UI.info("Invalid input. Please try again.");
        }
        return pendingPrice;
    }

    public int determineArticleNumberIsValid() {
        UI.info("Enter article number: ");
        int pendingArticleNumber;
        do {
            pendingArticleNumber = Errorcheck.checkIntegerInput();
            assert pendingArticleNumber >= 0;
        }
        while (pendingArticleNumber < 1);

        products = jsonFileHandler.retrieveAllItemsInJsonFile();
        for (int i = 0; i < products.toArray().length; i++) {
            if (pendingArticleNumber == products.get(i).getArticleNumber()) {
                UI.info("Article number has been taken. Please try again.");
                pendingArticleNumber = -1;
            }
        }
        products.clear();
        return pendingArticleNumber;


    }

}

