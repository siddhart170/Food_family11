package com.example.food_family.Models;

public class EnteranceModel {
    String description, name, prise, purl;

    public EnteranceModel() {
    }

    public EnteranceModel(String description, String name, String prise, String purl) {
        this.description = description;
        this.name = name;
        this.prise = prise;
        this.purl = purl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrise() {
        return prise;
    }

    public void setPrise(String prise) {
        this.prise = prise;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
