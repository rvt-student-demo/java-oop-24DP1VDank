package rvt;

public class CD implements Interface_Packable {
    private String name;
    private String artist;
    private double weight;
    private int year;

    public CD(String name, String artist, int year) {
        this.name = name;
        this.artist = artist;
        this.weight = 0.1;
        this.year = year;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + ':'  + " " + artist + "(" + year + ")";
    }
    
}
