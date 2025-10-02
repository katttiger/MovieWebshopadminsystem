package se.iths.cecilia.webshopadmin.DAO;

import se.iths.cecilia.webshopadmin.controller.Errorcheck;
import se.iths.cecilia.webshopadmin.models.Candy;
import se.iths.cecilia.webshopadmin.models.Movie;
import se.iths.cecilia.webshopadmin.models.Product;
import se.iths.cecilia.webshopadmin.models.StuffedAnimal;

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
        //add menu that asks what type of product should be added
        int userInput = -1;
        Product newProduct = null;
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
                    newProduct = createNewProduct(new Movie());
                    jsonFileHandler.addNewProductToJsonFile((Movie) newProduct);
                }
                case 2 -> {
                    newProduct = createNewProduct(new Candy());
                    jsonFileHandler.addNewProductToJsonFile((Candy) newProduct);
                }
                case 3 -> {
                    newProduct = createNewProduct(new StuffedAnimal());
                    jsonFileHandler.addNewProductToJsonFile((StuffedAnimal) newProduct);
                }
            }
        } while (userInput < 1 || userInput > 3);
        System.out.println("Object of type " + newProduct.category() + " has been added.");
    }

    public Product createNewProduct(Product newProduct) {
        System.out.println("You want to add a " + newProduct.category());
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
