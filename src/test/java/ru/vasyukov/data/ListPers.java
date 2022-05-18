package ru.vasyukov.data;

import org.json.JSONPropertyIgnore;

import java.util.List;
import java.util.Map;

public class ListPers {
    private Map<String,Object> info;
    private List<Map<String,Object>> results;

    public ListPers() { super(); }

    public ListPers(Map<String,Object> info, List<Map<String,Object>> results) {
        this.info = info;
        this.results = results;
    }

    public Map<String,Object> getInfo() { return info; }

    public List<Map<String,Object>> getResults() { return results; }
}
