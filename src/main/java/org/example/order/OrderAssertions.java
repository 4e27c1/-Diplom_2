package org.example.order;

import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.is;

public class OrderAssertions {
    public void assertOrderCreationSuccessfully(ValidatableResponse response) {
        int number = response
                .assertThat()
                .statusCode(200)
                .extract()
                .path("order.number");
        assert number != 0;
    }


    public void assertOrderCreateNullIngredients(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(400)
                .body("message", is("Ingredient ids must be provided"));
    }

    public void assertOrderCreateWrongHash(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }

    public void assertGetOrderListSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("success", is(true));
    }

    public void assertGetOrderListNOTSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .body("message", is("You should be authorised"));
    }

}
