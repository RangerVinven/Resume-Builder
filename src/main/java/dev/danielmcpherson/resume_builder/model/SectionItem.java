package dev.danielmcpherson.resume_builder.model;

import java.util.List;

public class SectionItem {
    private String title;
    private Boolean show_bullets = true;
    private String subtitle;
    private String meta;
    private String meta_subtitle;
    private List<String> bullet_points;

    public SectionItem() {}

    public SectionItem(String title, String subtitle, String meta, List<String> bullet_points, String meta_subtitle) {
        this.title = title;
        this.subtitle = subtitle;
        this.meta = meta;
        this.bullet_points = bullet_points;
        this.meta_subtitle = meta_subtitle;
    }

    public SectionItem(String title, String subtitle, String meta, List<String> bullet_points, Boolean show_bullets) {
        this.title = title;
        this.subtitle = subtitle;
        this.meta = meta;
        this.bullet_points = bullet_points;
        this.show_bullets = show_bullets;
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

    public Boolean getShow_bullets() {
        return show_bullets;
    }

    public void setShow_bullets(Boolean show_bullets) {
        this.show_bullets = show_bullets;
    }

    public String getMeta_subtitle() {
        return meta_subtitle;
    }

    public void setMeta_subtitle(String meta_subtitle) {
        this.meta_subtitle = meta_subtitle;
    }
}

