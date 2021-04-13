import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
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
    //TODO при переносе в базовый класс тестов поменять модификатор доступа
    private TestConfig testConfig = ConfigFactory.create(TestConfig.class);


    @Before
    public void startUp() {
        System.out.println("Test of title on website: " + testConfig.baseUrl() + " with server-port -  " + testConfig.port() +
                " will run with maximum count of threads = " + testConfig.maxThreads() + ". This is just demonstration of Owner");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        log.info("Драйвер поднят");
    }

    @Test
    public void testTitle() {
        driver.get(testConfig.baseUrl());
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
