package ru.vasyukov.listener;

import io.qameta.allure.restassured.AllureRestAssured;

/**
 * Листенер RestAssured для запросов и ответов
 */
public class MyListener {
    public static AllureRestAssured myListener() {
        return new AllureRestAssured();
    }
}
