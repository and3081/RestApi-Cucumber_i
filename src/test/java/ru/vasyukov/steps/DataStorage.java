package ru.vasyukov.steps;

import org.json.JSONObject;
import ru.vasyukov.data.Episode;
import ru.vasyukov.data.Person;

public class DataStorage {
    private Integer personFirstId;
    private Person PersonFirst;
    private Episode lastEpisode;
    private Person PersonSecond;

    private JSONObject requestJson;
    private JSONObject responseJson;

    public Integer getPersonFirstId() { return personFirstId; }
    public void setPersonFirstId(Integer personFirstId) { this.personFirstId = personFirstId; }

    public Person getPersonFirst() { return PersonFirst; }
    public void setPersonFirst(Person personFirst) { PersonFirst = personFirst; }

    public Episode getLastEpisode() { return lastEpisode; }
    public void setLastEpisode(Episode lastEpisode) { this.lastEpisode = lastEpisode; }

    public Person getPersonSecond() { return PersonSecond; }
    public void setPersonSecond(Person personSecond) { PersonSecond = personSecond; }

    public JSONObject getRequestJson() { return requestJson; }
    public void setRequestJson(JSONObject requestJson) { this.requestJson = requestJson; }

    public JSONObject getResponseJson() { return responseJson; }
    public void setResponseJson(JSONObject responseJson) { this.responseJson = responseJson; }
}
