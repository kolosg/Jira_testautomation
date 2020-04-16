package com.kolosg.Jira.testautomation.features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.chrome.ChromeOptions;


//action build for features
public abstract class JiraFeatureBuild {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String BASE_URL = Util.getEnvironmentVariable("base_url");

    public JiraFeatureBuild(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    protected WebElement waitUntilElementClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }

    protected WebElement waitUntilElementLoaded(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    protected boolean waitUntilElementTextFound(WebElement webElement) {
        try {
            webElement.isDisplayed();
            return true;
        } catch (Exception e) {
            System.out.println("No Element Found!");
            return false;
        }
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

    public void navigateToURL(String URL) {
        driver.get(URL);
    }

}
