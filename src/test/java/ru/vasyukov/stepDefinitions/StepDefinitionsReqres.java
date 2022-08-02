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

    @Дано("Запрашиваем все страницы юзеров")
    public void readAllUsers() {
        queryListUsers(storage);
    }

    @Тогда("Проверяем количество юзеров {int}")
    public void assertCountUsers(int count) {
        Assertions.assertEquals(count, storage.getCount(),
                "Количество юзеров не правильно");
    }

    @И("Проверяем уникальность ID и email юзеров")
    public void assertIDAndEmail() {
        assertListUsers(storage.getListUsers());
    }

    @Дано("Запрашиваем юзера с ID {int}, статус {int}")
    public void requestID(int id, int status) {
        requestForID(storage, id, status);
    }

    @Тогда("Проверяем юзера: ID {int}, first_name {string}, last_name {string}")
    public void assertID(int id, String firstName, String lastName) {
        Assertions.assertEquals(id, storage.getSingleUser().getData().getId(),
                "Неправильный ID");
        Assertions.assertEquals(firstName, storage.getSingleUser().getData().getFirst_name(),
                "Неправильный firstName");
        Assertions.assertEquals(lastName, storage.getSingleUser().getData().getLast_name(),
                "Неправильный lastName");
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
        storage.setUserJob(createUser(storage.getRequestJson()));
    }

    @Тогда("Проверяем json ответа")
    public void assertResponse() {
        JSONObject jsonOut = new JSONObject(storage.getUserJob());
        attachJsonAnnotationAllure(jsonOut);
        assertJsonToJson(storage.getRequestJson(), jsonOut);
    }
}
