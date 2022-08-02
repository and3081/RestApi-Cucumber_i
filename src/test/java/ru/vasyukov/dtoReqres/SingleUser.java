package ru.vasyukov.dtoReqres;

import java.util.List;

/** DTO для юзера */
@SuppressWarnings("unused")
public class SingleUser {
    private User data;
    private Support support;

    public SingleUser() {}

    public SingleUser(User data, Support support) {
        this.data = data;
        this.support = support;
    }

    public User getData() { return data; }
    public Support getSupport() {return support;}
}
