package org.example.behavioural.observer;

public class ObserverMain {
    public static void main(String[] args) {
        YouTubeChanel chanel = new YouTubeChanel();

        Subscriber s1 = new Subscriber("Kranthi");
        Subscriber s2 = new Subscriber("Ravi");

        chanel.subscribe(s1);
        chanel.subscribe(s2);

        chanel.uploadVideo("Observer Pattern Explained");
    }
}
