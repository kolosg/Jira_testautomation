package com.kolosg.Jira.testautomation.features;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeOptions;
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
        this.wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(Util.getEnvironmentVariable("timeout_limit")), TimeUnit.SECONDS);
    }

    protected WebElement waitUntilElementClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }

    protected WebElement waitUntilElementLoaded(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    protected void clickOnElement(WebElement webElement) {
        waitUntilElementClickable(webElement).click();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        driver.quit();
    }

}
