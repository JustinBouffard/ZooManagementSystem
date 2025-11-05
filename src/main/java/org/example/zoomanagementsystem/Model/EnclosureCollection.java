package org.example.zoomanagementsystem.Model;

import java.util.List;

public interface EnclosureCollection {
    public String getName();
    public List<EnclosureCollection> getCollections();
    public List<Animal> getAnimals();
}
