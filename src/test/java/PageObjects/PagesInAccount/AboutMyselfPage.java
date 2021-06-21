package PageObjects.PagesInAccount;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private By deleteContactButton = By.xpath("//div[contains(@class,'container__col') and not (descendant::input[contains(@type,'checkbox')])]//button[text()='Удалить']");
    private By addContactButton = By.xpath("//button[text()='Добавить']");
    private By contactMethodDropdownButton = By.xpath("//span[text()='Способ связи']");
    private By readyToRelocateForValue = By.cssSelector("input[id='id_ready_to_relocate_1']");
    private By contactsSelectedMessangersValues = By.cssSelector("div[data-prefix='contact'] div[data-selected-option-class] div.input");
    private By contactsMessangersAccsValues = By.cssSelector("div[data-prefix='contact'] div[data-selected-option-class] + input");
    private static int addNum = 0;


    Actions actions = new Actions(driver);

    public AboutMyselfPage(WebDriver driver) {
        super(driver);
    }


    public void setNameRu(String name) {
        WebElement nameRuField = driver.findElement(nameRu);
        nameRuField.clear();
        nameRuField.sendKeys(name);
    }

    public void setNameLatin(String name) {
        WebElement nameLatinField = driver.findElement(nameLatin);
        nameLatinField.clear();
        nameLatinField.sendKeys(name);
    }

    public void setSecondnameRu(String secondname) {
        WebElement secondnameRuField = driver.findElement(secondnameRu);
        secondnameRuField.clear();
        secondnameRuField.sendKeys(secondname);
    }

    public void setSecondnameLatin(String secondname) {
        WebElement secondnameLatinField = driver.findElement(secondnameLatin);
        secondnameLatinField.clear();
        secondnameLatinField.sendKeys(secondname);
    }

    public void setBlogName(String blogNameInOtus) {
        WebElement blogNameField = driver.findElement(blogName);
        blogNameField.clear();
        blogNameField.sendKeys(blogNameInOtus);
    }

    public void setBirthDate(String birthDateValue) {
        WebElement birthDateField = driver.findElement(birthDate);
        birthDateField.clear();
        birthDateField.sendKeys(birthDateValue);
        birthDateField.sendKeys(Keys.ENTER);
    }

    public void setCountry(String countryValue) {
        WebElement countryDropdownElement = driver.findElement(countryDropdown);
        countryDropdownElement.click();
        driver.findElement(countryEmptyValue).click();
        countryDropdownElement.click();
        String countryLocator = String.format("//div[contains(@class,'lk-cv-block__select-scroll_country')]/button[normalize-space(text())='%s']", countryValue);
        driver.findElement(By.xpath(countryLocator)).click();
    }

    public void setCity(String cityValue) {
        WebElement cityDropdownElement = driver.findElement(cityDropdown);
        actions.moveToElement(cityDropdownElement).build().perform();
        new WebDriverWait(driver,2).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(cityDropdownElement)).click();
        driver.findElement(cityEmptyValue).click();
        cityDropdownElement.click();
        String cityLocator = String.format("//div[contains(@class,'lk-cv-block__select-scroll_city')]/button[normalize-space(text())='%s']", cityValue);
        driver.findElement(By.xpath(cityLocator)).click();
    }

    public void setRelocate(boolean ready) {
        if (ready) {
            setReadyToRelocate();
        } else if (!ready) {
            setNotReadyToRelocate();
        }
    }

    private void setReadyToRelocate() {
        WebElement readyToRelocateCheckbox = driver.findElement(readyToRelocate);
        actions.moveToElement(readyToRelocateCheckbox).build().perform();
        // при помощи JS нажимаем на псевдоэлемент
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('input#id_ready_to_relocate_1 + span.radio__label',':before').click();");
    }

    private void setNotReadyToRelocate() {
        WebElement notReadyToRelocateCheckbox = driver.findElement(notReadyToRelocate);
        actions.moveToElement(notReadyToRelocateCheckbox).build().perform();
        // при помощи JS нажимаем на псевдоэлемент
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('input#id_ready_to_relocate_0 + span.radio__label',':before').click();");
    }

    public void addContact(String contact, String contactValue, boolean deleteOldContacts, int numOfContact) {
        // удалить уже существующие
        if (deleteOldContacts) {
            List<WebElement> deleteButtons = driver.findElements(deleteContactButton);
            if (deleteButtons.size() > 0) {
                for (WebElement button : deleteButtons) {
                    try {
                        button.click();
                    } catch (ElementNotInteractableException e){
                        System.out.println("Старых контактов нет");
                    }
                }
            }
            addNum = driver.findElements(By.xpath("//div[contains(@class,'container__row js-formset-row hide')]")).size();
        } else {
            addNum++;
        }

        // нажать на добавить
        driver.findElement(addContactButton).click();

        // выбрать значение
        driver.findElement(contactMethodDropdownButton).click();
        String messangerLocator = String.format("//div[contains(@data-num,'%d')]//div[contains(@class,'lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container')]/div[contains(@class,'lk-cv-block__select-scroll')]/button[@title='%s']", addNum, contact);
        try{
            actions.moveToElement(driver.findElement(contactMethodDropdownButton)).build().perform();
            driver.findElement(By.xpath(messangerLocator)).click();
        }
        catch (NoSuchElementException e){
            System.out.println("Такого мессенджера как " + contact + " нет в списке");
        }
        // ввести значение
        String messangerFieldLocator = String.format("//div[contains(@class,'input_straight-bottom-right') and text()='%s']/ancestor::div/following-sibling::input",contact);
        driver.findElement(By.xpath(messangerFieldLocator)).sendKeys(Keys.DOWN);
        driver.findElement(By.xpath(messangerFieldLocator)).sendKeys(contactValue);
    }

    public void clickSaveButton() {
        WebElement saveButtonElement = driver.findElement(saveButton);
        actions.moveToElement(saveButtonElement).build().perform();
        saveButtonElement.click();
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions
                        .visibilityOf(driver.findElement(saveDataLabel)));
    }


    public String getNameRu() {
        return driver.findElement(nameRu).getAttribute("value");
    }

    public String getNameLatin() {
        return driver.findElement(nameLatin).getAttribute("value");
    }

    public String getSecondnameRu() {
        return driver.findElement(secondnameRu).getAttribute("value");
    }

    public String getSecondnameLatin() {
        return driver.findElement(secondnameLatin).getAttribute("value");
    }


    public String getBlogName() {
        return driver.findElement(blogName).getAttribute("value");
    }

    public String getBirthDate() {
        return driver.findElement(birthDate).getAttribute("value");
    }

    public String getCountry() {
        return driver.findElement(countryDropdown).getText().trim();
    }

    public String getCity() {
        return driver.findElement(cityDropdown).getText().trim();
    }


    public boolean getRelocate() {
        if (driver.findElement(readyToRelocateForValue).getAttribute("checked")!= null) {
            return true;
        } else {
            return false;
        }
    }

    public Map<String, String> getContacts() {
        Map<String, String> messangerAndValues = new HashMap<String, String>();
        for(int i = 0; i < driver.findElements(contactsSelectedMessangersValues).size(); i++){
            String messanger = driver.findElements(contactsSelectedMessangersValues).get(i).getText().trim();
            String messangerAccValue = driver.findElements(contactsMessangersAccsValues).get(i).getAttribute("value");
            messangerAndValues.put(messanger,messangerAccValue);
        }
        return messangerAndValues;
    }

}
