package se.iths.cecilia.webshopadmin.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie extends Product {
    @JsonProperty("category")
    private String category;

    public Movie(int articleNumber, String name, double price, String description) {
        super(articleNumber, name, price, description);
    }

    public Movie() {
        this.category = "Movie";
    }

    @Override
    public String toString() {
        return category()
                + "\nArticle number: " + this.getArticleNumber()
                + "\nName: " + this.getName()
                + "\nDescription: " + this.getDescription()
                + "\nPrice (SEK): " + this.getPrice();
    }

    @Override
    public String category() {
        return "Category: " + this.category;
    }
}
