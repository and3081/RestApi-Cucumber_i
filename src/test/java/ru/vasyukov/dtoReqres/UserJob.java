package ru.vasyukov.dtoReqres;

/** DTO для должности юзера */
@SuppressWarnings("unused")
public class UserJob {
    private String name;
    private String job;
    private String id;
    private String createdAt;

    public UserJob() {}

    public UserJob(String name, String job, String id, String createdAt) {
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
