package org.example.structural.flyweight;

public class TreeType implements Tree{

    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture){
        this.name =name;
        this.color=color;
        this.texture=texture;
    }

    @Override
    public void display(int x, int y){
        System.out.println("Tree: " + name+" at (" +x+ ","+y+")");
    }
}
