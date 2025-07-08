package kz.yandex;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

/**
 * Класс для настройки и выбора браузера.
 */
public class BrowserConfig {

    protected WebDriver driver;
    public static final String PROPERTIES = "src/main/resources/config.properties";
    private static String browser;

    @Before
    public void configure() {
        Properties prop = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(PROPERTIES);
            prop.load(fileInputStream);
            browser = prop.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
        }
        selectBrowser();
    }

    // Выбор и инициализация браузера
    public void selectBrowser() {
        if ("chrome".equals(browser))
            setUpChrome();
        else
            setUpYandex();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public void setUpChrome() {
        driver = new ChromeDriver();
    }

    public void setUpYandex() {
        driver = new ChromeDriver();
    }
}
