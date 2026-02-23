package org.example.structural.flyweight;

public class FlyweightClient {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            TreeType tree = TreeFactory.getTreeType("Oak", "Green", "Rough");
            tree.display(i, i);
        }
    }
}
