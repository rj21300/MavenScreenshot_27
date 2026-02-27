package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage 
{
    WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) 
    {
        this.driver = driver;
    }

    // Locators
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.xpath("//h3[@data-test='error']");

    // Open Login Page
    public void openLoginPage()
    {
        driver.get("https://www.saucedemo.com/");
    }

    // Login Method
    public void login(String username, String password)
    {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);

        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(loginButton).click();
    }

    // Get Current URL (for success validation)
    public String getURL()
    {
        return driver.getCurrentUrl();
    }

    // Get Page Title (optional validation)
    public String getPageTitle()
    {
        return driver.getTitle();
    }

    // Get Error Message (for failed login validation)
    public String getErrorMessage()
    {
        try
        {
            return driver.findElement(errorMessage).getText();
        }
        catch(Exception e)
        {
            return "";
        }
    }

}