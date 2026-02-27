package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseTest;

public class LoginTest extends BaseTest
{

    @DataProvider(name = "loginData")
    public Object[][] loginData()
    {
        return new Object[][]
        {
            // PASS CASES
            {"standard_user", "secret_sauce", "success"},  // Pass
            {"standard_user", "wrongpass", "error"},       // Pass
            {"", "", "empty"},                             // Pass

            // FAIL CASES (intentional)
            {"standard_user", "secret_sauce", "failUrl"},  // Fail
            {"standard_user", "wrongpass", "failError"},   // Fail
            {"", "", "failValidation"}                     // Fail
        };
    }

    @Test(dataProvider = "loginData")
    public void verifyLogin(String username, String password, String expectedResult)
    {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys(username);

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.id("login-button")).click();

        // PASS CASES

        if(expectedResult.equals("success"))
        {
            Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory"),
                "Login should be successful"
            );
        }

        else if(expectedResult.equals("error"))
        {
            String errorMsg = driver.findElement(By.xpath("//h3")).getText();

            Assert.assertTrue(
                errorMsg.contains("Username and password not matched"),"Error message should appear");
        }

        else if(expectedResult.equals("empty"))
        {
            String errorMsg = driver.findElement(By.xpath("//h3")).getText();

            Assert.assertTrue(
                errorMsg.contains("required"),"Validation message should appear");
        }

        // FAIL CASES (intentional wrong assertion)

        else if(expectedResult.equals("failUrl"))
        {
            
            Assert.assertTrue(
                driver.getCurrentUrl().contains("dashboard"),"Intentional fail - wrong URL");
        }

        else if(expectedResult.equals("failError"))
        {
            String errorMsg = driver.findElement(By.xpath("//h3")).getText();

            Assert.assertTrue(
                errorMsg.contains("Success"),"Intentional fail - wrong error message");
        }

        else if(expectedResult.equals("failValidation"))
        {
            String errorMsg = driver.findElement(By.xpath("//h3")).getText();

            Assert.assertTrue(
                errorMsg.contains("Success"),"Intentional fail - wrong validation message"
            );
        }
    }
}