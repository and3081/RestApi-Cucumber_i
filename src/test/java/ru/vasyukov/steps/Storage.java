package ru.vasyukov.steps;

import org.json.JSONObject;
import ru.vasyukov.dtoReqres.UserJob;
import ru.vasyukov.dtoRick.Episode;
import ru.vasyukov.dtoRick.Person;

/** DTO для тестовых шагов */
public class Storage {
    /** Для тестов rickandmortyapi.com */
    private Integer personFirstId;
    private Person PersonFirst;
    private Episode lastEpisode;
    private Person PersonSecond;

    /** Для тестов reqres.in */
    private String filename;
    private JSONObject requestJson;
    private UserJob userJob;

    public Integer getPersonFirstId() { return personFirstId; }
    public void setPersonFirstId(Integer personFirstId) { this.personFirstId = personFirstId; }

    public Person getPersonFirst() { return PersonFirst; }
    public void setPersonFirst(Person personFirst) { PersonFirst = personFirst; }

    public Episode getLastEpisode() { return lastEpisode; }
    public void setLastEpisode(Episode lastEpisode) { this.lastEpisode = lastEpisode; }

    public Person getPersonSecond() { return PersonSecond; }
    public void setPersonSecond(Person personSecond) { PersonSecond = personSecond; }

    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    public JSONObject getRequestJson() { return requestJson; }
    public void setRequestJson(JSONObject requestJson) { this.requestJson = requestJson; }

    public UserJob getUser() { return userJob; }
    public void setUser(UserJob userJob) { this.userJob = userJob; }
}
