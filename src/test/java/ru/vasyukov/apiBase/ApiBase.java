package ru.vasyukov.apiBase;

import ru.vasyukov.data.ListPers;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static ru.vasyukov.specifications.Specification.*;

public class ApiBase {
    public static Integer findPersIdForName(String namePers) {
        ListPers listPers;
        int numberPage = 0;
        do {
            numberPage++;
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

    public static void getPers() {
        Object q= given()
                .spec(requestSpec())
                .when()
                .get("api/character")
                .then()
                .log().body();
    }
}
