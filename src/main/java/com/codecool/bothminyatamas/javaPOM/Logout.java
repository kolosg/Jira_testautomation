package com.codecool.bothminyatamas.javaPOM;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Logout extends BasePOM{

    @FindBy(id = "login-form-username")
    private WebElement loginUsernameField;

    @FindBy(id = "login-form-password")
    private WebElement loginPasswordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='header-details-user-fullname']/span/span/img")
    WebElement profilePicture;

    @FindBy(xpath = "//*[@id='log_out']")
    WebElement logOutButton;

    public Logout(WebDriver driver, WebDriverWait wait) {
        super(wait);
        PageFactory.initElements(driver, this);
    }

    public void logout(){
        profilePicture.click();
        waitUntilElementClickable(logOutButton);
        logOutButton.click();
    }

    public boolean isLoggedOut(WebDriver driver){
        driver.get("https://jira.codecool.codecanvas.hu/secure/ViewProfile.jspa");
        String expectedErrorMessage = "You must log in to access this page.";
        String errorMessage = driver.findElement(By.xpath("/html/body/div/section/div/div/section/form/div[1]/div[1]/p[1]")).getText();
        return expectedErrorMessage.equals(errorMessage);
    }

    public void login() {
        waitUntilElementLoaded(loginUsernameField);
        loginUsernameField.sendKeys(Util.USERNAME);
        loginPasswordField.sendKeys(Util.PASSWORD);
        clickOnElement(loginButton);
    }
}
