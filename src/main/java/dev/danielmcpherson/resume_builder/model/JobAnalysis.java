package dev.danielmcpherson.resume_builder.model;

import java.util.List;

public class JobAnalysis {

    private String analysis;
    private String companyName;
    private String jobTitle;
    private String jobType;
    private String seniorityLevel;
    private String workStyle;
    private String industry;
    private List<String> requiredSkills;
    private List<String> technicalSkills;
    private List<String> requiredProgrammingLanguages;
    private List<String> requiredSpokenLanguages;
    private List<String> certifications;
    private List<String> qualifications;
    private String yearsOfExperience;
    private List<String> teamValues;
    private List<String> otherRequirements;
    private String notes;

    // Getters and Setters
    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSeniorityLevel() {
        return seniorityLevel;
    }

    public void setSeniorityLevel(String seniorityLevel) {
        this.seniorityLevel = seniorityLevel;
    }

    public String getWorkStyle() {
        return workStyle;
    }

    public void setWorkStyle(String workStyle) {
        this.workStyle = workStyle;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public List<String> getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(List<String> technicalSkills) {
        this.technicalSkills = technicalSkills;
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

    public List<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<String> getTeamValues() {
        return teamValues;
    }

    public void setTeamValues(List<String> teamValues) {
        this.teamValues = teamValues;
    }

    public List<String> getOtherRequirements() {
        return otherRequirements;
    }

    public void setOtherRequirements(List<String> otherRequirements) {
        this.otherRequirements = otherRequirements;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
