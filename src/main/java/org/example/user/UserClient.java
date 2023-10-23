package org.example.user;

import io.restassured.response.ValidatableResponse;

import java.util.Map;

public class UserClient extends org.example.GeneralClient {
    public static final String REGISTER_PATH = "/api/auth/register";
    public static final String LOGIN_PATH = "/api/auth/login";
    public static final String DELETE_AND_EDIT_PATH = "/api/auth/user";

    public ValidatableResponse create (User user){
        return getRequestSpecification()
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then().log().all();
    }
    public  ValidatableResponse login (LoginData loginData){
        return getRequestSpecification()
                .body(loginData)
                .when()
                .post(LOGIN_PATH)
                .then().log().all();
    }
    public ValidatableResponse loginNotAllBody(Map login) {
        return getRequestSpecification()
                .body(login)
                .when()
                .post(LOGIN_PATH )
                .then().log().all();
    }

    public ValidatableResponse delete(String accessToken) {
        return getRequestSpecification()
                .headers(Map.of("Authorization", accessToken))
                .when()
                .delete(DELETE_AND_EDIT_PATH)
                .then().log().all();
    }
    public ValidatableResponse edit(String accessToken) {
        return getRequestSpecification()
                .headers(Map.of("Authorization", accessToken))
                .body(Map.of("name", "editedName"))
                .when()
                .patch(DELETE_AND_EDIT_PATH)
                .then().log().all();
    }
    public ValidatableResponse editNoToken() {
        return getRequestSpecification()
                .body(Map.of("name", "editedName"))
                .when()
                .patch(DELETE_AND_EDIT_PATH)
                .then().log().all();
    }
}
