package ru.vasyukov.steps;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import ru.vasyukov.dtoReqres.*;
import ru.vasyukov.properties.TestData;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static ru.vasyukov.specifications.Specification.*;

/** Класс тестовых шагов Reqres */
public class ApiStepsReqres {

    @Step("Запрос всех страниц юзеров, сбор юзеров")
    public static void queryListUsers(Storage storage) {
        int numberPage = 1;
        int count;
        ListUsers list;
        List<User> users = new ArrayList<>();

        do {
            list = given()
                    .spec(requestSpecReqres())
                    .queryParam("page", numberPage)
                    .when()
                    .get(TestData.application.apiUsers())
                    .then()
                    //.log().body()
                    .spec(responseSpecCheckListUsers())
                    .extract().body().as(ListUsers.class);
            numberPage++;
            users.addAll(list.getData());
            count = list.getTotal();
        } while (numberPage <= list.getTotal_pages());
        storage.setCount(count);
        storage.setListUsers(users);
    }

    @Step("Проверка уникальности ID и email юзеров")
    public static void assertListUsers(List<User> list) {
        List<Integer> duplicateID = list.stream()
                .map(User::getId)
                .collect(Collectors.toMap(el-> el, el-> 1, (el1,el2)-> el1+1))
                .entrySet().stream()
                .filter(el-> el.getValue()>1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        List<String> duplicateEmail = list.stream()
                .map(User::getEmail)
                .collect(Collectors.toMap(el-> el, el-> 1, (el1,el2)-> el1+1))
                .entrySet().stream()
                .filter(el-> el.getValue()>1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        Assertions.assertEquals(0, duplicateID.size(),
                "Найдены дубликаты ID: " + duplicateID);
        Assertions.assertEquals(0, duplicateEmail.size(),
                "Найдены дубликаты Email: " + duplicateEmail);
    }

    public static void requestForIDUser(Storage storage, int id, int status) {
        SingleUser user = given()
                .spec(requestSpecReqres())
                .when()
                .get(TestData.application.apiUsers() + "/" + id)
                .then()
                //.log().body()
                .spec(responseSpecCheckUser(status))
                .extract().body().as(SingleUser.class);
        storage.setSingleUser(user);
    }

    @Step("Запрос всех страниц ресурсов, сбор ресурсов")
    public static void queryListResources(Storage storage) {
        int numberPage = 1;
        int count;
        ListResources list;
        List<Resource> resources = new ArrayList<>();

        do {
            list = given()
                    .spec(requestSpecReqres())
                    .queryParam("page", numberPage)
                    .when()
                    .get(TestData.application.apiResources())
                    .then()
                    //.log().body()
                    .spec(responseSpecCheckListResources())
                    .extract().body().as(ListResources.class);
            numberPage++;
            resources.addAll(list.getData());
            count = list.getTotal();
        } while (numberPage <= list.getTotal_pages());
        storage.setCount(count);
        storage.setListResources(resources);
    }

    @Step("Проверка уникальности ID и name ресурсов")
    public static void assertListResources(List<Resource> list) {
        List<Integer> duplicateID = list.stream()
                .map(Resource::getId)
                .collect(Collectors.toMap(el-> el, el-> 1, (el1,el2)-> el1+1))
                .entrySet().stream()
                .filter(el-> el.getValue()>1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        List<String> duplicateName = list.stream()
                .map(Resource::getName)
                .collect(Collectors.toMap(el-> el, el-> 1, (el1,el2)-> el1+1))
                .entrySet().stream()
                .filter(el-> el.getValue()>1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        Assertions.assertEquals(0, duplicateID.size(),
                "Найдены дубликаты ID: " + duplicateID);
        Assertions.assertEquals(0, duplicateName.size(),
                "Найдены дубликаты Name: " + duplicateName);
    }

    public static void requestForIDResource(Storage storage, int id, int status) {
        SingleResource resource = given()
                .spec(requestSpecReqres())
                .when()
                .get(TestData.application.apiResources() + "/" + id)
                .then()
                //.log().body()
                .spec(responseSpecCheckResource(status))
                .extract().body().as(SingleResource.class);
        storage.setSingleResource(resource);
    }

    @Step("Создание файла Json для запроса {filename}")
    public static boolean createJsonFile(String filename, String strJson) {
        JSONObject json = new JSONObject(strJson);
        boolean isWrite = writeJsonFile(filename, json);
        attachFileAnnotationAllure(filename);
        attachFileMethodAllure(filename);
        return isWrite;
    }

    @Step("Чтение Json из файла {filename}")
    public static JSONObject readBodyJson(String filename) {
        JSONObject json = readJsonFile(filename);
        Assertions.assertNotNull(json, "Json из файла " + filename + " не прочитан");
        attachJsonAnnotationAllure(json);
        return json;
    }

    @Step("Изменение Json запроса: {key}, {value}")
    public static JSONObject modifyBodyJson(JSONObject json, String key, String value) {
        json.put(key, value);
        attachJsonAnnotationAllure(json);
        return json;
    }

    @Step("Создание юзера-job {body}")
    public static UserJob createUserJob(JSONObject body) {
        return given()
                .spec(requestSpecReqres())
                .body(body.toString())
                .when()
                .post(TestData.application.apiUsers())
                .then()
                //.log().body()
                .spec(responseSpecCheckJobCreate())
                .extract().body().as(UserJob.class);
    }

    public static void assertJsonToJson(JSONObject jsonIn, JSONObject jsonOut) {
        Map<String,Object> mapIn = jsonIn.toMap();
        Map<String,Object> mapOut = jsonOut.toMap();

        for (Map.Entry<String,Object> el : mapIn.entrySet()) {
            Assertions.assertTrue(mapOut.containsKey(el.getKey()),
                    "В Json ответа нет ключа " + el.getKey());
            Assertions.assertEquals(el.getValue(), mapOut.get(el.getKey()),
                    "В Json разные значения по ключу " + el.getKey());
        }
    }

    @Step("Изменение юзера-job ID {id}: {body}")
    public static UserJobUpdate updateCmdUserJob(String cmd, int id, String body) {
        return given()
                .spec(requestSpecReqres())
                .body(body)
                .when()
                .request(cmd, TestData.application.apiUsers() + "/" + id)
                .then()
                //.log().body()
                .spec(responseSpecCheckJobUpdate())
                .extract().body().as(UserJobUpdate.class);
    }

    public static void delUserJob(int id) {
        given()
                .spec(requestSpecReqres())
                .when()
                .delete(TestData.application.apiUsers() + "/" + id)
                .then()
                //.log().body()
                .spec(responseSpecCheckJobDelete());
    }

    public static void registrationEmail(String body, int status) {
        given()
                .spec(requestSpecReqres())
                .body(body)
                .when()
                .post(TestData.application.apiRegister())
                .then()
                //.log().body()
                .spec(responseSpecCheckRegistrationEmail(status));
    }

    public static JSONObject readJsonFile(String filename) {
        try {
            return new JSONObject(new String(Files.readAllBytes(Paths.get(filename))));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean writeJsonFile(String filename, JSONObject json) {
        try(FileWriter file = new FileWriter(filename)) {
            file.write(json.toString(2));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Json", type = "application/json")
    public static byte[] attachJsonAnnotationAllure(JSONObject json) {
        return json.toString(2).getBytes(StandardCharsets.UTF_8);
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Аттач файла '{filename}'", type = "application/json")
    public static byte[] attachFileAnnotationAllure(String filename) {
        try {
            return Files.readAllBytes(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[]{};
        }
    }

    public static void attachFileMethodAllure(String filename) {
        try {
            Allure.addAttachment("Вариант методом: аттач файла '" + filename + "'",
                    "application/json", Files.newInputStream(Paths.get(filename)), ".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
