package JustSimpleTests;

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

        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[data-tid='8bc8e36b']"))));
        WebElement firstArticle = driver.findElement(By.xpath("//div[@data-zone-name='SearchResults']//article[@data-autotest-id][1]"));
        String articleTitle = firstArticle.findElement(By.xpath("//h3[@data-zone-name = 'title']/a")).getAttribute("title");

        WebElement addToComparisonButton = firstArticle.findElement(By.xpath("//div[contains(@aria-label,'сравнению')]/div"));
        addToComparisonButton.click();

        // подождём плашку
        String popupLocator = "//div[@data-apiary-widget-id ='/content/popupInformer']//div[text() ='Товар %s добавлен к сравнению']/parent::div";
        // тут уже в принципе если упадем, то печалька, но все же добавлю ассерт
        WebElement comparisonPopup = new WebDriverWait(driver, 4).until(
                ExpectedConditions.visibilityOf(
                        driver.findElement(By.xpath(String.format(popupLocator, articleTitle)))));
        assertEquals("Поп-ап должен отображаться", true, comparisonPopup.isDisplayed());
        log.info("Поищем второй телефон");
        String anotherBrand = articleTitle.contains("Samsung") ? "Xiaomi" : "Samsung";
        WebElement secondArticle = driver.findElement(By.xpath("//div[@data-zone-name='SearchResults']//article//a[contains(@title, '" + anotherBrand + "')]"));
        // нажмём на кнопку для сравнения по второму телефону
        driver.findElement(By.xpath("//div[@data-zone-name='SearchResults']//article//a[contains(@title, '" + anotherBrand + "')]//ancestor::article//div[contains(@aria-label,'сравнению')]/div"))
                .click();
        articleTitle = secondArticle.getAttribute("title");
        comparisonPopup = new WebDriverWait(driver, 2).until(
                ExpectedConditions.visibilityOf(
                        driver.findElement(By.xpath(String.format(popupLocator, articleTitle)))));
        assertEquals("Поп-ап должен отображаться", true, comparisonPopup.isDisplayed());
        log.info("Перейдем в сравнение - попытаемся найти на анимированном поп-апе кнопку перехода в корзину сравнения");


        //TODO КЛАДБИЩЕ ДОМАШНИХ РЕШЕНИЙ

        // Данное решение работало у меня, но не работало на бОльшем экране, судя по всему - по сути заглушка, я поднимался и всё
        // Actions actions = new Actions(driver);
        //actions.moveToElement(driver.findElement(By.cssSelector("input#header-search"))).build().perform();

        // эти Вэйты бесполезны, хоть чутка пиксель появляется - все, в АТАКУ ГРЫЗИ ЕГО АТА ИЩИ КНОПКУ - а нифига, она не до конца появилась, уймись и подожди - не хочет
        // new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(comparisonPopup));
        /*new WebDriverWait(driver, 5).until(
                ExpectedConditions.presenceOfElementLocated(comparisonPopup.findElement(By.xpath("//a[@href='/my/compare-lists']/parent::div")))).click();*/

        /*
        даже с другими локаторами не работает ((((
        new WebDriverWait(driver, 5).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Сравнить']/parent::a/parent::div")));
        new WebDriverWait(driver, 5).until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Сравнить']/parent::a/parent::div")));
        */


        //TODO В Мире Костылей - https://stackoverflow.com/questions/44912203/selenium-web-driver-java-element-is-not-clickable-at-point-x-y-other-elem - перепробовал варианты отсюда,
        // все, как один не срабатывали...
        // JS тоже не сработал, опять же правильным вариантом мне видится ожидание окончания загрузки анимации - там есть такой вариант, но я не смог найти animated, или навроде того
        // UPD 15-00 , 19.05 - я нашел скрипт на jQuery - но в случае яндекс маркета тупо не работает!(  НО! Зато нашел полезный скрипт по готовности документа - readyState

        // TODO Старый комент - "Но вот этот вариант работает без доп.подпрыгиваний" - UPD: если замедлить интернет, то вариант с дрыганьем страницы туда-сюда не работает,
        //  поэтому сэнд кейс UP я закоментил

        //new WebDriverWait(driver, 2).until((WebDriver driver) -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete") == true);

        // Написал костыль, пока он спит - люблю его. Первый wait бесполезный, но опять же - замедлить интернет и мб он станет полезен
        WebElement comparisonButton =
                new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Сравнить']/parent::a/parent::div"))));
        Actions actions = new Actions(driver);
        actions.pause(3000L).moveToElement(comparisonButton).click().build().perform();
        log.info("--> ЕСЛИ МЫ ТУТ... Значит кнопка Сравнить переиграна и уничтожена как ДЕШЕВКА, как ШВАЛЬ!");


        // СТАРЫЙ ВАРИАНТ: ради интереса можно его запустить, думаю, не сработает

        /*
        TODO спонсор комментариев - Ctrl + Shift + / , для строчки - Ctrl + /
        driver.findElement(By.cssSelector("input#header-search")).sendKeys(Keys.UP);
        driver.findElement(By.cssSelector("input#header-search")).sendKeys(Keys.UP);
        driver.findElement(By.cssSelector("input#header-search")).sendKeys(Keys.UP);
        */
        //... и оглушил им кнопку, теперь не убежит. UPD - на другом компе, у вас, думаю, убежала бы
        //driver.findElement(By.xpath("//span[text()='Сравнить']/parent::a/parent::div")).click();

        log.info("Посмотрим наконец, что в корзине сравнения 2 телефона - после того, как мы нашли эту припадочную кнопку");
        assertEquals(2, driver.findElements(By.xpath("//div[@data-tid='a86a07a1 2d4d9fc1']")).size());

        /*log.info("Порадуем себя маэстро");
        driver.get("https://www.meme-arsenal.com/create/template/2705171");
        actions.moveToElement(driver.findElement(By.xpath("//img[@crossorigin='Anonymous']"))).pause(5000L).build().perform();*/
    }


    @After
    public void stop() {
        log.info("КОНЕЦ ЭТОГО ТЕСТА");
        if (driver != null)
            driver.quit();
    }

}
