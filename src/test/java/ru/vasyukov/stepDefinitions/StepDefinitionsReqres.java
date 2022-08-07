package ru.vasyukov.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
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

    @Before("@TestsReqres")
    public void createStorage() {
        storage = new Storage();
    }

    @After("@TestsReqres")
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
    public void requestIDUser(int id, int status) {
        requestForIDUser(storage, id, status);
    }

    @Тогда("Проверяем юзера: ID {int}, first_name {string}, last_name {string}")
    public void assertIDUser(int id, String firstName, String lastName) {
        Assertions.assertEquals(id, storage.getSingleUser().getData().getId(),
                "Неправильный ID");
        Assertions.assertEquals(firstName, storage.getSingleUser().getData().getFirst_name(),
                "Неправильный firstName");
        Assertions.assertEquals(lastName, storage.getSingleUser().getData().getLast_name(),
                "Неправильный lastName");
    }

    @Дано("Запрашиваем все страницы ресурсов")
    public void readAllResources() {
        queryListResources(storage);
    }

    @Тогда("Проверяем количество ресурсов {int}")
    public void assertCountResources(int count) {
        Assertions.assertEquals(count, storage.getCount(),
                "Количество ресурсов не правильно");
    }

    @И("Проверяем уникальность ID и name ресурсов")
    public void assertIDAndName() {
        assertListResources(storage.getListResources());
    }

    @Дано("Запрашиваем ресурс с ID {int}, статус {int}")
    public void requestIDResource(int id, int status) {
        requestForIDResource(storage, id, status);
    }

    @Тогда("Проверяем ресурс: ID {int}, name {string}, color {string}")
    public void assertIDResource(int id, String name, String color) {
        Assertions.assertEquals(id, storage.getSingleResource().getData().getId(),
                "Неправильный ID");
        Assertions.assertEquals(name, storage.getSingleResource().getData().getName(),
                "Неправильный Name");
        Assertions.assertEquals(color, storage.getSingleResource().getData().getColor(),
                "Неправильный Color");
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
    public void createUserJobFromFile() {
        storage.setUserJob(createUserJob(storage.getRequestJson()));
    }

    @Тогда("Проверяем json ответа CREATE")
    public void assertResponseUserJob() {
        JSONObject jsonOut = new JSONObject(storage.getUserJob());
        attachJsonAnnotationAllure(jsonOut);
        assertJsonToJson(storage.getRequestJson(), jsonOut);
    }

    @Дано("Изменяем {string} пользователя {int} на {string}")
    public void updateUserJob(String cmd, int id, String jsonString) {
        if (!cmd.equals("put") && !cmd.equals("patch")) {
            Assertions.fail("Команда изменения должна быть 'put' или 'patch'");
        }
        storage.setRequestJson(new JSONObject(jsonString));
        storage.setUserJobUpdate(updateCmdUserJob(cmd, id, jsonString));
    }

    @Тогда("Проверяем json ответа {string}")
    public void assertResponseUserJobUpdate(String cmd) {
        JSONObject jsonOut = new JSONObject(storage.getUserJobUpdate());
        attachJsonAnnotationAllure(jsonOut);
        assertJsonToJson(storage.getRequestJson(), jsonOut);
    }

    @Дано("Удаляем пользователя {int}")
    public void deleteUserJob(int id) {
        delUserJob(id);
    }

    @Дано("Проверяем запрос регистрации {string}, статус {int}")
    public void registration(String jsonString, int status) {
        registrationEmail(jsonString, status);
    }

    @Дано("Проверяем запрос авторизации {string}, статус {int}")
    public void authorization(String jsonString, int status) {
        authorizationEmail(jsonString, status);
    }
}
