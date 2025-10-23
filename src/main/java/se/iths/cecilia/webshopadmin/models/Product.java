package se.iths.cecilia.webshopadmin.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import se.iths.cecilia.webshopadmin.DAO.filehandler.JSONFileHandler;
import se.iths.cecilia.webshopadmin.controller.Errorcheck;
import se.iths.cecilia.webshopadmin.view.UI;

import java.util.List;
import java.util.Scanner;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "category"
)

@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = Candy.class, name = "Candy"),
                @JsonSubTypes.Type(value = StuffedAnimal.class, name = "StuffedAnimal"),
                @JsonSubTypes.Type(value = Movie.class, name = "Movie"),
        }
)

@JsonPropertyOrder(
        {"category", "articleNumber", "name", "description", "price"}
)

public abstract class Product {

    @JsonProperty("articleNumber")
    private int articleNumber;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private double price;

    @JsonProperty("description")
    private String description;
    @JsonProperty("category")
    private String category;


    public Product() {
    }

    public abstract String category();

    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Factory {
        String category;
        Scanner sc = new Scanner(System.in);

        private JSONFileHandler jsonFileHandler;
        List<Product> products;

        public Factory() {
            this.jsonFileHandler = new JSONFileHandler();
        }

        public Product createProduct() {
            return switch (category) {
                case "Candy" -> createCandy();
                case "StuffedAnimal" -> createStuffedAnimal();
                case "Movie" -> createMovie();
                default -> null;
            };
        }

        public Candy createCandy() {
            Candy newProduct = new Candy();
            newProduct.setName(returnProductName());
            newProduct.setDescription(returnDescription());
            newProduct.setArticleNumber(determineArticleNumberIsValid());
            newProduct.setPrice(determinePriceIsValid());
            return newProduct;
        }

        public StuffedAnimal createStuffedAnimal() {
            StuffedAnimal newProduct = new StuffedAnimal();
            newProduct.setName(returnProductName());
            newProduct.setDescription(returnDescription());
            newProduct.setArticleNumber(determineArticleNumberIsValid());
            newProduct.setPrice(determinePriceIsValid());
            return newProduct;
        }

        public Movie createMovie() {
            Movie newProduct = new Movie();
            newProduct.setName(returnProductName());
            newProduct.setDescription(returnDescription());
            newProduct.setArticleNumber(determineArticleNumberIsValid());
            newProduct.setPrice(determinePriceIsValid());
            return newProduct;
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
}

