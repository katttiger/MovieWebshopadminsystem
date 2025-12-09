package se.iths.cecilia.webshopadmin.DAO;

import se.iths.cecilia.webshopadmin.DAO.filehandler.JSONFileHandler;
import se.iths.cecilia.webshopadmin.models.Candy;
import se.iths.cecilia.webshopadmin.models.Movie;
import se.iths.cecilia.webshopadmin.models.Product;
import se.iths.cecilia.webshopadmin.models.StuffedAnimal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductDAOJOptionPane implements ProductDAOInterface {

    private List<Product> products;
    private JSONFileHandler jsonFileHandler;

    public ProductDAOJOptionPane() {
        this.jsonFileHandler = JSONFileHandler.getInstance();
    }

    @Override
    public void addProduct() {
        String[] options = new String[]{"Movie", "Candy", "Stuffed animal", "Cancel"
        };

        try {
            int answer = JOptionPane.showOptionDialog(
                    null,
                    "What type of product do you want to add?",
                    "Add product",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[3]
            );

            Product newProduct = null;
            switch (answer) {
                case 0 -> newProduct = createProduct(new Movie());
                case 1 -> newProduct = createProduct(new Candy());
                case 2 -> newProduct = createProduct(new StuffedAnimal());
            }

            jsonFileHandler.addNewProductToJsonFile(newProduct);

            JOptionPane.showMessageDialog(
                    null,
                    "Product has been added."
            );

        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid format. Please try again."
            );
        }
    }

    public Product createProduct(Product newProduct) {

        do {
            String name = JOptionPane.showInputDialog(
                    "Enter name of movie"
            );
            newProduct.setName(name);
        } while (newProduct.getName().isBlank());

        do {
            String description = JOptionPane.showInputDialog(
                    "Enter description of movie"
            );
            newProduct.setDescription(description);
        } while (newProduct.getDescription().isBlank());

        do {
            int aricleNumber = Integer.parseInt(
                    JOptionPane.showInputDialog("Enter articlenumber of movie"));
            if (aricleNumber > 0) {
                newProduct.setArticleNumber(aricleNumber);
            } else {
                newProduct.setArticleNumber(0);
            }
        } while (newProduct.getArticleNumber() == 0);

        do {
            double price = Double.parseDouble(
                    JOptionPane.showInputDialog("Enter price of movie")
            );
            newProduct.setPrice(price);
        } while (newProduct.getPrice() == -1);

        return newProduct;
    }

    @Override
    public void listAllProducts() {
        products = jsonFileHandler.retrieveAllItemsInJsonFile();
        if (products != null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Your products: " + products.toString()
            );
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "There are no products registered in our system."
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

            returned = jsonFileHandler.retrieveItemFromJsonFile(userInput);
            if (!returned.isBlank()) {
                JOptionPane.showMessageDialog(
                        null,
                        returned);
            }

        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid format. Try again");
        }
    }

}
