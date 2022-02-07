package testcase;

import main.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import pages.*;

public class PaymentCase extends BaseTest {

    HomePage homePage;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    PaymentPage paymentPage;
    DeliveryPage deliveryPage;
    SummaryPage summaryPage;
    private String email = "testotomasyon35@gmail.com";
    private String password = "Otomasyon35";
    private String username = "Betül Aksoy";
    private String productName = "kitap";

    @Test
    @Order(1)
    public void login() throws InterruptedException {
        homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.isOnHomePage(), "Error!");
        Thread.sleep(5000);
        homePage.hoverLoginButton();
        Thread.sleep(5000);
        homePage.clickLoginButton();
        loginPage = new LoginPage(driver);
        Thread.sleep(5000);
        Assertions.assertTrue(loginPage.isOnLoginPage(), "Error!");
        loginPage.typeEmail(email);
        loginPage.clickLogin();
        Thread.sleep(5000);
        Assertions.assertTrue(loginPage.isOnLoginPageToTypePassword());
        loginPage.typePassword(password);
        loginPage.clickElementSelect();
        Thread.sleep(5000);
        Assertions.assertTrue(homePage.isCorrectUsername(username), "Incorrect username!");
    }

    @Test
    @Order(2)
    public void searchProduct() throws InterruptedException {
        homePage.search(productName);
        Thread.sleep(5000);
        productsPage = new ProductsPage(driver);
        Thread.sleep(5000);
        Assertions.assertTrue(productsPage.isOnProductsPage(productName), "You are not on the products page!");
        Thread.sleep(5000);
    }

    @Test
    @Order(3)
    public void addToCart() throws InterruptedException {
        productsPage.addProduct(1);
        Thread.sleep(5000);
        cartPage = new CartPage(driver);
        Thread.sleep(5000);
        Assertions.assertTrue(cartPage.isOnCartPage(), "You are not on the cart page!");
    }

    @Test
    @Order(4)
    public void payment() throws InterruptedException {
        cartPage.clickOk();
        deliveryPage = new DeliveryPage(driver);
        Thread.sleep(5000);
        Assertions.assertTrue(deliveryPage.isUrlCorrect("teslimat"), "You are not on the delivery page!");
        Thread.sleep(5000);
        deliveryPage.clickOk();
        Thread.sleep(5000);
        paymentPage = new PaymentPage(driver);
        Thread.sleep(5000);
        Assertions.assertEquals(paymentPage.getTitle(), "Ödeme Bilgileri");
        Thread.sleep(5000);
        paymentPage.instantRemittance();
        summaryPage = new SummaryPage(driver);
        Thread.sleep(5000);
        Assertions.assertEquals(summaryPage.getTitle(), "Sipariş Özeti");
        Thread.sleep(5000);
        summaryPage.checkPaymentInfo();
        Thread.sleep(5000);
        Assertions.assertEquals(summaryPage.gettextmessage(summaryPage.akbankText), "Akbank");
    }

}

