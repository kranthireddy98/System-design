package org.example.behavioural.Mediator;

public class MediatorMain {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatRoom();

        User user1 = new ChatUser(mediator,"kranthi");
        User user2 = new ChatUser(mediator,"Ravi");
        User user3 = new ChatUser(mediator,"sneha");

        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);

        user1.send("Hello everyone!");
    }
}
