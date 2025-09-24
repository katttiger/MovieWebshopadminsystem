package se.iths.cecilia.webshopadmin.program;

import se.iths.cecilia.webshopadmin.models.Candy;
import se.iths.cecilia.webshopadmin.models.Movie;
import se.iths.cecilia.webshopadmin.models.Product;
import se.iths.cecilia.webshopadmin.models.StuffedAnimal;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProgramLogic {

    //FOR G: use a list to check that the methods work

    // FOR VG: move methods to class filehandlinglogic and create a UI.

    Scanner sc = new Scanner(System.in);
    private List<Product> products;

    public ProgramLogic() {
        this.products = seedData();
    }

    public void addProduct() {
        //add menu that asks what type of product should be added
        System.out.println("What type of product do you want to add? ");
        System.out.println("1. Movie");
        System.out.println("2. Candy");
        System.out.println("3. Stuffed animal");

        int userInput = -1;
        try {
            do {
                Scanner sc = new Scanner(System.in);
                userInput = sc.hasNextInt() ? sc.nextInt() : -1;

                if (userInput == -1) {
                    throw new InputMismatchException();
                }
            }
            while (userInput < 1 || userInput > 3);

        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid option");
            ;
        }

        Product newProduct = null;
        switch (userInput) {
            case 1 -> newProduct = addNewMovie();
            case 2 -> newProduct = addNewCandy();
            case 3 -> newProduct = addNewStuffedAnimal();
            default -> System.out.println("Invalid input");
        }
        System.out.println("You have added a new " + newProduct.category());
        products.add(newProduct);
        System.out.println("Press enter to return to menu.");
        sc.nextLine();
    }

    public Movie addNewMovie() {
        Movie newMovie = new Movie();
        Scanner sc = new Scanner(System.in);
        System.out.println("You want to add a movie.");

        System.out.println("Enter name: ");
        newMovie.setName(sc.nextLine());

        System.out.println("Enter description: ");
        newMovie.setDescription(sc.nextLine());

        do {
            System.out.println("Enter article number: ");
            newMovie.setArticleNumber(sc.hasNextInt() ? sc.nextInt() : -1);
            sc.nextLine();
            if (newMovie.getArticleNumber() == -1) {
                System.out.println("Articlenumber must be a positive integer.");
            }
            determineArticleNumberIsValid(newMovie);
        } while (newMovie.getArticleNumber() == -1);

        do {
            System.out.println("Enter price (SEK): ");
            newMovie.setPrice(sc.hasNextDouble() ? sc.nextDouble() : -1);
            sc.nextLine();
            if (newMovie.getPrice() == -1) {
                System.out.println("Price must be a positive number.");
            }
        } while (newMovie.getPrice() == -1);
        return newMovie;
    }

    public int determineArticleNumberIsValid(Product product) {
        //check that article number is unique. PROBLEM TODAY: article number is still added
        for (int i = 0; i < products.toArray().length; i++) {
            if (product.getArticleNumber() == products.get(i).getArticleNumber()) {
                System.out.println("Articlenumber is already taken. Enter another.");
                return -1;
            }
        }
        return product.getArticleNumber();
    }

    public Candy addNewCandy() {
        System.out.println("You want to add a candy.");
        return null;
    }

    public StuffedAnimal addNewStuffedAnimal() {
        System.out.println("You want to add a stuffed animal.");

        return null;
        //ask user to enter valid input
        //ERRORHANDLING: PRICE MAY ONLY BE A NUMBER. NO CHARS ALLOWED
    }

    public void listAllProducts() {
        System.out.println("Below are our currents products: ");
        //call a method from filehandling logic and store it
        for (int i = 0; i < products.toArray().length; i++) {
            System.out.println("\n------------\n");
            System.out.println(products.get(i).toString());
        }
        System.out.println("Press enter to return to menu.");
        sc.nextLine();

    }

    public void searchForProduct() {
        //list is fetched from database

        int userInput = -1;
        Product productReturned = null;

        do {
            try {
                System.out.println("Enter the articlenumber of the item you wish to find:");
                userInput = sc.hasNextInt() ? sc.nextInt() : -1;
                sc.nextLine();
                if (userInput == -1) {
                    throw new InputMismatchException();
                }

                //thoughts for the future: add a binary search

                for (int i = 0; i < products.toArray().length; i++) {
                    if (products.get(i).getArticleNumber() == userInput) {
                        productReturned = products.get(i);
                    }

                    if (productReturned != null) {
                        System.out.println("Your product: \n" + productReturned.toString());
                        System.out.println("----------");
                    } else {
                        System.out.println("No product with articlenumber " + userInput + "was found.");
                    }
                    System.out.println("Press enter to return to menu.");
                    sc.nextLine();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You may only enter whole numbers.");
            }
        } while (userInput == -1);
    }

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

    public void closeApplication() {
        System.out.println("Closing down application.");
        System.exit(0);
    }
}
