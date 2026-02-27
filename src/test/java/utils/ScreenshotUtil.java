package utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.apache.commons.io.FileUtils;

public class ScreenshotUtil 
{
    public static void captureScreenshot(WebDriver driver, String testName)
    {
        try
        {
            // Create screenshots folder if not exists
            File folder = new File("screenshots");
            if(!folder.exists())
            {
                folder.mkdir();
            }

            // Take screenshot
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            // File name with timestamp
            String fileName = testName + "_" + System.currentTimeMillis() + ".png";

            File dest = new File(folder, fileName);

            FileUtils.copyFile(src, dest);

            System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}