package dev.danielmcpherson.resume_builder.model;

import java.util.List;

public class SectionItem {
    private String title;
    private String subtitle;
    private String meta;
    private List<String> bullet_points;

    public SectionItem() {}

    public SectionItem(String title, String subtitle, String meta, List<String> bullet_points) {
        this.title = title;
        this.subtitle = subtitle;
        this.meta = meta;
        this.bullet_points = bullet_points;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public List<String> getBullet_points() {
        return bullet_points;
    }

    public void setBullet_points(List<String> bullet_points) {
        this.bullet_points = bullet_points;
    }
}

