package org.example.behavioural.observer;

import java.util.ArrayList;
import java.util.List;

public class YouTubeChanel implements Subject{

    private List<Observer> subscribers = new ArrayList<>();

    private String videoTitle;

    @Override
    public void subscribe(Observer observer) {
        subscribers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        subscribers.remove(observer);
    }

    public void uploadVideo(String title){
        this.videoTitle = title;
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : subscribers){
            observer.update(videoTitle);
        }
    }
}
