import kz.yandex.client.Client;
import kz.yandex.client.ClientLogin;
import kz.yandex.client.ClientSteps;
import kz.yandex.BrowserConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import kz.yandex.pages.MainPage;
import kz.yandex.pages.LoginPage;
import kz.yandex.pages.RegisterPage;
import kz.yandex.client.ClientFaker;
import io.restassured.RestAssured;
import java.time.Duration;

/**
 * Тесты регистрации пользователей.
 */
public class RegistrationTest extends BrowserConfig {

    private Client client;
    private String accessToken;

    @Before
    public void setUp() {
        driver.get(ClientSteps.baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void registerNewClientTest() {
        client = ClientFaker.getRandomClient();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registerPage.setClientInfo(client.getName(), client.getEmail(), client.getPassword());
        registerPage.clickRegisterButton();

        Assert.assertEquals("Вход", loginPage.getEnterLabelText());
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    public void registerNewClientWithWrongPasswordTest() {
        client = ClientFaker.getRandomClientWithWrongPassword();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registerPage.setClientInfo(client.getName(), client.getEmail(), client.getPassword());
        registerPage.clickRegisterButton();

        Assert.assertEquals("Некорректный пароль", registerPage.getPasswordErrorText());
    }

    @After
    public void tearDown() {
        driver.quit();

        // Удаление пользователя через API при наличии токена
        if (client != null && accessToken == null) {
            try {
                RestAssured.baseURI = ClientSteps.baseURL;
                accessToken = ClientSteps
                        .loginClient(new ClientLogin(client.getEmail(), client.getPassword()))
                        .then()
                        .extract()
                        .path("accessToken");
            } catch (Exception e) {
                System.out.println("⚠️ Не удалось получить accessToken: " + e.getMessage());
            }
        }

        if (accessToken != null) {
            try {
                ClientSteps.deleteClient(accessToken);
            } catch (Exception e) {
                System.out.println("⚠️ Ошибка при удалении пользователя: " + e.getMessage());
            }
        }
    }
}
