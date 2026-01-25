package org.example.Creational.factory;

public class NotificationClient {
    public static void main(String[] args) {
        NotificationCreator creator = new EmailNotificationCreator();

        creator.sendNotification();

        creator =new  SmsNotificationCreator();

        creator.sendNotification();


    }
}
