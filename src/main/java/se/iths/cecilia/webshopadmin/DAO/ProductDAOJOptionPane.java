package se.iths.cecilia.webshopadmin.DAO;

import se.iths.cecilia.webshopadmin.controller.Errorcheck;
import se.iths.cecilia.webshopadmin.models.Candy;
import se.iths.cecilia.webshopadmin.models.Movie;
import se.iths.cecilia.webshopadmin.models.Product;
import se.iths.cecilia.webshopadmin.models.StuffedAnimal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOJOptionPane implements ProductDAOInterface {
    //DATA ACCESS TO DB
    //G : LIST WITH PRODUCTS
    private List<Product> products;
    Errorcheck errorcheck;

    //VG : FILEHANDLING (STREAM INPUT AND OUTPUT)
    public ProductDAOJOptionPane() {
        this.products = seedData();
        this.errorcheck = new Errorcheck();
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
                //        products.add(newProduct);
                JOptionPane.showMessageDialog(
                        null,
                        "Object of type" + newProduct.category() + " has been added to"
                );
                products.add(newProduct);
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

            for (int i = 0; i < products.toArray().length; i++) {
                if (pendingArticleNumber == products.get(i).getArticleNumber()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Article number has aldready been taken. Please try again;");
                    pendingArticleNumber = -1;
                }
            }
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
        JOptionPane.showMessageDialog(
                null,
                ("Below are our currents products:" +
                        " \n%s").formatted(products.toArray().toString()));
    }

    @Override
    public void searchForProduct() {
        try {
            int userInput = 0;
            String returned = "";
            String answer = JOptionPane.showInputDialog(
                    "Please enter the article number you want to search for: ");
            userInput = Integer.parseInt(answer);

            if (userInput != JOptionPane.CANCEL_OPTION) {
                for (int i = 0; i < products.toArray().length; i++) {
                    if (products.get(i).getArticleNumber() == userInput) {
                        returned = products.get(i).toString();
                    }
                }

                if (!returned.isEmpty()) {
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

    //SEEDDATA FOR IMPLEMENTATION WITH LIST
    public List<Product> seedData() {
        Candy ferraribilar = new Candy();
        ferraribilar.setName("Ferrari Orginal");
        ferraribilar.setArticleNumber(12);
        ferraribilar.setPrice(199);
        ferraribilar.setDescription("Små ferraribilar av vingummi med hallonsmak.");

        StuffedAnimal sam = new StuffedAnimal();
        sam.setName("Sam");
        sam.setArticleNumber(34);
        sam.setPrice(155);
        sam.setDescription("En nallebjörn. " +
                "\n Storlek: ca 25cm (mätt sittande, ca 35 cm i fullängd)" +
                "\n Material: Polyester" +
                "\n Varför: Även vuxna kan behöva någon när Imhotep väcks till liv.");

        Movie theMummy = new Movie();
        theMummy.setName("The Mummy (1999)");
        theMummy.setArticleNumber(56);
        theMummy.setDescription("Översteprästen Imhotep blev en gång levande begravd och mumifierad som straff för att ha gjort uppror mot sin farao. Dessutom lades den grymmaste av förbannelser över honom. " +
                "\n År 1923 störs hans gravkammare, bland annat av en grupp amerikanska arkeologer som är på jakt efter försvunna skatter, och översteprästen vaknar till liv igen. Med sig som vapen för han Egyptens 10 farsoter." +
                "\n I rollerna: Brendan Fraser, Rachel Weisz, John Hannah");

        products = new ArrayList<>();
        products.add(ferraribilar);
        products.add(sam);
        products.add(theMummy);
        return products;
    }

}
