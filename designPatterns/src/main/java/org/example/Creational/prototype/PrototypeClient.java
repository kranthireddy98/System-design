package org.example.Creational.prototype;

public class PrototypeClient {

    public static void main(String[] args) {
        Employee prototype = new Employee("Kranthi","backend Engineer");

        Employee e1 = prototype.clone();
        e1.setName("Rajesh");
        Employee e2 = prototype.clone();



        System.out.println(e1);
        System.out.println(e2);
    }
}
