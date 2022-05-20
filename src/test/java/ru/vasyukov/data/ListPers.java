package ru.vasyukov.data;

import java.util.List;
import java.util.Map;

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
