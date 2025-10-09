package se.iths.cecilia.webshopadmin.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StuffedAnimal extends Product {

    @JsonProperty("category")
    private String category;

    public StuffedAnimal() {
        this.category = "StuffedAnimal";
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
