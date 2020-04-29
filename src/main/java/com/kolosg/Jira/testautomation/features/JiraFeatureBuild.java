package com.kolosg.Jira.testautomation.features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class JiraFeatureBuild {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public JiraFeatureBuild(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    protected WebElement waitUntilElementClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }

    protected WebElement waitUntilElementLoaded(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    protected void waitUntilElementTextFound(WebElement webElement) {
        try {
            webElement.isDisplayed();
        } catch (Exception e) {
            System.out.println("No Element Found!");
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
}
