package org.example.order;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.example.GeneralClient;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class OrderClient extends org.example.GeneralClient {
    public static final String ORDER_CREATE_PATH = "api/orders";

    public ValidatableResponse createOrder(Order order) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(GeneralClient.BASE_URI)
                .body(order)
                .when()
                .post(ORDER_CREATE_PATH)
                .then().log().all();
    }

    public ValidatableResponse getUsersOrdersWithAuth(String accessToken) {
        return getRequestSpecification()
                .contentType(ContentType.JSON)
                .headers(Map.of("Authorization", accessToken))
                .when()
                .get(ORDER_CREATE_PATH)
                .then().log().all();
    }

    public ValidatableResponse getUsersOrdersNoAuth() {
        return getRequestSpecification()
                .contentType(ContentType.JSON)
                .when()
                .get(ORDER_CREATE_PATH)
                .then().log().all();
    }
}
