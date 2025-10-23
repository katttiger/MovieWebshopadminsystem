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

    private Product(Builder builder) {
        this.articleNumber = builder.articleNumber;
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
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

    public static class Builder {
        private int articleNumber;
        private String name;
        private double price;
        private String description;

        public Builder articleNumber(int articleNumber) {
            this.articleNumber = articleNumber;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Product build() {
            return new Product(this) {
                @Override
                public String category() {
                    return "";
                }
            };
        }
    }
}

