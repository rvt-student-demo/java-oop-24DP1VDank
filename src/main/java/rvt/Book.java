package rvt;


public class Book implements Interface_Packable {
    private String name;
    private String author;
    private double weight;

    public Book(String author, String name, double weight) {
        this.name = name;
        this.author = author;
        this.weight = weight;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return author + ':'  + " " + name;
    }

}