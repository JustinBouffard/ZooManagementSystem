package org.example.zoomanagementsystem.Model;

public class Animal {
    private final String aName;
    private final int aAge;

    public Animal(String pName, int pAge) {
        this.aName = pName;
        this.aAge = pAge;
    }

    public String getName() {
        return this.aName;
    }

    public int getAge() {
        return this.aAge;
    }
}
