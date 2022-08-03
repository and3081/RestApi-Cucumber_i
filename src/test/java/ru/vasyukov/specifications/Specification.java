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

    public static ResponseSpecification responseSpecCheckListUsers(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("page", notNullValue())
                .expectBody("total", notNullValue())
                .expectBody("total_pages", notNullValue())
                .expectBody("data", not(emptyArray()))
                .expectBody("data.id", not(hasItem(nullValue())))
                .expectBody("data.email", not(hasItem(nullValue())))
                .build();
    }

    public static ResponseSpecification responseSpecCheckUser(int status){
        if (status == 404) {
            return new ResponseSpecBuilder()
                    .expectStatusCode(status)
                    .expectBody("size()", is(0))
                    .build();
        }
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .expectBody("data", not(emptyArray()))
                .expectBody("data.size()", is(5))
                .expectBody("data.id", notNullValue())
                .expectBody("data.email", notNullValue())
                .expectBody("data.first_name", notNullValue())
                .expectBody("data.last_name", notNullValue())
                .expectBody("data.avatar", notNullValue())
                .build();
    }

    public static ResponseSpecification responseSpecCheckListResources(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("page", notNullValue())
                .expectBody("total", notNullValue())
                .expectBody("total_pages", notNullValue())
                .expectBody("data", not(emptyArray()))
                .expectBody("data.id", not(hasItem(nullValue())))
                .expectBody("data.name", not(hasItem(nullValue())))
                .build();
    }

    public static ResponseSpecification responseSpecCheckResource(int status){
        if (status == 404) {
            return new ResponseSpecBuilder()
                    .expectStatusCode(status)
                    .expectBody("size()", is(0))
                    .build();
        }
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .expectBody("data", not(emptyArray()))
                .expectBody("data.size()", is(5))
                .expectBody("data.id", notNullValue())
                .expectBody("data.name", notNullValue())
                .expectBody("data.year", notNullValue())
                .expectBody("data.color", notNullValue())
                .expectBody("data.pantone_value", notNullValue())
                .build();
    }

    public static ResponseSpecification responseSpecCheckJobCreate(){
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectBody("name", notNullValue())
                .expectBody("job", notNullValue())
                .expectBody("id", notNullValue())
                .expectBody("createdAt", notNullValue())
                .build();
    }

    public static ResponseSpecification responseSpecCheckJobUpdate(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("name", notNullValue())
                .expectBody("job", notNullValue())
                .expectBody("updatedAt", notNullValue())
                .build();
    }
}
