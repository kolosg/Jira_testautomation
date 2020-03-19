package com.codecool.bothminyatamas.javaPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePOM {
    private WebDriverWait wait;

    public BasePOM(WebDriverWait wait){
        this.wait = wait;
    }

    protected void waitUntilElementClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitUntilElementLoaded(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitUntilElementNotVisible(WebElement webElement){
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    protected void clickOnElement(WebElement webElement) {
        waitUntilElementClickable(webElement);
        webElement.click();
    }
    public void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }
}
