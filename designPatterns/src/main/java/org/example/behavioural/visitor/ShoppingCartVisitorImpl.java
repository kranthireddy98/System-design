package org.example.behavioural.visitor;

public class ShoppingCartVisitorImpl implements ShoppingCartVisitor{
    @Override
    public int visit(Book book) {
        int cost = book.getPrice();
        if(cost>50){
            cost -=5;
        }
        return cost;
    }

    @Override
    public int visit(Fruit fruit) {
        return fruit.getWeight() * fruit.getPricePerKg();
    }
}
