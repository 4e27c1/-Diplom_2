package org.example.user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserCreationPositiveTest {
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    private  String accessToken;

    @After
    public void deleteCourier () {
        ValidatableResponse delete = client.delete(accessToken);
        check.deletedSuccessfully(delete);
    }
    @Test
    @DisplayName("Создание пользователя")
    public void userCreationPositive(){
        var user = UserGenerator.random();
        ValidatableResponse response = client.create(user);
        check.createdSuccessfully(response);

        LoginData logData = LoginData.from(user);
        ValidatableResponse loginResponse = client.login(logData);
        accessToken = check.loggedIsSuccessfully(loginResponse);
        assertNotNull(accessToken);
    }
}
