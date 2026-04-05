package rvt;

import java.util.ArrayList;

public class Box implements Interface_Packable {
    // max weight of the box
    private double weight;

    ArrayList<Interface_Packable> items;

    public static void main(String[] args) {
    Box BigBox = new Box(10);


    BigBox.add(new Book("Fyodor Dostoevsky", "Crime and Punishment", 2)) ;
    BigBox.add(new Book("Robert Martin", "Clean Code", 1));
    BigBox.add(new Book("Kent Beck", "Test Driven Development", 0.7));

    BigBox.add(new CD("Pink Floyd", "Dark Side of the Moon", 1973));
    BigBox.add(new CD("Wigwam", "Nuclear Nightclub", 1975));
    BigBox.add(new CD("Rendezvous Park", "Closer to Being Here", 2012));

    Box smallBox = new Box(5);
    smallBox.add(new Book("Author A", "Book A", 1));
    smallBox.add(new CD("Artist B", "CD B", 1970));

   //4 part of the task
    System.out.println("Big box before adding small box: " + BigBox);
    System.out.println("Small box: " + smallBox);
    // Add the small box to the big box
    BigBox.add(smallBox);
    
    System.out.println("Big box after adding small box: " + BigBox);
}

    public void add(Interface_Packable packable) {
        if (weight() + packable.weight() <= this.weight) { // weight() counts the total weight of the items in the box
    items.add(packable);
}
        }
    
    public Box(double weight) {
        // maximum weight of the box
        this.weight = weight;
        this.items = new ArrayList<>(); 
    }

    @Override
    public double weight() {
        double totalweight = 0;
        // calculate the total weight of the items in the box
        for (Interface_Packable item : items) {
            totalweight += item.weight();
        }
        return totalweight;
    }
    
    @Override
    public String toString() {
        return "Box: " + items.size() + " items, total weight " + weight() + " kg";
}
}
