package ru.vasyukov.steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ru.vasyukov.dto.Episode;
import ru.vasyukov.dto.ListPers;
import ru.vasyukov.dto.Person;
import ru.vasyukov.properties.TestData;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static ru.vasyukov.specifications.Specification.*;

/**
 * Класс тестовых шагов
 */
public class ApiStepsRick {

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

    @Step("Данные персонажа ID {id}: имя '{name}' раса '{species}' локация '{location}'")
    public static void viewPerson(int id, String name, String species, String location) {}

    @Step("Сверка персонажей")
    public static void comparePersons(Storage storage) {
        Assertions.assertEquals(storage.getPersonFirst().getSpecies(),
                storage.getPersonSecond().getSpecies(),
                "Расы у двух персонажей разные");
        Assertions.assertNotEquals(storage.getPersonFirst().getLocation().getName(),
                storage.getPersonSecond().getLocation().getName(),
                "Локации у двух персонажей одинаковы");
    }
}
