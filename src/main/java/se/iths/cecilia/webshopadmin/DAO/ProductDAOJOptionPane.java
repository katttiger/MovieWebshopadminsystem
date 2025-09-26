package se.iths.cecilia.webshopadmin.DAO;

import se.iths.cecilia.webshopadmin.controller.Errorcheck;
import se.iths.cecilia.webshopadmin.models.Candy;
import se.iths.cecilia.webshopadmin.models.Movie;
import se.iths.cecilia.webshopadmin.models.Product;
import se.iths.cecilia.webshopadmin.models.StuffedAnimal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
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
//        //add menu that asks what type of product should be added
//        int userInput = -1;
//        Product newProduct = null;
//        do {
//            System.out.println("""
//                    What type of product do you want to add?
//                    1. Movie
//                    2. Candy
//                    3. Stuffed animal""");
//
//            userInput = errorcheck.checkIntegerInput();
//            if (userInput < 1 || userInput > 3) {
//                System.out.println("Input must be between 1 and 3");
//            }
//
//            switch (userInput) {
//                case 1 -> newProduct = createNewProduct(new Movie());
//                case 2 -> newProduct = createNewProduct(new Candy());
//                case 3 -> newProduct = createNewProduct(new StuffedAnimal());
//            }
//        } while (userInput < 1 || userInput > 3);
//
//        products.add(newProduct);
//        System.out.println("Object of type " + newProduct.category() + " has been added to");
    }

//    public Product createNewProduct(Product newProduct) {
//        System.out.println("You want to add a " + newProduct.category());
//
//        do {
//            System.out.println("Enter name: ");
//            newProduct.setName(sc.nextLine());
//        } while (newProduct.getName().isEmpty() || newProduct.getName().isBlank());
//
//        do {
//            System.out.println("Enter description: ");
//            newProduct.setDescription(sc.nextLine());
//        } while (newProduct.getDescription().isEmpty() || newProduct.getDescription().isBlank());
//
//        do {
//            newProduct.setArticleNumber(determineArticleNumberIsValid());
//        } while (newProduct.getArticleNumber() == -1);
//
//        do {
//            newProduct.setPrice(determinePriceIsValid());
//        } while (newProduct.getPrice() < 0);
//
//        return newProduct;
//    }
//
//    public double determinePriceIsValid() {
//        System.out.println("Enter price (SEK): ");
//        double pendingPrice = sc.hasNextDouble() ? sc.nextDouble() : -1;
//        sc.nextLine();
//        if (pendingPrice < 1) {
//            System.out.println("Price must be a positive number.");
//        }
//        return pendingPrice;
//    }

//    public int determineArticleNumberIsValid() {
//        System.out.println("Enter article number: ");
//        int pendingArticleNumber;
//        do {
//            pendingArticleNumber = errorcheck.checkIntegerInput();
//            pendingArticleNumber = errorcheck.isNumberPositive(pendingArticleNumber);
//        }
//        while (pendingArticleNumber < 1);
//
//        for (int i = 0; i < products.toArray().length; i++) {
//            if (pendingArticleNumber == products.get(i).getArticleNumber()) {
//                System.out.println("Article number has been taken. Please try again.");
//                pendingArticleNumber = -1;
//            }
//        }
//        return pendingArticleNumber;
//    }

    @Override
    public void listAllProducts() {
        JOptionPane.showMessageDialog(
                null,
                "Below are our currents products: " + Arrays.toString(products.toArray()) + "\n----------\n");
    }

    @Override
    public void searchForProduct() {
        try {
            do {
                int userInput = -1;
                String returned = "";

                do {
                    String answer = JOptionPane.showInputDialog(
                            "Please enter the article number you want to search for: ");
                    userInput = Integer.parseInt(answer);
                } while (userInput < 1);
                
                for (int i = 0; i < products.toArray().length; i++) {
                    if (products.get(i).getArticleNumber() == userInput) {
                        returned = products.get(i).toString();
                    }

                    if (!returned.isBlank() || !returned.isEmpty()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Your product: \n" + returned + "\n----------");
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "No product with articlenumber " + userInput + " was found.");
                    }
                }
            } while (true);
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid format. Try again");
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
