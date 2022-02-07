package main;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;


import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(testlogger.TestResultLogger.class)

public class BaseTest {

    public static WebDriver driver;
    static String browser = System.getProperty("browser");

    @BeforeAll
    public static void setUp() throws InterruptedException {

        BaseTest.browser = "chrome";
        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxProfile profile = new FirefoxProfile();
            driver = new FirefoxDriver();
            profile.setPreference("permissions.default.desktop-notification", 1);
            profile.setPreference("--disable-popup-blocking", 1);
            profile.setPreference("--disable-gpu", 1);
            profile.setPreference("--disable-plugins", 1);
            profile.setPreference("--disable-plugins-discovery", 1);
            DesiredCapabilities capabilities;
            capabilities = new DesiredCapabilities();
            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
            driver = new FirefoxDriver(capabilities);

        } else if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            //ChromeOptions options=new ChromeOptions();
            Map<String, Object> prefs = new HashMap();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            driver = new ChromeDriver(options);
            driver.get("https://www.hepsiburada.com/");
            driver.manage().window().maximize();
        }
    }

    @BeforeEach
    public void beforeMethod() throws InterruptedException {
        Thread.sleep(5000);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    public WebDriver getDriver(String browser) {
        WebDriver driver = null;
        if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        }
        return driver;
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {

            TakesScreenshot ts = (TakesScreenshot) driver;

            File source = ts.getScreenshotAs(OutputType.FILE);// Calling method to capture screenshot

            try {
                FileHandler.copy(source, new File("/Screenshots/" + result.getName() + ".png"));
                System.out.println("Screenshot is taken");

            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }

            driver.quit();
        }
    }
}
