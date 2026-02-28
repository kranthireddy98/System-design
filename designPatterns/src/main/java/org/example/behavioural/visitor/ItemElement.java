package org.example.behavioural.visitor;

public interface ItemElement {

    int accept(ShoppingCartVisitor visitor);

}
