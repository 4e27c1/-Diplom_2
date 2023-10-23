package org.example.user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LoginNegativeTest {
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();
    private String accessToken;

    @Test
    @DisplayName("Логин с неверным паролем")
    public void loginWithWrongPassword(){
        var user = UserGenerator.random();
        Map <String, String> wrongLogData = new HashMap<>();
        wrongLogData.put("email", user.getEmail());
        wrongLogData.put("password", user.getPassword()+"11");
        System.out.println(wrongLogData);

        ValidatableResponse response = client.create(user);
        check.createdSuccessfully(response);

        ValidatableResponse responseNew = client.loginNotAllBody(wrongLogData);
        check.loggedNotSuccessfully(responseNew);
    }
    @Test
    @DisplayName("Логин с неверным email")
    public void loginWithWrongEmail(){
        var user = UserGenerator.random();
        Map <String, String> wrongLogData = new HashMap<>();
        wrongLogData.put("email", user.getEmail()+"11");
        wrongLogData.put("password", user.getPassword());
        System.out.println(wrongLogData);

        ValidatableResponse response = client.create(user);
        check.createdSuccessfully(response);

        ValidatableResponse responseNew = client.loginNotAllBody(wrongLogData);
        check.loggedNotSuccessfully(responseNew);
    }
}
