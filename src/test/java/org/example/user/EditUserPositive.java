package org.example.user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class EditUserPositive {
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    private String accessToken;

    @After
    public void deleteCourier() {
        ValidatableResponse delete = client.delete(accessToken);
        check.deletedSuccessfully(delete);
    }

    @Test
    @DisplayName("Создание и логин и редактирование пользователя")
    public void userCreationAndLoginPositive() {
        var user = UserGenerator.random();
        ValidatableResponse response = client.create(user);
        check.createdSuccessfully(response);

        LoginData logData = LoginData.from(user);
        ValidatableResponse loginResponse = client.login(logData);
        accessToken = check.loggedIsSuccessfully(loginResponse);

        ValidatableResponse editResponse = client.edit(accessToken);
        check.editedSuccessfully(editResponse);
    }
}
