package ru.vasyukov.data;

import java.util.List;
import java.util.Map;

public class Person {
    private int id;
    private String name;
    private String status;  // 'Alive', 'Dead' or 'unknown'
    private String species;  // "Human",...
    private String type;
    private String gender;  // 'Female', 'Male', 'Genderless' or 'unknown'
    private Map<String,Object> origin;  // "name", "url"
    private Map<String,Object> location;  // "name", "url"
    private String image;
    private List<String> episode;
    private String url;
    private String created;

    public Person() { super(); }

    public Person(int id, String name, String status, String species, String type,
                  String gender, Map<String, Object> origin, Map<String, Object> location,
                  String image, List<String> episode, String url, String created) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.origin = origin;
        this.location = location;
        this.image = image;
        this.episode = episode;
        this.url = url;
        this.created = created;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getStatus() { return status; }

    public String getSpecies() { return species; }

    public String getType() { return type; }

    public String getGender() { return gender; }

    public Map<String, Object> getOrigin() { return origin; }

    public Map<String, Object> getLocation() { return location; }

    public String getImage() { return image; }

    public List<String> getEpisode() { return episode; }

    public String getUrl() { return url; }

    public String getCreated() { return created; }
}
