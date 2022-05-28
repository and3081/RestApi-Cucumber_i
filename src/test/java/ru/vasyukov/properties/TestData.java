package ru.vasyukov.properties;

import org.aeonbits.owner.ConfigFactory;

public class TestData {
    /**
     * static метод для работы с проперти из файла application.properties
     */
    public static Application application = ConfigFactory.create(Application.class);
}
