package ru.vasyukov.dtoReqres;

/** DTO для должности юзера */
@SuppressWarnings("unused")
public class UserJobUpdate {
    private String name;
    private String job;
    private String updatedAt;

    public UserJobUpdate() {}

    public UserJobUpdate(String name, String job, String updatedAt) {
        this.name = name;
        this.job = job;
        this.updatedAt = updatedAt;
    }

    public String getName() {return name;}
    public String getJob() {return job;}
    public String getUpdatedAt() {return updatedAt;}
}
