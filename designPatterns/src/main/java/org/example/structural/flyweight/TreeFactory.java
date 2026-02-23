package org.example.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class TreeFactory {
    private static Map<String,TreeType> treeMap = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture){
        String key = name+color+texture;

        if(!treeMap.containsKey(key)){
            System.out.println("Don't contain Key");
            treeMap.put(key, new TreeType(name, color, texture));
        }
        return treeMap.get(key);
    }
}
