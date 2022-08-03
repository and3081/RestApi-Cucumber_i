package ru.vasyukov.steps;

import org.json.JSONObject;
import ru.vasyukov.dtoReqres.*;
import ru.vasyukov.dtoRick.Episode;
import ru.vasyukov.dtoRick.Person;

import java.util.List;

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
    private int count;
    private List<User> listUsers;
    private SingleUser singleUser;
    private List<Resource> listResources;
    private SingleResource singleResource;

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

    public UserJob getUserJob() { return userJob; }
    public void setUserJob(UserJob userJob) { this.userJob = userJob; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public List<User> getListUsers() { return listUsers; }
    public void setListUsers(List<User> listUsers) { this.listUsers = listUsers; }

    public SingleUser getSingleUser() { return singleUser; }
    public void setSingleUser(SingleUser singleUser) { this.singleUser = singleUser; }

    public List<Resource> getListResources() { return listResources; }
    public void setListResources(List<Resource> listResources) { this.listResources = listResources; }

    public SingleResource getSingleResource() { return singleResource; }
    public void setSingleResource(SingleResource singleResource) { this.singleResource = singleResource; }
}
