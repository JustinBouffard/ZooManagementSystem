package org.example.zoomanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class Enclosure implements EnclosureCollection {
    private final String aName;
    private List<Animal> aAnimalList = new ArrayList<Animal>();

    public Enclosure(String pName) {
        this.aName = pName;
    }

    public void addAnimal(Animal pAnimal) {
        this.aAnimalList.add(pAnimal);
    }

    public String display() {
        return "";
    }
}
