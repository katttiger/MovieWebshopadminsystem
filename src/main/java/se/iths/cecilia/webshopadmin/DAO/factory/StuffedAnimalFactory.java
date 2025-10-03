package se.iths.cecilia.webshopadmin.DAO.factory;

import se.iths.cecilia.webshopadmin.DAO.filehandler.JSONFileHandler;
import se.iths.cecilia.webshopadmin.controller.Errorcheck;
import se.iths.cecilia.webshopadmin.models.Product;
import se.iths.cecilia.webshopadmin.models.StuffedAnimal;

import java.util.List;
import java.util.Scanner;

public class StuffedAnimalFactory implements ProductFactory {

    Scanner sc = new Scanner(System.in);
    Errorcheck errorcheck;
    JSONFileHandler jsonFileHandler;

    List<Product> products;

    public StuffedAnimalFactory() {
        this.errorcheck = new Errorcheck();
        this.jsonFileHandler = new JSONFileHandler();
    }


    @Override
    public StuffedAnimal create() {
        StuffedAnimal newProduct = new StuffedAnimal();
        do {
            System.out.println("Enter name: ");
            newProduct.setName(sc.nextLine());
        } while (newProduct.getName().isBlank());

        do {
            System.out.println("Enter description: ");
            newProduct.setDescription(sc.nextLine());
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
        System.out.println("Enter price (SEK): ");
        double pendingPrice = 0;
        try {
            pendingPrice = sc.hasNextDouble() ? sc.nextDouble() : -1;
            sc.nextLine();
            if (pendingPrice < 0) {
                System.out.println("Price must be a positive number.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
        return pendingPrice;
    }

    public int determineArticleNumberIsValid() {
        System.out.println("Enter article number: ");
        int pendingArticleNumber;
        do {
            pendingArticleNumber = errorcheck.checkIntegerInput();
            assert pendingArticleNumber >= 0;
        }
        while (pendingArticleNumber < 1);

        products = jsonFileHandler.retrieveAllItemsInJsonFile();
        for (int i = 0; i < products.toArray().length; i++) {
            if (pendingArticleNumber == products.get(i).getArticleNumber()) {
                System.out.println("Article number has been taken. Please try again.");
                pendingArticleNumber = -1;
            }
        }
        products.clear();
        return pendingArticleNumber;


    }

}

