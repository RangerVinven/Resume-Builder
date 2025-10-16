package dev.danielmcpherson.resume_builder.model;

import java.util.List;

public class JobAnalysis {

    private String analysis;
    private String companyName;
    private String jobType;
    private String workStyle;
    private List<String> requiredSkills;
    private List<String> requiredProgrammingLanguages;
    private List<String> requiredSpokenLanguages;
    private List<String> certifications;
    private List<String> teamValues;
    private List<String> allOtherRequirements;
    private String otherNotes;

    // Getters and Setters
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<String> getAllOtherRequirements() {
        return allOtherRequirements;
    }

    public void setAllOtherRequirements(List<String> allOtherRequirements) {
        this.allOtherRequirements = allOtherRequirements;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getWorkStyle() {
        return workStyle;
    }

    public void setWorkStyle(String workStyle) {
        this.workStyle = workStyle;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public List<String> getRequiredProgrammingLanguages() {
        return requiredProgrammingLanguages;
    }

    public void setRequiredProgrammingLanguages(List<String> requiredProgrammingLanguages) {
        this.requiredProgrammingLanguages = requiredProgrammingLanguages;
    }

    public List<String> getRequiredSpokenLanguages() {
        return requiredSpokenLanguages;
    }

    public void setRequiredSpokenLanguages(List<String> requiredSpokenLanguages) {
        this.requiredSpokenLanguages = requiredSpokenLanguages;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }

    public List<String> getTeamValues() {
        return teamValues;
    }

    public void setTeamValues(List<String> teamValues) {
        this.teamValues = teamValues;
    }

    public String getOtherNotes() {
        return otherNotes;
    }

    public void setOtherNotes(String otherNotes) {
        this.otherNotes = otherNotes;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getAnalysis() {
        return analysis;
    }

}
