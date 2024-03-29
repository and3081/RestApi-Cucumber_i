package ru.vasyukov.dtoRick;

import java.util.List;

/**
 * DTO для эпизода
 */
public class Episode {
    private int id;
    private String name;
    private String air_date;
    private String episode;
    private List<String> characters;
    private String url;
    private String created;

    public Episode() {}

    public Episode(int id, String name, String air_date, String episode, List<String> characters,
                   String url, String created) {
        this.id = id;
        this.name = name;
        this.air_date = air_date;
        this.episode = episode;
        this.characters = characters;
        this.url = url;
        this.created = created;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getAir_date() { return air_date; }

    public String getEpisode() { return episode; }

    public List<String> getCharacters() { return characters; }

    public String getUrl() { return url; }

    public String getCreated() { return created; }
}
