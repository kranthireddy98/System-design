package org.example.behavioural.template;

public class TemplateClient {
    public static void main(String[] args) {
        Beverage tea = new Tea();
        tea.prepareRecipe();

        Beverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}
