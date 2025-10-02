package se.iths.cecilia.webshopadmin.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


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

    public Product(int articleNumber, String name, double price, String description) {
        this.articleNumber = articleNumber;
        this.name = name;
        this.price = price;
        this.description = description;
    }

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
}
