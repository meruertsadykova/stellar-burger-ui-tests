import kz.yandex.client.Client;
import kz.yandex.client.ClientSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import kz.yandex.BrowserConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import kz.yandex.pages.*;
import kz.yandex.client.ClientFaker;
import java.time.Duration;

/**
 * Тесты авторизации пользователя через различные сценарии.
 */
public class LoginTest extends BrowserConfig {

    private Client client;
    private String accessToken;

    @Before
    public void setUp() {
        driver.get(ClientSteps.baseURL);
        RestAssured.baseURI = ClientSteps.baseURL;
        client = ClientFaker.getRandomClient();
        accessToken = ClientSteps.createNewClient(client).then().extract().path("accessToken");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверяет возможность входа в систему через кнопку «Войти в аккаунт» на главной странице. " +
            "Пользователь регистрируется через API, затем вводит логин и пароль в UI.")
    public void loginFromMainPageTest(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickLoginButton();
        loginPage.setClientLoginData(client.getEmail(), client.getPassword());
        loginPage.clickLoginButton();
        Assert.assertEquals("Оформить заказ", mainPage.getCreateOrderButtonText());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверяет возможность входа в систему через кнопку «Личный кабинет» на главной странице.")
    public void loginFromProfilePageTest(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickAccountButton();
        loginPage.setClientLoginData(client.getEmail(), client.getPassword());
        loginPage.clickLoginButton();
        Assert.assertEquals("Оформить заказ", mainPage.getCreateOrderButtonText());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверяет возможность авторизации через форму регистрации.")
    public void loginFromRegisterPageTest(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.clickAccountButton();
        loginPage.clickRegisterButton();
        registerPage.clickLoginButton();
        loginPage.setClientLoginData(client.getEmail(), client.getPassword());
        loginPage.clickLoginButton();
        Assert.assertEquals("Оформить заказ", mainPage.getCreateOrderButtonText());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверяет возможность авторизации через форму восстановления пароля.")
    public void loginFromForgotPasswordPageTest(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        mainPage.clickAccountButton();
        loginPage.clickForgotPasswordButton();
        forgotPasswordPage.clickLoginButton();
        loginPage.setClientLoginData(client.getEmail(), client.getPassword());
        loginPage.clickLoginButton();
        Assert.assertEquals("Оформить заказ", mainPage.getCreateOrderButtonText());
    }

    @After
    public void tearDown(){
        driver.quit();
        if (accessToken != null) {
            ClientSteps.deleteClient(accessToken);
        }
    }
}
