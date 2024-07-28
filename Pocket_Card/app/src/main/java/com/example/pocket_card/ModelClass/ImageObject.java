package com.example.pocket_card.ModelClass;

public class ImageObject {

    String name;
    long id;
    double size;

    public ImageObject() {
    }

    public ImageObject(String name, long id, double size) {
        this.name = name;
        this.id = id;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
