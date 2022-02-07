package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class BasePage {
    WebDriver driver;
    By okButton = By.cssSelector("#continue_step_btn");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void mouseOver(By locator) {
        WebElement element = driver.findElement(locator);
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public void mouseOver(WebElement webElement) {
        Actions action = new Actions(driver);
        action.moveToElement(webElement).perform();
    }

    public void click(By locator) {
        find(locator).click();
    }

    public void type(By locator, String text) {
        find(locator).sendKeys(text);
    }

    public Boolean isDisplayed(By locator) {
        return find(locator).isDisplayed();
    }

    protected void scrollToElement(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement helper = find(locator);
        js.executeScript("arguments[0].scrollIntoView(true);", helper);
    }

    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    public String gettextmessage(By locator)  {
        waitFor(locator);
        return gettext(locator);

    }

    public void waitFor(By locator){
        WebDriverWait wait=new WebDriverWait(driver, 12);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String gettext(By locator){
        String text;
        text = find(locator).getText();
        return text;
    }

    public void clickOk() {
        click(okButton);
    }

    public boolean isUrlCorrect(String text) {
        boolean flag = false;
        String websitePageTitle = driver.getCurrentUrl();
        if (websitePageTitle.contains(text)) { //The reason I use the contains method is in case there can be spaces at the beginning and end of the title on the site.
            flag = true;
        }
        return flag;
    }

    public String getTitle(){
        return driver.getTitle();
    }

}
