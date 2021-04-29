import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class SecondHomework {
    private Logger log = LogManager.getLogger(SecondHomework.class);
    protected static WebDriver driver;
    private TestConfig testConfig = ConfigFactory.create(TestConfig.class);

    @Before
    public void startUp() {
        log.info("Let's test some sh** on website: {} and others", testConfig.baseUrl());
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        log.info("Драйвер поднят");
    }

    @Test
    public void otusContactsTest(){
        driver.get(testConfig.baseUrl());
        log.info("Сайт открыт");
        log.info("Проведём тест");
        driver.findElement(By.cssSelector("a[title='Контакты']")).click();
        String address = driver.findElement(By.xpath("//div[normalize-space(text())='Адрес']/following-sibling::div")).getText();
        assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02",address);
        log.info(address);




    }



    @After
    public void stop() {
        log.info("КОНЕЦ ЭТОГО ТЕСТА");
        if (driver != null)
            driver.quit();
    }






}
