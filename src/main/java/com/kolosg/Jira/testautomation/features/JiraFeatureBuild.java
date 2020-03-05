package com.kolosg.Jira.testautomation.features;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


//action build for features
public abstract class JiraFeatureBuild {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String BASE_URL = Util.getEnvironmentVariable("base_url");

    protected JiraFeatureBuild(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Integer.parseInt(Util.getEnvironmentVariable("timeout_limit")));
        driver.manage().window().maximize();
    }

    protected void waitUntilElementClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitForSEC(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    protected void waitUntilElementLoaded(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void clickOnElement(WebElement webElement) {
        waitUntilElementClickable(webElement);
        webElement.click();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        driver.quit();
    }

}
