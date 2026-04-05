package rvt;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class Warehouse {
    Map<String, Integer> product_price = new HashMap<>();
    Map<String, Integer> product_stock = new HashMap<>();
    public static void main(String[] args) {    
    Warehouse warehouse = new Warehouse();

    /*  1 Part if the task 
    warehouse.addProduct("milk", 3, 10);
    warehouse.addProduct("coffee", 5, 7);

    System.out.println("prices:");
    System.out.println("milk: " + warehouse.price("milk"));
    System.out.println("coffee: " + warehouse.price("coffee"));
    System.out.println("sugar: " + warehouse.price("sugar"));  
    */ 

    /* 2 part of the task
    warehouse.addProduct("coffee", 5, 1);

    System.out.println("stock:");
    System.out.println("coffee:  " + warehouse.stock("coffee"));
    System.out.println("sugar: " + warehouse.stock("sugar"));

    System.out.println("taking coffee " + warehouse.take("coffee"));
    System.out.println("taking coffee " + warehouse.take("coffee"));
    System.out.println("taking sugar " + warehouse.take("sugar"));

    System.out.println("stock:");
    System.out.println("coffee:  " + warehouse.stock("coffee"));
    System.out.println("sugar: " + warehouse.stock("sugar"));
        */ 
       
    //3 part of the task
    warehouse.addProduct("milk", 3, 10);
    warehouse.addProduct("coffee", 5, 6);
    warehouse.addProduct("buttermilk", 2, 20);
    warehouse.addProduct("yogurt", 2, 20);

    System.out.println("products:");

    for (String product: warehouse.products()) {
        System.out.println(product);
}
    }

    public void addProduct(String product, int price, int stock) {
        product_price.put(product, price);
        product_stock.put(product, stock);
    }

    public int price(String product){
        if (!product_price.containsKey(product)) {
            return -99;
        }
        else{
        return product_price
        .get(product);}
    }

    public int stock(String product) {
        if (!product_stock.containsKey(product)) {
            return 0;
        }
        else {
            return product_stock.get(product);
        }
    }

    public boolean take(String product) {
        if (!product_stock.containsKey(product) || product_stock.get(product) == 0) {
            return false;
        }
        else {
            int currentStock = product_stock.get(product);
            product_stock.put(product, currentStock - 1);
            return true;
        }
    }

    public Set<String> products(){
        return product_price.keySet();
    }
}

    

