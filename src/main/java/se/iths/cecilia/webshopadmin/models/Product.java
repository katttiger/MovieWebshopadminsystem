package se.iths.cecilia.webshopadmin.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Product {

    @JsonProperty("articleNumber")
    private int articleNumber;

    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private double price;

    @JsonProperty("description")
    private String description;

    public String category() {
        return "";
    }

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
