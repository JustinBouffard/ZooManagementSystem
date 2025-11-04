package org.example.zoomanagementsystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CompositeEnclosureCollection implements EnclosureCollection {
    private final String aAreaName;
    private List<EnclosureCollection> aEnclosureCollectionList = new ArrayList<EnclosureCollection>();

    public CompositeEnclosureCollection(String pAreaName) {
        this.aAreaName = pAreaName;
    }

    public void addCollection(EnclosureCollection pEnclosureCollection) {
        aEnclosureCollectionList.add(pEnclosureCollection);
    }

    public void removeCollection(EnclosureCollection pEnclosureCollection) {
        aEnclosureCollectionList.remove(pEnclosureCollection);
    }

    @Override
    public String getName() {
        return this.aAreaName;
    }

    @Override
    public List<EnclosureCollection> getCollections() {
        return Collections.unmodifiableList(this.aEnclosureCollectionList);
    }

    @Override
    public List<Animal> getAnimals() {
        return List.of();
    }
}
