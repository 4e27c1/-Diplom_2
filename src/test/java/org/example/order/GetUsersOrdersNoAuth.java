package org.example.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class GetUsersOrdersNoAuth {

    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    @Test
    @DisplayName("Получение информации о заказах пользователя без авторизации")
    public void getOrderListNoAuthTest() {
        ValidatableResponse responseList = client.getUsersOrdersNoAuth();
        check.assertGetOrderListNOTSuccessfully(responseList);
    }
}
