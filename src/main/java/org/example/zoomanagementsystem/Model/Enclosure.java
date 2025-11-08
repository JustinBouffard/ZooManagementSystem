package org.example.zoomanagementsystem.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Enclosure implements EnclosureCollection {
    private final String aName;
    private final List<Animal> aAnimalList = new ArrayList<Animal>();

    public Enclosure(String pName) {
        this.aName = pName;
    }

    public void addAnimal(Animal pAnimal) {
        this.aAnimalList.add(pAnimal);
    }

    public void removeAnimal(Animal pAnimal) {
        this.aAnimalList.remove(pAnimal);
    }

    @Override
    public String getName() {
        return this.aName;
    }

    @Override
    public List<EnclosureCollection> getCollections() {
        return List.of();
    }

    @Override
    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(this.aAnimalList);
    }

    @Override
    public String toString() {
        return this.aName;
    }
}
