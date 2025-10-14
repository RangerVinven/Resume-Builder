package dev.danielmcpherson.resume_builder.model;

import java.util.List;

public class Resume {
    private PersonInformation person_information;
    private List<Section> sections;

    public Resume() {}

    public PersonInformation getPerson_information() {
        return person_information;
    }
    public void setPerson_information(PersonInformation person_information) {
        this.person_information = person_information;
    }

    public List<Section> getSections() {
        return sections;
    }
    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
