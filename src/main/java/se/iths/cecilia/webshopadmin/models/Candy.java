package se.iths.cecilia.webshopadmin.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Candy extends Product {

    @JsonProperty("category")
    private String category;

    public Candy() {
        this.category = "Candy";
    }

    @Override
    public String toString() {
        return category()
                + "\nArticle number: " + this.getArticleNumber()
                + "\nName: " + this.getName()
                + "\nDescription: " + this.getDescription()
                + "\nPrice (SEK): " + this.getPrice()
                + "\n---";
    }

    @Override
    public String category() {
        return "Category: " + this.category;
    }

}
