package dev.danielmcpherson.resume_builder.model;

import java.util.List;

public class Section {
    private String name;
    private List<SectionItem> items;

    public Section() {}

    public Section(String name, List<SectionItem> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SectionItem> getItems() {
        return items;
    }

    public void setItems(List<SectionItem> items) {
        this.items = items;
    }
}

