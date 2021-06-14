package Tests;

import PageObjects.MainPage;
import PageObjects.PagesInAccount.AboutMyselfPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
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
    public void testAccInfo() {
        // открыть сайт отус
        //driver.get("https://otus.ru/");
        MainPage mainPage = new MainPage(driver);

        // перейти на сайт и авторизоваться
        mainPage.getAndLogin();
        logger.info("Открыта страница Отус");

        // зайти на личный кабинет и затем в раздел о себе
        AboutMyselfPage aboutMyselfPage = mainPage.enterAcc().goToAboutMyselfPage();

        //TODO - всё внизу поменять на методы и элементы со страницы AboutMyselfPage

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

        //TODO добавление контактов сделать и добавить на пейдже и тут!

        // нажать на Сохранить
        actions.moveToElement(saveButton).build().perform();
        saveButton.click();

        // проверим, что появилась надпись
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions
                        .visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'container-padding-top-half')]//span[text() = 'Данные успешно сохранены']"))));

        // открываем чистый браузер
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        // снова заходим о себе и прочее
        mainPage = new MainPage(driver);
        // перейти на сайт и авторизоваться
        mainPage.getAndLogin();
        logger.info("Открыта страница Отус");
        // зайти на личный кабинет и затем в раздел о себе
        aboutMyselfPage = mainPage.enterAcc().goToAboutMyselfPage();
        logger.info("Снова перешли на страницу о себе");

        // проверить всё
        //TODO добавить новые проверки с учетом геттеров из aboutMySelfPage итд данных из пропертей

        Assert.assertEquals("Илья", driver.findElement(By.id("id_fname")).getAttribute("value"));
        Assert.assertEquals("Kurganov", driver.findElement(By.id("id_lname_latin")).getAttribute("value"));
    }
}
