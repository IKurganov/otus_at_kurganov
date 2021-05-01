import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ThirdHomework {

    private Logger log = LogManager.getLogger(ThirdHomework.class);
    protected static WebDriver driver;
    private TestConfig testConfig = ConfigFactory.create(TestConfig.class);

    @Before
    public void startUp() {
        log.info("Let's test Yandex Market");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        log.info("Драйвер поднят");
    }

    @Test
    public void yandexMarketTest() {
        driver.get(testConfig.yandexMarketUrl());
        log.info("Сайт яндекс маркета открыт");
        log.info("Проведём тестирование маркета и перейдем на электронику");
        driver.findElement(By.xpath("//span[text() = 'Электроника']")).click();

        log.info("Перейдем на смартфоны");
        driver.findElement(By.xpath("//a[normalize-space(text()) = 'Смартфоны']")).click();
        // найдем контейнер с брендами, от него выделим самсунг и хаоми
        WebElement brandsFilter = driver.findElement(By.xpath("//fieldset[@data-autotest-id = '7893318']"));

        log.info("Поищем самсунг и ксяоми, цена от мин к макс");
        brandsFilter.findElement(By.xpath("//span[text() = 'Samsung']")).click();
        brandsFilter.findElement(By.xpath("//span[text() = 'Xiaomi']")).click();
        driver.findElement(By.xpath("//button[text() = 'по цене']")).click();

        log.info("Подождём исчезновения лоадера. Затем найдем телефоны и проверим плашку");

        new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[data-tid='8bc8e36b']"))));
        WebElement firstArticle = driver.findElement(By.xpath("//div[@data-zone-name='SearchResults']//article[@data-autotest-id][1]"));
        String articleTitle = firstArticle.findElement(By.xpath("//h3[@data-zone-name = 'title']/a")).getAttribute("title");

        WebElement addToComparisonButton = firstArticle.findElement(By.xpath("//div[contains(@aria-label,'сравнению')]/div"));
        addToComparisonButton.click();

        // подождём плашку
        String popupLocator = "//div[@data-apiary-widget-id ='/content/popupInformer']//div[text() ='Товар %s добавлен к сравнению']";
        // тут уже в принципе если упадем, то печалька, но все же добавлю ассерт
        WebElement comparisonPopup = new WebDriverWait(driver, 4).until(
                ExpectedConditions.visibilityOf(
                        driver.findElement(By.xpath(String.format(popupLocator, articleTitle)))));
        assertEquals("Поп-ап должен отображаться", true, comparisonPopup.isDisplayed());

        log.info("Поищем второй телефон");
        String anotherBrand = articleTitle.contains("Samsung") ? "Xiaomi" : "Samsung";
        WebElement secondArticle = driver.findElement(By.xpath("//div[@data-zone-name='SearchResults']//article//a[contains(@title, '"+ anotherBrand +"')]"));
        // нажмём на кнопку для сравнения по второму телефону
        driver.findElement(By.xpath("//div[@data-zone-name='SearchResults']//article//a[contains(@title, '"+ anotherBrand +"')]//ancestor::article//div[contains(@aria-label,'сравнению')]/div"))
                .click();
        articleTitle = secondArticle.getAttribute("title");
        comparisonPopup = new WebDriverWait(driver, 2).until(
                ExpectedConditions.visibilityOf(
                        driver.findElement(By.xpath(String.format(popupLocator, articleTitle)))));
        assertEquals("Поп-ап должен отображаться", true, comparisonPopup.isDisplayed());

        log.info("Перейдем в сравнение");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("input#header-search"))).build().perform();
        comparisonPopup.findElement(By.xpath("//a[@href='/my/compare-lists']/parent::div")).click();

        log.info("Посмотрим, что там 2 телефона");
        assertEquals(2,driver.findElements(By.xpath("//div[@data-tid='a86a07a1 2d4d9fc1']")).size());
    }


    @After
    public void stop() {
        log.info("КОНЕЦ ЭТОГО ТЕСТА");
        if (driver != null)
            driver.quit();
    }


}
