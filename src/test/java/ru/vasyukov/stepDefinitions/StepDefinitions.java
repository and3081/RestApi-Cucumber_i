package ru.vasyukov.stepDefinitions;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import ru.vasyukov.properties.TestData;
import ru.vasyukov.steps.ApiSteps;
import ru.vasyukov.steps.StepStorage;

public class StepDefinitions extends ApiSteps {
    private final StepStorage stepStorage = new StepStorage();

    @Дано("Находим ID персонажа1 по имени")
    public void findCheckPersonIdForName() {
        stepStorage.setPersonFirstId(findPersonIdForName(TestData.application.personName()));
        Assertions.assertNotNull(stepStorage.getPersonFirstId(),
                "ID персонажа '" + TestData.application.personName() + "' не найден");
    }

    @Когда("Находим персонажа1 по ID и проверяем у него наличие эпизодов")
    public void findFirstPersonCheckEpisodes() {
        stepStorage.setPersonFirst(getPerson(stepStorage.getPersonFirstId()));
        Assertions.assertFalse(stepStorage.getPersonFirst().getEpisode().isEmpty(),
                "Не найдены эпизоды у ID " + stepStorage.getPersonFirstId());
    }

    @Затем("Находим последний эпизод и проверяем у него наличие персонажей")
    public void findLastEpisodeCheckPersons() {
        stepStorage.setLastEpisode(getLastEpisode(stepStorage.getPersonFirst()));
        Assertions.assertFalse(stepStorage.getLastEpisode().getCharacters().isEmpty(),
                "Не найдены персонажи у эпизода");
    }

    @Затем("Находим последнего персонажа2")
    public void findLastPerson() {
        stepStorage.setPersonSecond(getLastPerson(stepStorage.getLastEpisode()));
    }

    @Тогда("Сверяем расу и локацию персонажа1 и персонажа2")
    public void assertTwoPersons() {
        vewPerson(stepStorage.getPersonFirst().getId(),
                stepStorage.getPersonFirst().getName(),
                stepStorage.getPersonFirst().getSpecies(),
                stepStorage.getPersonFirst().getLocation().getName());
        vewPerson(stepStorage.getPersonSecond().getId(),
                stepStorage.getPersonSecond().getName(),
                stepStorage.getPersonSecond().getSpecies(),
                stepStorage.getPersonSecond().getLocation().getName());
        comparePersons(stepStorage);
    }

    @Дано("Создаем файл с данными для запроса")
    public void createCheckJsonFile() {
        stepStorage.setFilename(TestData.application.filename());
        Assertions.assertTrue(createJsonFile(TestData.application.filename()),
                "Файл " + TestData.application.filename() + " не создан");
    }

    @Когда("Создаем пользователя с данными из файла")
    public void createUserFromFile() {
        stepStorage.setRequestJson(modifyBodyJson(readBodyJson(stepStorage.getFilename())));
        stepStorage.setResponseJson(createUser(stepStorage.getRequestJson()));
    }

    @Тогда("Проверяем json ответа")
    public void assertResponse() {
        stepStorage.getRequestJson().put("id",
                stepStorage.getResponseJson().optString("id", "нет"));
        stepStorage.getRequestJson().put("createdAt",
                stepStorage.getResponseJson().optString("createdAt", "нет"));
        Assertions.assertTrue(stepStorage.getRequestJson()
                        .similar(stepStorage.getResponseJson()),
                "Json ответа не соответствует ожидаемому");
    }
}
