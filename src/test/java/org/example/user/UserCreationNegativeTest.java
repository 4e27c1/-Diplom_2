package org.example.user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class UserCreationNegativeTest {
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();

    @Test
    @DisplayName("Создание пользователя c уже существующим емайлом")
    public void userCreationPositive(){
        var user = UserGenerator.random();
        ValidatableResponse response = client.create(user);
        check.createdSuccessfully(response);

        ValidatableResponse responseNew = client.create(user);
        check.createdNotSuccessfully(responseNew);

    }

    @Test
    @DisplayName("Создание курьера не все поля заполнены")
    public void courierCreationNotAllFields(){
        var user = UserGenerator.generic();
        ValidatableResponse response = client.create(user);
        check.creationWithBadRequest(response);

    }
}
