package org.example.strategy;

public class LowerCaseStrategy implements TextFormatter{

    @Override
    public String format(String text) {
        return text.toLowerCase();
    }
}
