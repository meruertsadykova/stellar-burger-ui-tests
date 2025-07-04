import kz.yandex.BrowserConfig;
import kz.yandex.client.ClientFaker;
import kz.yandex.client.ClientSteps;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.*;
import kz.yandex.pages.MainPage;
import kz.yandex.client.Client;
import java.time.Duration;

/**
 * Тесты главной страницы конструктора бургеров.
 */
public class ConstructorTest extends BrowserConfig {

    private Client client;

    @Before
    public void setUp() {
        driver.get(ClientSteps.baseURL);
        RestAssured.baseURI = ClientSteps.baseURL;
        client = ClientFaker.getRandomClient();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Переход к разделу Булки на главной странице")
    public void bunSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        String text = mainPage.getMenuTabLocator();
        Assert.assertEquals("Булки", text);
    }

    @Test
    @DisplayName("Переход к разделу Соусы на главной странице")
    public void sauceSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        String text = mainPage.getMenuTabLocator();
        Assert.assertEquals("Соусы", text);
    }

    @Test
    @DisplayName("Переход к разделу Начинки на главной странице")
    public void fillingSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingButton();
        String text = mainPage.getMenuTabLocator();
        Assert.assertEquals("Начинки", text);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
