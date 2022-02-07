package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    By accountButton = By.xpath("//*[@id='myAccount']");
    By logo = By.xpath("//*[@title='Hepsiburada']");
    By customButtonText = By.xpath("(//*[@id='customButtonText'])[1]");
    By loginBtn = By.xpath("(//*[text()='Giriş Yap'])[2]");
    By usernameField = By.xpath("//*[@class=\"sf-OldMyAccount-1k66b\"]");
    By searchButton = By.xpath("//*[@class=\"SearchBoxOld-buttonContainer\"]");
    By searchField = By.xpath("//*[@class=\"desktopOldAutosuggestTheme-input\"]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void hoverLoginButton() {
        mouseOver(accountButton);
    }

    public boolean isOnHomePage() {
        return isDisplayed(logo) && isDisplayed(customButtonText);
    }

    public void clickLoginButton() {
        click(loginBtn);
    }


    public boolean isCorrectUsername(String username) {
        boolean flag = false;
        String usernametext = find(usernameField).getText();
        if (usernametext.equals(username)) {
            flag = true;
        }
        return flag;
    }

    public void search(String productName) {
        click(searchField);
        type(searchField,productName);
        click(searchButton);
    }
}
