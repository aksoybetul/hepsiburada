package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SummaryPage  extends BasePage{

    By paymentInfoText = By.xpath("//*[text()='Ödeme bilgileri']");
    public By akbankText = By.xpath("//*[text()='Akbank']");

    public SummaryPage(WebDriver driver) {
        super(driver);
    }


    public void checkPaymentInfo() {
        scrollToElement(paymentInfoText);
        waitFor(akbankText);
    }
}
