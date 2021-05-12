package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SecondHomework {
    private Logger log = LogManager.getLogger(SecondHomework.class);
    protected static WebDriver driver;
    private TestConfig testConfig = ConfigFactory.create(TestConfig.class);

    @Before
    public void startUp() {
        log.info("Let's test something on website: {} and others", testConfig.baseUrl());
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        log.info("Драйвер поднят");
    }

    @Test
    public void otusContactsTest() {
        driver.get(testConfig.baseUrl());
        log.info("Сайт открыт");
        log.info("Проведём тест");
        driver.findElement(By.cssSelector("a[title='Контакты']")).click();

        String address = driver.findElement(By.xpath("//div[normalize-space(text())='Адрес']/following-sibling::div")).getText();
        // проверим адрес
        assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02", address);
        // расширим окно
        driver.manage().window().maximize();
        // проверим заголовок
        assertEquals("Проверим заголовок страницы", "Контакты | OTUS", driver.getTitle());
    }

    @Test
    public void tele2NumberSearchTest() {
        driver.get("https://msk.tele2.ru/shop/number");
        // проверим, что появились номера, скинем в ноль ожидание
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        log.info("Сайт теле 2 открыт");
        log.info("Проведём тест поиска номера");
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions
                        .visibilityOf(driver.findElement(By.name("searchNumber"))))
                .sendKeys("97");
        // поиск номеров начинается автоматически
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions
                        .visibilityOf(driver.findElement(By.cssSelector("div.product-group"))));
    }

    @Test
    public void otusFaqTest() {
        driver.get(testConfig.baseUrl());
        log.info("Сайт отуса открыт");
        log.info("Проведём тест вопросов из ЧаВо");
        // переходим в FAQ
        driver.findElement(By.cssSelector("a[title='FAQ']")).click();
        // ищем наш вопрос
        // в selenide есть хороший метод - скрол инто вью, а тут нет ((( иногда когда элемент внизу, спускаюсь к нему, здесь тоже так сделаю на всякий
        WebElement question = driver.findElement(By.xpath("//div[normalize-space(text())='Где посмотреть программу интересующего курса?']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(question)
                .build().perform();
        question.click();
        // здесь стоит поставить явное ожидание, так как элемент может быть и не виден
        String answer = new WebDriverWait(driver, 1)
                .until(ExpectedConditions
                        .visibilityOf(question.findElement(By.xpath("following-sibling::div[contains(@class, 'faq-question__answer')]")))).getText();

        assertEquals("Сверим ожидаемый и актуальный ответ на вопросы - упадёт, если не сойдётся",
                "Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”",
                answer);
    }


    @Test
    public void otusSubscriptionTest() {
        driver.get(testConfig.baseUrl());
        log.info("Сайт отуса открыт");
        log.info("Проведём тест подписки по емейлу");
        // переходим в FAQ
        WebElement emailInput = driver.findElement(By.cssSelector("input.footer2__subscribe-input"));
        Actions actions = new Actions(driver);
        actions.moveToElement(emailInput)
                .build().perform();
        emailInput.sendKeys("test@test.ru");
        // дождемся активности кнопки подписки после ввода имейл
        WebElement subscribeButton = driver.findElement(By.xpath("//button[normalize-space(text()) = 'Подписаться']"));
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.not(ExpectedConditions.attributeContains(subscribeButton, "disabled", "disabled")));
        subscribeButton.click();
        // дождемся появления текста и проверим его
        WebElement successTextElement = new WebDriverWait(driver, 1)
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("p.subscribe-modal__success"))));
        assertEquals("Вы успешно подписались", successTextElement.getText());
    }


    @After
    public void stop() {
        log.info("КОНЕЦ ЭТОГО ТЕСТА");
        if (driver != null)
            driver.quit();
    }


}
