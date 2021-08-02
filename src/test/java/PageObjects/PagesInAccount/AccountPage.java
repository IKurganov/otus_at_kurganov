package PageObjects.PagesInAccount;

import PageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {


    private By aboutMyself = By.cssSelector("a[title = 'О себе']");
    private By myCourses;
    private By payment;
    private By getFriend;
    private By settings;
    private By documents;

    public AccountPage(WebDriver driver) {
        super(driver);
    }


    public AboutMyselfPage goToAboutMyselfPage(){
        driver.findElement(aboutMyself).click();
        return new AboutMyselfPage(driver);
    }


}
