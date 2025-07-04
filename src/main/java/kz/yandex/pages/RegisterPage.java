package kz.yandex.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class RegisterPage {

    private WebDriver driver;

    private By nameField = By.xpath(".//label[text()='Имя']/../input");
    private By emailField = By.xpath(".//label[text()='Email']/../input");
    private By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    private By registerButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    private By loginButton = By.xpath(".//a[text()='Войти']");
    private By passwordError = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод имени: {name}")
    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввод email: {email}")
    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Заполнение данных пользователя: имя - {name}, email - {email}, пароль - (скрыт)")
    public void setClientInfo(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }

    @Step("Получение текста ошибки под полем пароля")
    public String getPasswordErrorText() {
        return driver.findElement(passwordError).getText();
    }
}
