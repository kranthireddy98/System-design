package org.example.structural.composite;

public class CompositeClient {
    public static void main(String[] args) {
        File file1 = new File("resume.pdf");
        File file = new File("photo.png");

        Folder folder =  new Folder("Documents");

        folder.add(file);
        folder.add(file1);

        Folder root = new Folder("Root");

        root.add(folder);

        root.showDetails();
    }
}
