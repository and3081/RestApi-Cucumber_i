package ru.vasyukov.dto;

public class Origin {
    private String name;
    private String url;

    public Origin() {}

    public Origin(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() { return name; }

    public String getUrl() { return url; }
}