package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage
{
    By deleteAllSelection = By.xpath("//*[@class='delete_all_2uTds']");
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnCartPage() {
        return isDisplayed(deleteAllSelection);
    }
}
