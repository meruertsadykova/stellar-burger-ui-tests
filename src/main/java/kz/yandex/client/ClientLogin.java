package kz.yandex.client;

import lombok.Data;

/**
 * Класс для хранения учетных данных клиента
 */
@Data
public class ClientLogin {

    private String email;
    private String password;

    public ClientLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private ClientLogin() {
    }
}
