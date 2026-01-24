package org.example.Creational.builder;

public class BuilderMain {
    public static void main(String[] args) {
        UserBp user = new UserBp.UserBuilder()
                .firstName("kranthi")
                .lastName("reddy")
                .email("k@k.com")
                .age(26)
                .country("india")
                .build();

        System.out.println(user);
    }
}
