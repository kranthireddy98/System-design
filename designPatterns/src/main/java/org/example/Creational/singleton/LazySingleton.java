package org.example.Creational.singleton;

public class LazySingleton extends MyClone{

    private static LazySingleton instance;

    private LazySingleton(){
        if(instance!=null){
            throw new IllegalStateException("Object can't be create using reflection");
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new CloneNotSupportedException();
    }

    public static LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }

        return instance;
    }
}
