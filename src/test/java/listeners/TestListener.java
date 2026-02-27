package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtil;
import Base.BaseTest;

public class TestListener extends BaseTest implements ITestListener
{
    @Override
    public void onTestFailure(ITestResult result)
    {
        ScreenshotUtil.captureScreenshot(driver, result.getName());
    }
}