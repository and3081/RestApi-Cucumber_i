package ru.vasyukov.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import ru.vasyukov.properties.TestData;
import ru.vasyukov.steps.Storage;

import static ru.vasyukov.steps.ApiStepsRick.*;
import static ru.vasyukov.steps.ApiStepsReqres.*;

/** Класс методов-определений для аннотаций кукумбера */
public class StepDefinitions {
    public Storage storage;

    @Before
    public void createStorage() {
        storage = new Storage();
    }

    @After
    public void dropStorage() {
        if (storage != null) {
            storage = null;
        }
    }

    @Дано("Находим ID персонажа1 по имени")
    public void findCheckPersonIdForName() {
        storage.setPersonFirstId(findPersonIdForName(TestData.application.personName()));
        Assertions.assertNotNull(storage.getPersonFirstId(),
                "ID персонажа '" + TestData.application.personName() + "' не найден");
    }

    @Когда("Находим персонажа1 по ID и проверяем у него наличие эпизодов")
    public void findFirstPersonCheckEpisodes() {
        storage.setPersonFirst(getPerson(storage.getPersonFirstId()));
        Assertions.assertFalse(storage.getPersonFirst().getEpisode().isEmpty(),
                "Не найдены эпизоды у ID " + storage.getPersonFirstId());
    }

    @Затем("Находим последний эпизод и проверяем у него наличие персонажей")
    public void findLastEpisodeCheckPersons() {
        storage.setLastEpisode(getLastEpisode(storage.getPersonFirst()));
        Assertions.assertFalse(storage.getLastEpisode().getCharacters().isEmpty(),
                "Не найдены персонажи у эпизода");
    }

    @Затем("Находим последнего персонажа2")
    public void findLastPerson() {
        storage.setPersonSecond(getLastPerson(storage.getLastEpisode()));
    }

    @Тогда("Сверяем расу и локацию персонажа1 и персонажа2")
    public void assertTwoPersons() {
        viewPerson(storage.getPersonFirst().getId(),
                storage.getPersonFirst().getName(),
                storage.getPersonFirst().getSpecies(),
                storage.getPersonFirst().getLocation().getName());
        viewPerson(storage.getPersonSecond().getId(),
                storage.getPersonSecond().getName(),
                storage.getPersonSecond().getSpecies(),
                storage.getPersonSecond().getLocation().getName());
        comparePersons(storage);
    }

    @Дано("Создаем файл с данными для запроса")
    public void createCheckJsonFile() {
        storage.setFilename(TestData.application.filename());
        Assertions.assertTrue(createJsonFile(storage.getFilename()),
                "Файл " + storage.getFilename() + " не создан");
    }

    @Когда("Создаем пользователя с данными из файла")
    public void createUserFromFile() {
        storage.setRequestJson(modifyBodyJson(readBodyJson(storage.getFilename())));
        storage.setResponseJson(createUser(storage.getRequestJson()));
    }

    @Тогда("Проверяем json ответа")
    public void assertResponse() {
        attachJsonAnnotationAllure(storage.getResponseJson());
        storage.getRequestJson().put("id",
                storage.getResponseJson().optString("id", "нет"));
        storage.getRequestJson().put("createdAt",
                storage.getResponseJson().optString("createdAt", "нет"));
        Assertions.assertTrue(storage.getRequestJson()
                        .similar(storage.getResponseJson()),
                "Json ответа не соответствует ожидаемому");
    }
}
