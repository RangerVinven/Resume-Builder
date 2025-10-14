package dev.danielmcpherson.resume_builder.model;

import java.util.List;

public class PersonInformation {
    private String name;
    private List<String> details;

    public PersonInformation() {}

    public PersonInformation(String name, List<String> details) {
        this.name = name;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
