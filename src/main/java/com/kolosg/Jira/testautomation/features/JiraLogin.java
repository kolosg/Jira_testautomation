package com.kolosg.Jira.testautomation.features;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class JiraLogin extends JiraFeatureBuild{

    private final String validationURL = Util.BASE_URL + "/secure/Viewprofile.jspa";

    @FindBy(xpath = "//input[@id='login-form-username']")
    private WebElement loginUsernameField;

    @FindBy(xpath = "//input[@id='login-form-password']")
    private WebElement loginPasswordField;

    @FindBy(xpath = "//input[@id='login-form-submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='usernameerror']/p")
    private WebElement errorMessage;

    @FindBy(xpath = "//*[@id='header-details-user-fullname']//img")
    WebElement userProfilePicture;

    public JiraLogin(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForSuccessfulLogin() {
        waitUntilElementLoaded(userProfilePicture);
    }

    public void loginAttempt(String username, String password) {
        waitUntilElementLoaded(loginUsernameField);
        loginUsernameField.sendKeys(username);
        loginPasswordField.sendKeys(password);
        clickOnElement(loginButton);
    }

    public String getValidationURL() {
        return validationURL;
    }

    public void loginValidation() {
        Util.navigateToURL(driver, validationURL);
    }

    public void setUpLogin() {
        Util.navigateToURL(driver, Util.BASE_URL);
        loginAttempt(Util.USERNAME, Util.PASSWORD);
        waitForSuccessfulLogin();
    }

    public void getCAPTCHA() {
        loginAttempt(Util.USERNAME, "invalidPassword");
        waitUntilElementLoaded(errorMessage);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
