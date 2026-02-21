package org.example.structural.Proxy;

public class RealImage implements Image{

    private String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadfromDisk();
    }

    private void loadfromDisk(){
        System.out.println("Loading image from Disk: "+ fileName);

    }

    @Override
    public  void display(){
        System.out.println("Displaying image: "+fileName);
    }
}
