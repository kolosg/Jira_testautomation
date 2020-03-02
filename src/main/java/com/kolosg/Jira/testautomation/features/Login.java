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
    WebElement loginUsernameField;

    @FindBy(id = "login-form-password")
    WebElement loginPasswordField;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(xpath = "//*[@id='header-details-user-fullname']/span/span/img")
    WebElement profilePicture;

    protected Login(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
