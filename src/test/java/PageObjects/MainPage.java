package PageObjects;

import PageObjects.PagesInAccount.AccountPage;
import Tests.FourthHomeworkTest;
import Utils.ConfigForTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MainPage extends BasePage {
    private Logger logger = LogManager.getLogger(MainPage.class);
    // элементы
    private By logButton = By.cssSelector("button.header2__auth");
    private By loginInput = By.cssSelector("div.new-input-line_slim:nth-child(3) > input");
    private By passwordInput = By.name("password");
    private By enterIntoOtusButton = By.xpath("//button[normalize-space(text())='Войти']");
    private By avatar = By.cssSelector("div.ic-blog-default-avatar");
    private By personalCab = By.cssSelector("a[href='/learning/']");


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage getAndLogin() {
        driver.get(ConfigForTests.getInstance().getBaseUrl());
        driver.findElement(logButton).click();

        //значения
        //TODO вынести значения

        String login = "IKurganov@sportmaster.ru";
        String password = "Goingbackwards229";

        // действия
        driver.findElement(loginInput).sendKeys(login);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(enterIntoOtusButton).click();
        logger.info("попытались залогиниться");
        return this;
    }

    public AccountPage enterAcc() {

        Actions actions = new Actions(driver);

        // как вариант ещё задержаться на 400 миллисекунд методом Pause:
        actions.moveToElement(driver.findElement(avatar))
                .pause(400L).build().perform();


        driver.findElement(personalCab).click();
        logger.info("вошли в личный кабинет");
        return new AccountPage(driver);
    }


}
