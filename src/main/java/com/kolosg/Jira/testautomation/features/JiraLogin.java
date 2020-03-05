package com.kolosg.Jira.testautomation.features;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends JiraFeatureBuild{
    private final String USERNAME = Util.getEnvironmentVariable("jira_username");
    private final String PASSWORD = Util.getEnvironmentVariable("jira_password");

    @FindBy(id = "login-form-username")
    private WebElement loginUsernameField;

    @FindBy(id = "login-form-password")
    private WebElement loginPasswordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='header-details-user-fullname']/span/span/img")
    private WebElement profilePicture;

    public Login(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.get(BASE_URL);
    }

    public void loginAttempt(String username, String password) {
        waitUntilElementLoaded(loginUsernameField);
        loginUsernameField.sendKeys(username);
        loginPasswordField.sendKeys(password);
        clickOnElement(loginButton);
    }

    public boolean validateLogin() {
        waitForSEC(Integer.parseInt(Util.getEnvironmentVariable("waiting_seconds")));
        return profilePicture.isDisplayed();
    }

    public void loginValidation() {
        Util.navigateToURL(driver, validationURL);
    }

    public void loginValidation() {
        Util.navigateToURL(driver, validationURL);
    }

    /*
    //another way to validate
    public int getNumberOfGadgetsOfPageForValidation() {
        waitForSEC(Integer.parseInt(Util.getEnvironmentVariable("waiting_seconds")));
        return driver.findElements(By.id("gadget")).size();
    }*/

}
