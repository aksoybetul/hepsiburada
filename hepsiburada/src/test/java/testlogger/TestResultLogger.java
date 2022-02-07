package testlogger;

import logger.Log;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import main.BaseTest;




public class TestResultLogger extends BaseTest implements TestWatcher  {

    //java klasornun altındali log klasoru
    Log log = new Log();


    @Override
    public void testSuccessful(ExtensionContext context) {
        String testName = context.getDisplayName();
        log.info(testName + " PASSED");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        String testFailCause = cause.getMessage() ;
        log.error(testName + " FAILED with cause : " + testFailCause);
    }
    public void takeScreenShot()
    {

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String projectPath = System.getProperty("user.dir");
        String filepath = (projectPath + "\\ScreenShots\\screenshot_" + System.currentTimeMillis() + ".png");
        try {
            FileUtils.copyFile(screenshot, new File(filepath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }}

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {

    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {

    }
}
