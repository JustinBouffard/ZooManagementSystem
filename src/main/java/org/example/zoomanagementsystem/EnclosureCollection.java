package org.example.zoomanagementsystem;

import java.util.List;

public interface EnclosureCollection {
    public String getName();
    public List<EnclosureCollection> getCollections();
    public List<Animal> getAnimals();
}
