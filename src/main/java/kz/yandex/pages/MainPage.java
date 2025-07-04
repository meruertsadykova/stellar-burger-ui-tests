package kz.yandex.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы элементов на главной странице
    private By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By profileButton = By.xpath(".//p[text()='Личный Кабинет']");
    private By bunButton = By.xpath(".//span[text()='Булки']");
    private By sauceButton = By.xpath(".//span[text()='Соусы']");
    private By fillingButton = By.xpath(".//span[text()='Начинки']");
    private By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private By menuTabLocator = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickAccountButton() {
        driver.findElement(profileButton).click();
    }

    @Step("Клик по вкладке 'Булки'")
    public void clickBunButton() {
        driver.findElement(bunButton).click();
    }

    @Step("Клик по вкладке 'Соусы'")
    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    @Step("Клик по вкладке 'Начинки'")
    public void clickFillingButton() {
        driver.findElement(fillingButton).click();
    }

    @Step("Получение текста с кнопки 'Оформить заказ'")
    public String getCreateOrderButtonText() {
        return driver.findElement(createOrderButton).getText();
    }

    @Step("Получение текста активной вкладки меню конструктора")
    public String getMenuTabLocator() {
        return driver.findElement(menuTabLocator).getText();
    }
}
