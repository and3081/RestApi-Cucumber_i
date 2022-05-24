package ru.vasyukov.listener;

import io.qameta.allure.restassured.AllureRestAssured;

public class MyListener {
    public static AllureRestAssured myListener() {
        return new AllureRestAssured();
    }
}
