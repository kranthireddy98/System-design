package org.example.Creational.factory;

public abstract class NotificationCreator {

    // Factory Method
    protected abstract Notification createNotification();

    public void sendNotification(){
        Notification notification = createNotification();
        notification.send();;
    }
}
