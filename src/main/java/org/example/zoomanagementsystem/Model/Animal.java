package org.example.zoomanagementsystem.Model;

public class Animal {
    private final String aName;
    private final double aAge;

    public Animal(String pName, double pAge) {
        this.aName = pName;
        this.aAge = pAge;
    }

    public String getName() {
        return this.aName;
    }

    public double getAge() {
        return this.aAge;
    }
}
