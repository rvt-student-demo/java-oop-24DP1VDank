package rvt;
import java.util.Map;
import java.util.HashMap;

public class ShoppingCart {
    Map<String, Item> products = new HashMap<>();

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        /* 
        cart.add("milk", 3);
        cart.add("buttermilk", 2);
        cart.add("cheese", 5);
        System.out.println("cart price: " + cart.price());
        cart.add("computer", 899);
        System.out.println("cart price: " + cart.price());
        cart.print(); */
        cart.add("milk", 3);
        cart.print();
        System.out.println("cart price: " + cart.price() + "\n");

        cart.add("buttermilk", 2);
        cart.print();
        System.out.println("cart price: " + cart.price() + "\n");

        cart.add("milk", 3);
        cart.print();
        System.out.println("cart price: " + cart.price() + "\n");

        cart.add("milk", 3);
        cart.print();
        System.out.println("cart price: " + cart.price() + "\n");
    }
  

   public void add(String product, int price){
        if (!products.containsKey(product)) {
            products.put(product, new Item(product, 1, price));
        } else {
            products.get(product).increaseQuantity();
   } 
}

    public int price(){
        int totalPrice = 0;
        for (Item item: products.values()) {
            totalPrice += item.price();
        }
        return totalPrice;
}
    public void print(){
        for (Item item: products.values()) {
            System.out.println(item);
        
    }

}
}
