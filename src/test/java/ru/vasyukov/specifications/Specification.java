package ru.vasyukov.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import ru.vasyukov.properties.TestData;

import static org.hamcrest.Matchers.*;
import static ru.vasyukov.listener.MyListener.myListener;

/**
 * Класс спеков для запросов и ответов
 */
public class Specification {
    public static RequestSpecification requestSpecRick() {
        return new RequestSpecBuilder()
                .setBaseUri(TestData.application.baseUrlRickandmortyapi())
                .setContentType(ContentType.JSON + ";charset=UTF-8")
                .addFilter(myListener())
                .build();
    }

    public static ResponseSpecification responseSpecCheckListPers(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("results", notNullValue())
                .expectBody("results", not(emptyArray()))
                .expectBody("results.id", not(hasItem(nullValue())))
                .expectBody("results.name", not(hasItem(nullValue())))
                .build();
    }

    public static ResponseSpecification responseSpecCheckPerson(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("name", notNullValue())
                .expectBody("species", notNullValue())
                .expectBody("location", notNullValue())
                .expectBody("location.name", notNullValue())
                .expectBody("episode", notNullValue())
                .expectBody("episode", not(emptyArray()))
                .build();
    }

    public static ResponseSpecification responseSpecCheckEpisode(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("name", notNullValue())
                .expectBody("characters", notNullValue())
                .expectBody("characters", not(emptyArray()))
                .build();
    }

    public static RequestSpecification requestSpecReqres() {
        return new RequestSpecBuilder()
                .setBaseUri(TestData.application.baseUrlReqres())
                .setContentType(ContentType.JSON + ";charset=UTF-8")
                .addFilter(myListener())
                .build();
    }

    public static ResponseSpecification responseSpecCheckCreate(){
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectBody("name", notNullValue())
                .expectBody("job", notNullValue())
                .expectBody("id", notNullValue())
                .expectBody("createdAt", notNullValue())
                .build();
    }
}
