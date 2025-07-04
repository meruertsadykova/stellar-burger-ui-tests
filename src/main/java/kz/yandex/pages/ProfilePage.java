package kz.yandex.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class ProfilePage {
    private WebDriver driver;

    private By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private By logoutButton = By.xpath(".//button[text()='Выход']");
    private By constructorButton = By.xpath(".//p[text()='Конструктор']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Конструктор' в личном кабинете")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по логотипу в хедере")
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    @Step("Клик по кнопке 'Выход'")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("Получение текста кнопки 'Выход'")
    public String getLogoutButtonText() {
        return driver.findElement(logoutButton).getText();
    }
}
