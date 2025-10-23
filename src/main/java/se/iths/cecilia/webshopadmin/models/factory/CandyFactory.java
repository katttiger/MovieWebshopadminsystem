package se.iths.cecilia.webshopadmin.models.factory;

import se.iths.cecilia.webshopadmin.DAO.filehandler.JSONFileHandler;
import se.iths.cecilia.webshopadmin.controller.Errorcheck;
import se.iths.cecilia.webshopadmin.models.Candy;
import se.iths.cecilia.webshopadmin.models.Product;
import se.iths.cecilia.webshopadmin.view.UI;

import java.util.List;
import java.util.Scanner;

public class CandyFactory extends ProductFactory {
    Scanner sc = new Scanner(System.in);
    JSONFileHandler jsonFileHandler;
    List<Product> products;

    Candy newProduct;

    public CandyFactory() {
        this.jsonFileHandler = JSONFileHandler.getInstance();
    }

    public String returnProductName() {
        String pendingName;
        UI.info("Enter name: ");
        do {
            pendingName = UI.prompt(sc.nextLine());
        } while (pendingName.isBlank());
        return pendingName;
    }

    public String returnDescription() {
        String pendingDescription;
        UI.info("Enter description: ");
        do {
            pendingDescription = UI.prompt(sc.nextLine());
        } while (pendingDescription.isBlank());
        return pendingDescription;
    }

    @Override
    public Product createProduct() {
        return new Product.Builder()
                .name(returnProductName())
                .description(returnDescription())
                .articleNumber(determineArticleNumberIsValid())
                .price(determinePriceIsValid())
                .build();
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
            checkThatArticlenumberIsUnique(pendingArticleNumber);
        }
        while (pendingArticleNumber < 1 && pendingArticleNumber == -1);
        return pendingArticleNumber;
    }

    public void checkThatArticlenumberIsUnique(int pendingArticleNumber) {
        products = jsonFileHandler.retrieveAllItemsInJsonFile();
        for (int i = 0; i < products.toArray().length; i++) {
            if (pendingArticleNumber == products.get(i).getArticleNumber()) {
                UI.info("Article number has been taken. Please try again.");
                pendingArticleNumber = -1;
            }
        }
        products.clear();
    }
}
