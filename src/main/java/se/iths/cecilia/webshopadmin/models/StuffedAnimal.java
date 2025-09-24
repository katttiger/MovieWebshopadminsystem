package se.iths.cecilia.webshopadmin.models;

public class StuffedAnimal extends Product {
    private String category;

    public StuffedAnimal() {
        this.category = "Suffed animal";
    }

    @Override
    public String toString() {
        return "Category: " + Category()
                + "\nArticle number: " + this.getArticleNumber()
                + "\nName: " + this.getName()
                + "\nDescription: " + this.getDescription()
                + "\nPrice (SEK): " + this.getPrice();
    }

    @Override
    public String Category() {
        return "Category: " + this.category;
    }
}
