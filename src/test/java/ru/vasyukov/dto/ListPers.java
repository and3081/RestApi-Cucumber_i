package ru.vasyukov.dto;

import java.util.List;

public class ListPers {
    private Info info;
    private List<Person> results;

    public ListPers() {}

    public ListPers(Info info, List<Person> results) {
        this.info = info;
        this.results = results;
    }

    public Info getInfo() { return info; }

    public List<Person> getResults() { return results; }
}
