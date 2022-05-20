package ru.vasyukov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.vasyukov.steps.ApiSteps;

public class Tests extends ApiSteps {
    @DisplayName("Тест Сверка двух персонажей")
    @ParameterizedTest(name = "{arguments}")
    @MethodSource("ru.vasyukov.data.TestParams#providerTest1")
    public void TestTwoPersons(String nameFirstPers) {
        findCheckPersonIdForName(nameFirstPers);
        findFirstPersonCheckEpisodes();
        findLastEpisodeCheckPersons();
        findSecondPerson();
        assertTwoPersons();
    }

    @DisplayName("Тест Проверка json")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("ru.vasyukov.data.TestParams#providerTest2")
    public void TestJson(String filename) {
        createCheckJsonFile(filename);
        createUserFromFile(filename);
        assertResponse();
    }
}
