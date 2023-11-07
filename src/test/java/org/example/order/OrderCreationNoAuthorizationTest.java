package org.example.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.Constants;
import org.junit.Test;

import java.util.List;

public class OrderCreationNoAuthorizationTest {
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    @Test
    @DisplayName("Создание заказа без авторизации")
    public void createOrderNoAuthorize() {

        var order = new Order(List.of(Constants.BUN_ID, Constants.FILLING_ID, Constants.SAUCE_ID));

        ValidatableResponse response = client.createOrder(order);
        check.assertOrderCreationSuccessfully(response);
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void createOrderWithNoIngredients() {
        var order = new Order(null);

        ValidatableResponse response = client.createOrder(order);
        check.assertOrderCreateNullIngredients(response);
    }

    @Test
    @DisplayName("Создание заказа с неправильным хешем")
    public void createOrderWrongHash() {
        var order = new Order(List.of(Constants.BUN_ID, Constants.FILLING_ID, Constants.SAUCE_ID, Constants.WRONG_INGREDIENT_ID));

        ValidatableResponse response = client.createOrder(order);
        check.assertOrderCreateWrongHash(response);
    }
}
