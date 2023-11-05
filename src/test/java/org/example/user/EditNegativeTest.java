package org.example.user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class EditNegativeTest {
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();

    @Test
    @DisplayName("Создание и редактирование пользователя без логина")
    public void userCreationAndLoginPositive() {
        var user = UserGenerator.random();
        ValidatableResponse response = client.create(user);
        check.createdSuccessfully(response);

        ValidatableResponse editResponse = client.editNoToken();
        check.editedNotSuccessfully401(editResponse);
    }
}
