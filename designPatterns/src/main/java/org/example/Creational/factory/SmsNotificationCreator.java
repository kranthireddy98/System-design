package org.example.Creational.factory;

public class SmsNotificationCreator extends NotificationCreator{

    @Override
    protected  Notification createNotification(){
        return new SmsNotification();
    }
}
