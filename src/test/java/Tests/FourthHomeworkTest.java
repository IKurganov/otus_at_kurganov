package Tests;

import PageObjects.MainPage;
import PageObjects.PagesInAccount.AboutMyselfPage;
import Utils.ConfigForTests;
import Utils.model.OtusUser;
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

        // создаю юзера, у которого уже есть все данные, кроме контактов - не стал их выносить
        OtusUser testUser = new OtusUser();
        testUser.setNameRu(ConfigForTests.getInstance().getNameRu());
        testUser.setNameLatin(ConfigForTests.getInstance().getNameLatin());
        testUser.setSecondnameRu(ConfigForTests.getInstance().getSecondnameRu());
        testUser.setSecondnameLatin(ConfigForTests.getInstance().getSecondnameLatin());
        testUser.setBlogName(ConfigForTests.getInstance().getBlogName());
        testUser.setBirthDate(ConfigForTests.getInstance().getBirthDate());
        testUser.setCountry(ConfigForTests.getInstance().getCountry());
        testUser.setCity(ConfigForTests.getInstance().getCity());
        testUser.setRelocate(ConfigForTests.getInstance().isRelocate());

        // как проверки - можно было бы вынести в отдельный метод, но пока нет смысла и профита
        aboutMyselfPage.setNameRu(testUser.getNameRu());
        aboutMyselfPage.setNameLatin(testUser.getNameLatin());
        aboutMyselfPage.setSecondnameRu(testUser.getSecondnameRu());
        aboutMyselfPage.setSecondnameLatin(testUser.getSecondnameLatin());
        aboutMyselfPage.setBlogName(testUser.getBlogName());
        aboutMyselfPage.setBirthDate(testUser.getBirthDate());
        aboutMyselfPage.setCountry(testUser.getCountry());
        aboutMyselfPage.setCity(testUser.getCity());
        aboutMyselfPage.setRelocate(testUser.isRelocate());
        aboutMyselfPage.addContact("Тelegram","Telegram_contact", true, 1);
        aboutMyselfPage.addContact("VK","VK_contact", false, 2);
        aboutMyselfPage.clickSaveButton();


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
        logger.info("Снова перешли на страницу о себе и начнем проверку");
        // проверить всё

        checkPersonalData(aboutMyselfPage, testUser);
    }

    private void checkPersonalData(AboutMyselfPage aboutMyselfPage, OtusUser otusUser){
        Assert.assertEquals("Имя-ру не то", otusUser.getNameRu(), aboutMyselfPage.getNameRu());
        Assert.assertEquals("Имя-латин не то", otusUser.getNameLatin(), aboutMyselfPage.getNameLatin());
        Assert.assertEquals("Фамилия-ру не то", otusUser.getSecondnameRu(), aboutMyselfPage.getSecondnameRu());
        Assert.assertEquals("Фамилия-латин не то", otusUser.getSecondnameLatin(), aboutMyselfPage.getSecondnameLatin());
        Assert.assertEquals("Блог-имя не то", otusUser.getBlogName(), aboutMyselfPage.getBlogName());
        Assert.assertEquals("Дата рождения не та", otusUser.getBirthDate(), aboutMyselfPage.getBirthDate());
        Assert.assertEquals("Страна не та", otusUser.getCountry(), aboutMyselfPage.getCountry());
        Assert.assertEquals("Город не тот", otusUser.getCity(), aboutMyselfPage.getCity());
        Assert.assertEquals("Релокейт не тот", otusUser.getNameLatin(), aboutMyselfPage.getNameLatin());
        // контакты
        Assert.assertTrue("Было добавлено 2 контакта",aboutMyselfPage.getContacts().size() == 2);
        Assert.assertTrue(aboutMyselfPage.getContacts().containsKey("VK"));
        Assert.assertTrue(aboutMyselfPage.getContacts().containsValue("VK_contact"));
        Assert.assertTrue(aboutMyselfPage.getContacts().containsKey("Тelegram"));
        Assert.assertTrue(aboutMyselfPage.getContacts().containsValue("Telegram_contact"));
    }
}
