package org.example.zoomanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class CompositeEnclosureCollection implements EnclosureCollection {
    private final String aAreaName;
    private List<EnclosureCollection> aEnclosureCollectionList = new ArrayList<EnclosureCollection>();

    public CompositeEnclosureCollection(String pAreaName) {
        this.aAreaName = pAreaName;
    }

    public String display() {
        return "";
    }

    public void addCollection(EnclosureCollection pEnclosureCollection) {
        aEnclosureCollectionList.add(pEnclosureCollection);
    }
}
