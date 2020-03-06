package com.kolosg.Jira.testautomation.features;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class JiraLogin extends JiraFeatureBuild{

    private final String validationURL = Util.BASE_URL + "/secure/Viewprofile.jspa";

    @FindBy(id = "login-form-username")
    private WebElement loginUsernameField;

    @FindBy(id = "login-form-password")
    private WebElement loginPasswordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='usernameerror']/p")
    private WebElement errorMessage;

    public JiraLogin(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void loginAttempt(String username, String password) {
        waitUntilElementLoaded(loginUsernameField);
        loginUsernameField.sendKeys(username);
        loginPasswordField.sendKeys(password);
        clickOnElement(loginButton);
        Util.waitForSEC(driver, 10);

    }

    public void getCAPTCHA() {
        waitUntilElementLoaded(loginUsernameField);
        loginUsernameField.sendKeys(Util.USERNAME);
        loginPasswordField.sendKeys("invalidPassword");
        clickOnElement(loginButton);
        waitUntilElementLoaded(errorMessage);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getValidationURL() {
        return validationURL;
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
