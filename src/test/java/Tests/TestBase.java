package Tests;

import Utils.ConfigForTests;
import Utils.WebDriverBrowser;
import Utils.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestBase {
    private Logger logger = LogManager.getLogger(TestBase.class);
    protected static WebDriver driver;

    @Before
    public void start() {
        WebDriverBrowser browser = ConfigForTests.getInstance().getWebDriverBrowser();
        driver = WebDriverFactory.getDriverForBrowser(browser);
        logger.info("Драйвер поднят для " + browser);
        driver.manage().timeouts().implicitlyWait(ConfigForTests.getWaiting(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void end() {
        logger.info("Тест окончен");
        if (driver != null)
            driver.quit();
    }

}
