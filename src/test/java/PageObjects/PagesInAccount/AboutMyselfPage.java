package PageObjects.PagesInAccount;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutMyselfPage extends AccountPage {
    private By nameRu = By.id("id_fname");
    private By nameLatin = By.id("id_fname_latin");
    private By secondnameRu = By.id("id_lname");
    private By secondnameLatin = By.id("id_lname_latin");
    private By blogName = By.id("id_blog_name");
    private By birthDate = By.name("date_of_birth");
    private By countryDropdown = By.cssSelector("input[name='country'] + div");
    private By countryEmptyValue = By.cssSelector("div.lk-cv-block__select-scroll_country button[data-empty]");
    private By cityDropdown = By.cssSelector("input[name='city'] + div");
    private By cityEmptyValue = By.xpath("//div[contains(@class,'lk-cv-block__select-scroll_city')]/button[@data-empty]");
    private By readyToRelocate = By.cssSelector("input[id='id_ready_to_relocate_1'] + span.radio__label");
    private By notReadyToRelocate = By.cssSelector("input[id='id_ready_to_relocate_0'] + span.radio__label");
    private By saveButton = By.name("continue");
    private By saveDataLabel = By.xpath("//div[contains(@class,'container-padding-top-half')]//span[text() = 'Данные успешно сохранены']");

    Actions actions = new Actions(driver);

    public AboutMyselfPage(WebDriver driver) {
        super(driver);
    }


    public void setNameRu(String name) {
        WebElement nameRuField = driver.findElement(nameRu);
        nameRuField.clear();
        nameRuField.sendKeys("Илья");
    }

    public void setNameLatin(String name) {
        WebElement nameLatinField = driver.findElement(nameLatin);
        nameLatinField.clear();
        nameLatinField.sendKeys("Ilya");
    }

    public void setSecondnameRu(String secondname) {
        WebElement secondnameRuField = driver.findElement(secondnameRu);
        secondnameRuField.clear();
        secondnameRuField.sendKeys("Курганов");
    }

    public void setSecondnameLatin(String secondname) {
        WebElement secondnameLatinField = driver.findElement(secondnameLatin);
        secondnameLatinField.clear();
        secondnameLatinField.sendKeys("Курганов");
    }

    public void setBlogName(String blogNameInOtus) {
        WebElement blogNameField = driver.findElement(blogName);
        blogNameField.clear();
        blogNameField.sendKeys("Илья");
    }

    public void setBirthDate(String birthDateValue) {
        WebElement birthDateField = driver.findElement(birthDate);
        birthDateField.clear();
        birthDateField.sendKeys("30.07.1994");
        birthDateField.sendKeys(Keys.ENTER);
    }

    public void setCountry(String countryValue) {
        WebElement countryDropdownElement = driver.findElement(countryDropdown);
        countryDropdownElement.click();
        driver.findElement(countryEmptyValue).click();
        countryDropdownElement.click();
        String countryLocator = String.format("//div[contains(@class,'lk-cv-block__select-scroll_country')]/button[normalize-space(text())='%s']","Россия"/*countryValue*/);
        driver.findElement(By.xpath(countryLocator)).click();
    }

    public void setCity(String cityValue) {
        WebElement cityDropdownElement = driver.findElement(cityDropdown);
        actions.moveToElement(cityDropdownElement).build().perform();
        cityDropdownElement.click();
        driver.findElement(cityEmptyValue).click();
        cityDropdownElement.click();
        String cityLocator = String.format("//div[contains(@class,'lk-cv-block__select-scroll_city')]/button[normalize-space(text())='%s']","Москва"/*cityValue*/);
        driver.findElement(By.xpath(cityLocator)).click();
    }

    public void setRelocate(boolean ready) {
        if(ready){
            setReadyToRelocate();
        }
        else if(!ready){
            setNotReadyToRelocate();
        }
    }

    private void setReadyToRelocate(){
        WebElement readyToRelocateCheckbox = driver.findElement(readyToRelocate);
        actions.moveToElement(readyToRelocateCheckbox).build().perform();
        // при помощи JS нажимаем на псевдоэлемент
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('input#id_ready_to_relocate_1 + span.radio__label',':before').click();");
    }

    private void setNotReadyToRelocate(){
        WebElement notReadyToRelocateCheckbox = driver.findElement(notReadyToRelocate);
        actions.moveToElement(notReadyToRelocateCheckbox).build().perform();
        // при помощи JS нажимаем на псевдоэлемент
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('input#id_ready_to_relocate_0 + span.radio__label',':before').click();");
    }

    public void clickSaveButton(){
        WebElement saveButtonElement = driver.findElement(saveButton);
        actions.moveToElement(saveButtonElement).build().perform();
        saveButtonElement.click();
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions
                        .visibilityOf(driver.findElement(saveDataLabel)));
    }


    public By getNameRu() {
        return nameRu;
    }

    public By getNameLatin() {
        return nameLatin;
    }

    public By getSecondnameRu() {
        return secondnameRu;
    }

    public By getSecondnameLatin() {
        return secondnameLatin;
    }

    public void setSecondnameLatin(By secondnameLatin) {
        this.secondnameLatin = secondnameLatin;
    }

    public By getBlogName() {
        return blogName;
    }

    public void setBlogName(By blogName) {
        this.blogName = blogName;
    }

    public By getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(By birthDate) {
        this.birthDate = birthDate;
    }

    public By getCountryDropdown() {
        return countryDropdown;
    }

    public void setCountryDropdown(By countryDropdown) {
        this.countryDropdown = countryDropdown;
    }

    public By getCityDropdown() {
        return cityDropdown;
    }

    public void setCityDropdown(By cityDropdown) {
        this.cityDropdown = cityDropdown;
    }
}
