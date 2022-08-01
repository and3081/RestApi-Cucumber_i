package ru.vasyukov.dtoReqres;

import ru.vasyukov.dtoRick.Location;
import ru.vasyukov.dtoRick.Origin;

import java.util.List;

/** DTO для юзера */
public class User {
    private String name;
    private String job;
    private String id;
    private String createdAt;

    public User() {}

    public User(String name, String job, String id, String createdAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
    }

    public String getName() {return name;}

    public String getJob() {return job;}

    public String getId() {return id;}

    public String getCreatedAt() {return createdAt;}
}
