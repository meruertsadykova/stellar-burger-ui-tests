package kz.yandex.client;

import com.github.javafaker.Faker;

// Генератор случайных клиентов
public class ClientFaker {

    public static Client getRandomClient() {
        Faker faker = new Faker();
        final String email = faker.internet().emailAddress();
        final String password = faker.internet().password(10, 15);
        final String name = faker.name().fullName();
        return new Client(email, password, name);
    }

    public static Client getRandomClientWithWrongPassword() {
        Faker faker = new Faker();
        final String email = faker.internet().emailAddress();
        final String password = faker.internet().password(4, 6); // намеренно короткий пароль
        final String name = faker.name().fullName();
        return new Client(email, password, name);
    }
}
