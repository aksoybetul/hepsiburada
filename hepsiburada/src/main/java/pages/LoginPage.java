package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    By signUpButton = By.xpath("//*[contains(text(),'Üye ol')]");
    By emailField = By.xpath("//*[@id='txtUserName']");
    By welcomeTitle = By.xpath("//div[contains(text(),'Hoş geldiniz')]");
    By useDiffAccount = By.xpath("//*[text()=\"Farklı hesap kullan\"]");
    By passwordField = By.xpath("//*[@id='txtPassword']");
    By elementSelectButton = By.xpath("//*[@id='btnEmailSelect']");
    By closeButton = By.xpath("//*[@class='hb-AxhVE dVER scdvp0pq943']");
    private By loginButton = By.xpath("//*[@id='btnLogin']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnLoginPage() {
        return isDisplayed(signUpButton);
    }

    public void typeEmail(String email) {
        click(emailField);
        type(emailField,email);
    }

    public boolean isOnLoginPageToTypePassword() {
        return isDisplayed(useDiffAccount);
    }

    public void typePassword(String password) {
        type(passwordField,password);
    }

    public void clickElementSelect() {
        click(elementSelectButton);
    }


    public void clickLogin() {
        click(loginButton);
    }
}
