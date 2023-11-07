package org.example.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.Constants;
import org.example.user.LoginData;
import org.example.user.UserAssertions;
import org.example.user.UserClient;
import org.example.user.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GetUsersOrderAuthorized {
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();
    private final UserClient userClient = new UserClient();
    private final UserAssertions userCheck = new UserAssertions();
    private String accessToken;

    @Before
    public void createTestData() {
        var user = UserGenerator.random();
        ValidatableResponse createResponse = userClient.create(user);
        userCheck.createdSuccessfully(createResponse);

        LoginData logData = LoginData.from(user);
        ValidatableResponse loginResponse = userClient.login(logData);
        accessToken = userCheck.loggedIsSuccessfully(loginResponse);

        var order = new Order(List.of(Constants.BUN_ID, Constants.FILLING_ID, Constants.SAUCE_ID));

        ValidatableResponse response = client.createOrder(order);
        check.assertOrderCreationSuccessfully(response);
    }

    @After
    public void deleteCourier() {
        ValidatableResponse delete = userClient.delete(accessToken);
        userCheck.deletedSuccessfully(delete);
    }

    @Test
    @DisplayName("Получение информации о заказах пользователя с авторизацией")
    public void getUsersOrdersAuthorized() {

        ValidatableResponse responseList = client.getUsersOrdersWithAuth(accessToken);
        check.assertGetOrderListSuccessfully(responseList);
    }
}
