package ru.vasyukov.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Класс провайдера данных для тестов
 */
public class TestParams {
    protected static Stream<Arguments> providerTest1() {
        return Stream.of(arguments( "Morty Smith"));
    }
}
