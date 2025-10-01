package se.iths.cecilia.webshopadmin.DAO;

import se.iths.cecilia.webshopadmin.controller.Errorcheck;
import se.iths.cecilia.webshopadmin.models.Candy;
import se.iths.cecilia.webshopadmin.models.Movie;
import se.iths.cecilia.webshopadmin.models.Product;
import se.iths.cecilia.webshopadmin.models.StuffedAnimal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductDAOJOptionPane implements ProductDAOInterface {
    //DATA ACCESS TO DB
    //G : LIST WITH PRODUCTS
    private List<Product> products;
    private Errorcheck errorcheck;
    private JSONFileHandler jsonFileHandler;

    //VG : FILEHANDLING (STREAM INPUT AND OUTPUT)
    public ProductDAOJOptionPane() {
        this.errorcheck = new Errorcheck();
        this.jsonFileHandler = new JSONFileHandler();
        jsonFileHandler.checkThatFileExists();
    }

    @Override
    public void addProduct() {
        try {
            String anwser = JOptionPane.showInputDialog("""
                    What type of product do you want to add?
                    1. Movie
                    2. Candy
                    3. Stuffed animal""");

            Product newProduct = null;
            int userChoice = Integer.parseInt(anwser);
            if (userChoice < 1 || userChoice > 3) {
                JOptionPane.showMessageDialog(
                        null,
                        "Input must be between 1 and 3. Try again.");
            } else {
                switch (userChoice) {
                    case 1 -> newProduct = createNewProduct(new Movie());
                    case 2 -> newProduct = createNewProduct(new Candy());
                    case 3 -> newProduct = createNewProduct(new StuffedAnimal());
                }
                jsonFileHandler.fileWriter(newProduct);
                //        products.add(newProduct);
                JOptionPane.showMessageDialog(
                        null,
                        "Product has been added."
                );
            }

        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid format. Please try again."
            );
        }
    }

    public Product createNewProduct(Product newProduct) {
        JOptionPane.showMessageDialog(
                null,
                "You want to add a " + newProduct.category() + "\n Press enter to continue.");
        do {
            newProduct.setName(JOptionPane.showInputDialog(
                    "Step 1 of 4:\n Enter name of product: "));
        } while (newProduct.getName().isBlank());
        do {
            newProduct.setDescription(
                    JOptionPane.showInputDialog(
                            "Step 2 of 4:\n Enter description of product: "
                    )
            );
        } while (newProduct.getDescription().isBlank());
        do {
            newProduct.setArticleNumber(determineArticleNumberIsValid());
        } while (newProduct.getArticleNumber() == -1);
        do {
            newProduct.setPrice(determinePriceIsValid());
        } while (newProduct.getPrice() < 0);
        return newProduct;
    }

    private int determineArticleNumberIsValid() {
        int pendingArticleNumber = 0;
        try {
            pendingArticleNumber = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "Step 3 of 4:\n Enter article number of product: "
                    )
            );

//            for (int i = 0; i < products.toArray().length; i++) {
//                if (pendingArticleNumber == products.get(i).getArticleNumber()) {
//                    JOptionPane.showMessageDialog(
//                            null,
//                            "Article number has aldready been taken. Please try again;");
//                    pendingArticleNumber = -1;
//                }
//            }
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid format. Please try again.");
            pendingArticleNumber = -1;
        }
        return pendingArticleNumber;
    }

    private double determinePriceIsValid() {
        double pendingPrice = 0;
        try {
            pendingPrice = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "Step 4 of 4:\n Enter price of product: "
                    )
            );
            if (pendingPrice < 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "Price must be a positive number.");
            }
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid input. Please try again."
            );
            pendingPrice = -1;
        }
        return pendingPrice;
    }

    @Override
    public void listAllProducts() {
        List<String> productStrings = jsonFileHandler.retrieveAllItemsInJsonFile();
        for (String s : productStrings) {
            s.trim();
            s.splitWithDelimiters(String.valueOf(1), '}');
        }
        if (productStrings != null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Your products: %s".formatted(productStrings)
            );
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "There are no products in the json file."
            );
        }
    }

    @Override
    public void searchForProduct() {
        try {
            int userInput = 0;
            String returned = null;
            String answer = JOptionPane.showInputDialog(
                    "Please enter the article number you want to search for: ");
            userInput = Integer.parseInt(answer);

            if (userInput != JOptionPane.CANCEL_OPTION) {
                returned = jsonFileHandler.retrieveItemFromJsonFile(userInput);
                if (!returned.isBlank()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Your product: \n" + returned + "\n----------");
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "No product with articlenumber " + userInput + " was found.");
                }
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid format. Try again");
        }
    }

}
