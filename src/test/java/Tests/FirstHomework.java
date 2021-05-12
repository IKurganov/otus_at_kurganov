package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class FirstHomework {

    private Logger log = LogManager.getLogger(FirstHomework.class);
    protected static WebDriver driver;

    private TestConfig testConfig = ConfigFactory.create(TestConfig.class);


    @Before
    public void startUp() {
        log.info("Test of title on website: {} \nwith server-port - {} will run with maximum count of threads = {} \nThis is just demonstration of Owner",
                testConfig.baseUrl(), testConfig.port(), testConfig.maxThreads());
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        log.info("Драйвер поднят");
    }

    @Test
    public void testTitle() {
        // с помощью Owner беру информацию о BaseUrl - сайт отуса, можно будет менять не во всём проекте сайт. Открываем сайт Отуса
        driver.get(testConfig.baseUrl());
        log.info("Сайт открыт");
        log.info("Проведём тест");
        //TODO рассмотреть возможность подключения более гибкой библиотеки для ассертов - Assertj , имеет удобный синтаксис для проверок хотя бы
        assertEquals("Проверим заголовок страницы", "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", driver.getTitle());
    }

    @After
    public void stop() {
        if (driver != null)
            driver.quit();
    }


}
