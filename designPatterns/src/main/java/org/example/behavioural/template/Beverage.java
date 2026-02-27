package org.example.behavioural.template;

public abstract class Beverage {

    public final void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    protected abstract void addCondiments() ;
    protected abstract void brew();
    private void pourInCup() {
        System.out.println("Pouring into cup");
    }



    private void boilWater() {
        System.out.println("Boiling Water");
    }
}
