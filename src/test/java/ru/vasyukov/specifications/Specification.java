package ru.vasyukov.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import ru.vasyukov.properties.TestData;

import static org.hamcrest.Matchers.*;

public class Specification {
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(TestData.props.baseUrl())
                .setContentType("application/json;charset=UTF-8")
                .build();
    }

    public static ResponseSpecification responseSpecListPers(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("results", notNullValue())
                .expectBody("results", not(emptyArray()))
                .expectBody("results.id", not(hasItem(nullValue())))
                .expectBody("results.name", not(hasItem(nullValue())))
                .build();
    }

    public static ResponseSpecification responseSpecPerson(){
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

    public static ResponseSpecification responseSpecEpisode(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("name", notNullValue())
                .expectBody("characters", notNullValue())
                .expectBody("characters", not(emptyArray()))
                .build();
    }
}