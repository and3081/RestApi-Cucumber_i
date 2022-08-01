package ru.vasyukov.dtoRick;

/**
 * DTO для локации персонажа
 */
public class Location {
    private String name;
    private String url;

    public Location() {}

    public Location(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() { return name; }

    public String getUrl() { return url; }
}
