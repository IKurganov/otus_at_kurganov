import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class FirstTest {

    private Logger log = LogManager.getLogger(FirstTest.class);
    protected static WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        log.info("Драйвер поднят");
    }

    @Test
    public void testTitle() {
        driver.get("https://otus.ru");
        log.info("Сайт открыт");
        log.info("Проведём тест");
        assertEquals("Проверим заголовок страницы", "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", driver.getTitle());
    }

    @After
    public void stop() {
        if (driver != null)
            driver.quit();
    }


}
