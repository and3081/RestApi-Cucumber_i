package ru.vasyukov.steps;

import org.json.JSONObject;
import ru.vasyukov.dto.Episode;
import ru.vasyukov.dto.Person;

/**
 * DTO для тестовых шагов
 */
public class StepStorage {
    /**
     * Для теста rickandmortyapi.com
     */
    private Integer personFirstId;
    private Person PersonFirst;
    private Episode lastEpisode;
    private Person PersonSecond;

    /**
     * Для теста reqres.in
     */
    private String filename;
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

    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    public JSONObject getRequestJson() { return requestJson; }
    public void setRequestJson(JSONObject requestJson) { this.requestJson = requestJson; }

    public JSONObject getResponseJson() { return responseJson; }
    public void setResponseJson(JSONObject responseJson) { this.responseJson = responseJson; }
}
