package org.example.structural.Proxy;

public class ProxyClient {
    public static void main(String[] args) {
        Image image = new ProxyImage("test.jpg");

        image.display();

        image.display();
    }
}
