package ru.vasyukov.dtoReqres;

/** DTO для ресурса */
@SuppressWarnings("unused")
public class SingleResource {
    private Resource data;
    private Support support;

    public SingleResource() {}

    public SingleResource(Resource data, Support support) {
        this.data = data;
        this.support = support;
    }

    public Resource getData() { return data; }
    public Support getSupport() {return support;}
}
