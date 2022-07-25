package ru.vasyukov.steps;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import ru.vasyukov.dto.Episode;
import ru.vasyukov.dto.ListPers;
import ru.vasyukov.dto.Person;
import ru.vasyukov.properties.TestData;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static ru.vasyukov.specifications.Specification.*;

/**
 * Класс тестовых шагов
 */
public class ApiSteps {

    @Step("Поиск ID персонажа по его имени {namePers}")
    public static Integer findPersonIdForName(String namePers) {
        ListPers listPers;
        int numberPage = 0;
        do {
            numberPage++;
            //System.out.printf("с.%d..\n", numberPage);
            listPers= given()
                    .spec(requestSpecRick())
                    .queryParam("page", numberPage)
                    .when()
                    .get(TestData.application.apiCharacter())
                    .then()
                    //.log().body()
                    .spec(responseSpecCheckListPers())
                    .extract().body().as(ListPers.class);
            List<Object> listId= listPers.getResults().stream()
                    .filter(el-> el.getName().equals(namePers))
                    .limit(1)
                    .map(Person::getId)
                    .collect(Collectors.toList());
            if (listId.size() > 0) return (Integer) listId.get(0);
        } while (listPers.getInfo().getNext() != null);
        return null;
    }

    @Step("Поиск персонажа по ID {idPers}")
    public static Person getPerson(int idPers) {
        return given()
                .spec(requestSpecRick())
                //.log().all()
                .when()
                .get(TestData.application.apiCharacter() + "/" + idPers)
                .then()
                //.log().all()
                .spec(responseSpecCheckPerson())
                .extract().body().as(Person.class);
    }

    @Step("Поиск последнего эпизода у персонажа {person.name}")
    public static Episode getLastEpisode(Person person) {
        String lastEpisode = person.getEpisode().get(person.getEpisode().size()-1);
        return given()
                .spec(requestSpecRick())
                .when()
                .get(lastEpisode)
                .then()
                //.log().body()
                .spec(responseSpecCheckEpisode())
                .extract().body().as(Episode.class);
    }

    @Step("Поиск последнего персонажа у эпизода {episode.name}")
    public static Person getLastPerson(Episode episode) {
        String lastPersonID = episode.getCharacters().get(episode.getCharacters().size() - 1)
                .replaceAll("[^0-9]", "");
        return getPerson(Integer.parseInt(lastPersonID));
    }

    @Step("Создание файла Json для запроса {filename}")
    public static boolean createJsonFile(String filename) {
        JSONObject json = new JSONObject();
        json.put("name", "Potato");
        try(FileWriter file = new FileWriter(filename)) {
            file.write(json.toString(2));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static JSONObject readJsonFile(String filename) {
        try {
            return new JSONObject(new String(Files.readAllBytes(Paths.get(filename))));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Attachment(value = "Вариант с аннотацией: аттач файла '{filename}'", type = "application/json")
    public static byte[] attachFile(String filename) {
        try {
            return Files.readAllBytes(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Step("Формирование json запроса из файла Json {filename}")
    public static JSONObject readBodyJson(String filename) {
        attachFile(filename);
        try {
            Allure.addAttachment("Вариант методом: аттач файла '" + filename + "'",
                    "application/json", Files.newInputStream(Paths.get(filename)), ".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject json = readJsonFile(filename);
        assert json != null;
        return json;
    }

    @Step("Изменение json запроса")
    public static JSONObject modifyBodyJson(JSONObject json) {
        json.put("name", "Tomato");
        json.put("job", "Eat maket");
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

    @Step("Данные персонажа ID {id}: имя '{name}' раса '{species}' локация '{location}'")
    public static void viewPerson(int id, String name, String species, String location) {}

    @Step("Сверка персонажей")
    public static void comparePersons(StepStorage stepStorage) {
        Assertions.assertEquals(stepStorage.getPersonFirst().getSpecies(),
                stepStorage.getPersonSecond().getSpecies(),
                "Расы у двух персонажей разные");
        Assertions.assertNotEquals(stepStorage.getPersonFirst().getLocation().getName(),
                stepStorage.getPersonSecond().getLocation().getName(),
                "Локации у двух персонажей одинаковы");
    }
}
