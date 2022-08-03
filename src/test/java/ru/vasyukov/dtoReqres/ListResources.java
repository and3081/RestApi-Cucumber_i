package ru.vasyukov.dtoReqres;

import java.util.List;

/** DTO для страницы ресурсов */
@SuppressWarnings("unused")
public class ListResources {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<Resource> data;
    private Support support;

    public ListResources() {}

    public ListResources(int page, int per_page, int total, int total_pages, List<Resource> data, Support support) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
        this.support = support;
    }

    public int getPage() {return page;}
    public int getPer_page() {return per_page;}
    public int getTotal() {return total;}
    public int getTotal_pages() {return total_pages;}
    public List<Resource> getData() {return data;}
    public Support getSupport() {return support;}
}
