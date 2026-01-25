package org.example.Creational.factory;

public class EmailNotificationCreator extends NotificationCreator{

    @Override
    protected Notification createNotification(){
        return new EmailNotification();
    }
}
