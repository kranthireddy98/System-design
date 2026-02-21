package org.example.Creational.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException, InvocationTargetException, InstantiationException, IllegalAccessException {
        EagerSingleton obj1= EagerSingleton.getInstance();

        System.out.println(obj1.hashCode());

        EagerSingleton obj2 = EagerSingleton.getInstance();

        System.out.println(obj2.hashCode());

        LazySingleton objL1 = LazySingleton.getInstance();
        LazySingleton objL2 = LazySingleton.getInstance();

        System.out.println(objL1.hashCode());
        System.out.println(objL2.hashCode());

        DoubleCheckLock objd2 = DoubleCheckLock.getInstance();
        DoubleCheckLock objd1 = DoubleCheckLock.getInstance();

        System.out.println(objd1.hashCode());
        System.out.println(objd2.hashCode());


       /*LazySingleton objCL = (LazySingleton) objL1.clone();
        System.out.println(objCL.hashCode());*/

        LazySingleton insta =null;

        Constructor[] constructors = LazySingleton.class.getDeclaredConstructors();

        for (Constructor constructor : constructors){
            constructor.setAccessible(true);
            insta=(LazySingleton) constructor.newInstance();
        }

        System.out.println(insta.hashCode());

     }
}
