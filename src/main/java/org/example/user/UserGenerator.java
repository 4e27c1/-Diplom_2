package org.example.user;
import org.apache.commons.lang3.RandomStringUtils;
public class UserGenerator {
    public static User generic () {
        var user = new User("", "1234", "SimpleBurger");
        return user;
    }

    public static User random() {
        return new User(RandomStringUtils.randomAlphanumeric(5, 10)+"@example.com", "1234", "TheBiggestBurgerFan");
    }
}

