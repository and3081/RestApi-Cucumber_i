package ru.vasyukov.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import ru.vasyukov.properties.TestData;
import ru.vasyukov.steps.Storage;

import static ru.vasyukov.steps.ApiStepsReqres.*;

/** Класс методов-определений для аннотаций кукумбера Reqres.feature */
public class StepDefinitionsReqres {
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

    @Дано("Создаем файл с данными для запроса {string}")
    public void createCheckJsonFile(String strJson) {
        storage.setFilename(TestData.application.filename());
        Assertions.assertTrue(createJsonFile(storage.getFilename(), strJson),
                "Файл " + storage.getFilename() + " не создан");
    }

    @И("Создаем json из файла и дополняем {string} {string}")
    public void modifyFromFile(String key, String value) {
        storage.setRequestJson(modifyBodyJson(readBodyJson(storage.getFilename()), key, value));
    }

    @Когда("Создаем пользователя с данными из файла")
    public void createUserFromFile() {
        storage.setUser(createUser(storage.getRequestJson()));
    }

    @Тогда("Проверяем json ответа")
    public void assertResponse() {
        JSONObject jsonOut = new JSONObject(storage.getUser());
        attachJsonAnnotationAllure(jsonOut);
        assertJsonToJson(storage.getRequestJson(), jsonOut);
    }
}
