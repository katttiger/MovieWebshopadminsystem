package se.iths.cecilia.webshopadmin.models;

public class Movie extends Product {

    private String category;

    public Movie() {
        this.category = "Movie";
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
