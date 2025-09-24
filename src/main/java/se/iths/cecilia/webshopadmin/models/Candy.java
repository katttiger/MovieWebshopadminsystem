package se.iths.cecilia.webshopadmin.models;

public class Candy extends Product {
    private String category;

    public Candy() {
        this.category = "Candy";
    }

    @Override
    public String toString() {
        return "Category: " + this.category
                + "Article number: " + this.getArticleNumber()
                + "Name: " + this.getName()
                + "Description: " + this.getDescription()
                + "Price (SEK)" + this.getPrice();
    }

    @Override
    public void Category() {
    }
}
