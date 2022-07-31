package ru.vasyukov.steps;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import ru.vasyukov.properties.TestData;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static ru.vasyukov.specifications.Specification.*;

/** Класс тестовых шагов Reqres */
public class ApiStepsReqres {

    @Step("Создание файла Json для запроса {filename}")
    public static boolean createJsonFile(String filename) {
        JSONObject json = new JSONObject();
        json.put("name", "Potato");
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

    @Step("Изменение Json запроса")
    public static JSONObject modifyBodyJson(JSONObject json) {
        json.put("job", "Eat");
        attachJsonAnnotationAllure(json);
        return json;
    }

    @Step("Создание юзера {body}")
    public static JSONObject createUser(JSONObject body) {
        return new JSONObject(given()
                .spec(requestSpecReqres())
                .body(body.toString())
                .when()
                .post(TestData.application.apiUsers())
                .then()
                //.log().body()
                .spec(responseSpecCheckCreate())
                .extract().body().asString());
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
