package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage{

    By sortingBar = By.xpath("//*[@class=\"horizontalSortingBar-toggle\"]");
    By product = By.cssSelector("li.productListContent-item");
    By addToCartButton = By.cssSelector("button[data-test-id='product-info-button']");
    By myShoppingCart = By.xpath("//*[@id='shoppingCart']");
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnProductsPage(String productName) {
        return isDisplayed(sortingBar);
    }


    private List<WebElement> getAllProducts(){
        return findAll(product);
    }

    public void addProduct(int i) throws InterruptedException {
        WebElement productElement = getAllProducts().get(i);
        scrollToElement(product);
        Thread.sleep(5000);
        mouseOver(productElement);
        Thread.sleep(5000);
        waitFor(addToCartButton);
        Thread.sleep(5000);
        click(addToCartButton);
        Thread.sleep(5000);
        click(myShoppingCart);

    }
}
