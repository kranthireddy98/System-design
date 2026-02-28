package org.example.behavioural.visitor;

public class ShoppingCartClient {
    public static void main(String[] args) {
        ItemElement[] items = new ItemElement[]{
                new Book(100, "1234"),
                new Book(40, "5678"),
                new Fruit(10, 2, "Banana"),
                new Fruit(5, 5, "Apple")
    };

        int total =0;
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();

        for (ItemElement item: items){
            total+= item.accept(visitor);
        }

        System.out.println("Total cost = "+total);
    }
}
