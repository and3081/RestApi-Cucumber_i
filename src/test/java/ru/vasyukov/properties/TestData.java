package ru.vasyukov.properties;

import org.aeonbits.owner.ConfigFactory;

public class TestData {
    /**
     * static метод для работы с проперти из файла props.properties
     */
    public static Props props = ConfigFactory.create(Props.class);
}
