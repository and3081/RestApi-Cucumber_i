package ru.vasyukov.apiBase;

import io.qameta.allure.Step;
import ru.vasyukov.data.Episode;
import ru.vasyukov.data.ListPers;
import ru.vasyukov.data.Person;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static ru.vasyukov.specifications.Specification.*;

public class ApiBase {

    @Step("Поиск ID персонажа по его имени {namePers}")
    public static Integer findPersonIdForName(String namePers) {
        ListPers listPers;
        int numberPage = 0;
        do {
            numberPage++;
            //System.out.printf("с.%d..\n", numberPage);
            listPers= given()
                    .spec(requestSpec())
                    .queryParam("page", numberPage)
                    .when()
                    .get("api/character")
                    .then()
                    .spec(responseSpecListPers())
                    //.log().body()
                    .extract().body().as(ListPers.class);
            List<Object> listId= listPers.getResults().stream()
                    .filter(el-> el.get("name").equals(namePers))
                    .limit(1)
                    .map(el-> el.get("id"))
                    .collect(Collectors.toList());
            if (listId.size() > 0) return (Integer) listId.get(0);
        } while (listPers.getInfo().get("next") != null);
        return null;
    }

    @Step("Поиск персонажа по ID {idPers} или url {url}")
    public static Person getPerson(int idPers, String url) {
        return given()
                .spec(requestSpec())
                .when()
                .get(url==null || url.isEmpty() ? "api/character/" + idPers : url)
                .then()
                .spec(responseSpecPerson())
                //.log().body()
                .extract().body().as(Person.class);
    }

    @Step("Поиск последнего эпизода у персонажа {person.name}")
    public static Episode getLastEpisode(Person person) {
        String lastEpisode = person.getEpisode().get(person.getEpisode().size()-1);
        return given()
                .spec(requestSpec())
                .when()
                .get(lastEpisode)
                .then()
                .spec(responseSpecEpisode())
                //.log().body()
                .extract().body().as(Episode.class);
    }

    @Step("Поиск последнего персонажа у эпизода {episode.name}")
    public static Person getLastPerson(Episode episode) {
        String lastPerson = episode.getCharacters().get(episode.getCharacters().size() - 1);
        return getPerson(0 , lastPerson);
    }
}