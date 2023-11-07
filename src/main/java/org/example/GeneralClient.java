package org.example;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GeneralClient {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    public RequestSpecification getRequestSpecification () {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }
}
