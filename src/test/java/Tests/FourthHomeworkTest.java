package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FourthHomeworkTest extends TestBase {


    private Logger logger = LogManager.getLogger(FourthHomeworkTest.class);


    @Test
    public void openPage() {
        // открыть сайт отус
        driver.get("https://otus.ru/");
        logger.info("Открыта страница Отус");
        // авторизоваться на сайте
        login();
        // зайти на личный кабинет
        enterAcc();
        // в разделе о себе заполнить инфу
        driver.findElement(By.cssSelector("a[title = 'О себе']")).click();

        // начинаем заполнять, ищем элементы
        WebElement nameRu = driver.findElement(By.id("id_fname"));
        WebElement nameLatin = driver.findElement(By.id("id_fname_latin"));
        WebElement secondnameRu = driver.findElement(By.id("id_lname"));
        WebElement secondnameLatin = driver.findElement(By.id("id_lname_latin"));
        WebElement blogName = driver.findElement(By.id("id_blog_name"));
        WebElement birthDate = driver.findElement(By.name("date_of_birth"));
        WebElement countryDropdown = driver.findElement(By.cssSelector("input[name='country'] + div"));
        WebElement cityDropdown = driver.findElement(By.cssSelector("input[name='city'] + div"));
        WebElement saveButton = driver.findElement(By.name("continue"));

        nameRu.clear();
        nameRu.sendKeys("Илья");
        nameLatin.clear();
        nameLatin.sendKeys("Ilya");
        secondnameRu.clear();
        secondnameRu.sendKeys("Курганов");
        secondnameLatin.clear();
        secondnameLatin.sendKeys("Kurganov");
        blogName.clear();
        blogName.sendKeys("Илья");
        birthDate.clear();
        birthDate.sendKeys("30.07.1994");
        birthDate.sendKeys(Keys.ENTER);

        countryDropdown.click();
        driver.findElement(By.cssSelector("div.lk-cv-block__select-scroll_country button[data-empty]")).click();
        countryDropdown.click();
        driver.findElement(By.xpath("//div[contains(@class,'lk-cv-block__select-scroll_country')]/button[normalize-space(text())='Россия']")).click();

        Actions actions = new Actions(driver);
        // спускаемся ниже
        actions.moveToElement(cityDropdown).build().perform();
        cityDropdown.click();
        driver.findElement(By.xpath("//div[contains(@class,'lk-cv-block__select-scroll_city')]/button[@data-empty]")).click();
        cityDropdown.click();
        driver.findElement(By.xpath("//div[contains(@class,'lk-cv-block__select-scroll_city')]/button[normalize-space(text())='Москва']")).click();

        WebElement readyToRelocate = driver.findElement(By.cssSelector("input[id='id_ready_to_relocate_1'] + span.radio__label"));
        actions.moveToElement(readyToRelocate).build().perform();
        // при помощи JS нажимаем на псевдоэлемент
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('input#id_ready_to_relocate_1 + span.radio__label',':before').click();");

        // нажать на Сохранить
        actions.moveToElement(saveButton).build().perform();
        saveButton.click();
        // проверим, что появилась надпись
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions
                        .visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'container-padding-top-half')]//span[text() = 'Данные успешно сохранены']"))));

        // открываем чистый браузер
        driver.quit();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят ЗАНОВО");
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://otus.ru/");
        // СНОВА авторизоваться на сайте
        login();
        // СНОВА зайти на личный кабинет
        enterAcc();
        //перейти в раздел о себе для проверок
        driver.findElement(By.cssSelector("a[title = 'О себе']")).click();
        // проверить всё
        Assert.assertEquals("Илья", driver.findElement(By.id("id_fname")).getAttribute("value"));
        Assert.assertEquals("Kurganov", driver.findElement(By.id("id_lname_latin")).getAttribute("value"));
    }

    private void login() {
        // элементы
        //TODO видимо надо отдельно писать локаторы, потом их искать
        WebElement logButton = driver.findElement(By.cssSelector("button.header2__auth"));

        //значения
        String login = "IKurganov@sportmaster.ru";
        String password = "Goingbackwards229";

        // действия
        logButton.click();
        driver.findElement(By.cssSelector("div.new-input-line_slim:nth-child(3) > input")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[normalize-space(text())='Войти']")).click();
        logger.info("залогинились");
    }

    private void enterAcc() {
        // элементы
        String avatar = "div.ic-blog-default-avatar";
        String accOption = "a[href='/learning/']";

        //значения
        String login = "IKurganov@sportmaster.ru";
        String password = "Goingbackwards229";

        // действия
        // навести курсор на элемент
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector(avatar)))
                .build().perform();

        // как вариант ещё задержаться на 400 миллисекунд методом Pause:
        actions.moveToElement(driver.findElement(By.cssSelector(avatar)))
                .pause(400L).build().perform();


        WebElement enterLkOption = driver.findElement(By.cssSelector(accOption));
        //actions.moveToElement(enterLkOption).build().perform();
        enterLkOption.click();

        logger.info("вошли в личный кабинет");
    }


}
