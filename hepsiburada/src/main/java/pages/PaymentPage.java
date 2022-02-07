package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BasePage{

    By instantRemittanceButton = By.xpath("//*[text()='Anında Havale']");
    By akbank = By.xpath("(//*[@class='sardesPaymentPage-MoneyTransfer-money_transfer_header'])[1]");
    By nextStep = By.xpath("//*[@id='continue_step_btn']");

    public PaymentPage(WebDriver driver) {
        super(driver);
    }


    public void instantRemittance() throws InterruptedException {
        Thread.sleep(5000);
        waitFor(instantRemittanceButton);
        click(instantRemittanceButton);
        Thread.sleep(5000);
        waitFor(akbank);
        Thread.sleep(5000);
        click(akbank);
        Thread.sleep(5000);
        waitFor(nextStep);
        click(nextStep);
    }
}
