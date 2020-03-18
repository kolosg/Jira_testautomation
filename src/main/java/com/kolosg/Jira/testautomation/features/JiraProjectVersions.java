package com.kolosg.Jira.testautomation.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class JiraProjectVersions extends JiraFeatureBuild{

    @FindBy(xpath = "//*[@id='project-config-versions-table']/tbody[1]/tr/td[3]/input[1]")
    private WebElement nameInputField;

    @FindBy(xpath = "//*[@id='project-config-versions-table']/tbody[1]/tr/td[7]/input")
    private WebElement addVersionButton;

    @FindBy(xpath = "//*[@id='project-config-versions-table']/tbody[1]/tr/td[4]/input")
    private WebElement descriptionInputField;

    @FindBy(partialLinkText = "Delete")
    private WebElement deleteVersionButton;

    @FindBy(xpath = "//*[@id='submit']")
    private WebElement confirmDeleteButton;

    @FindBy(className = "project-config-version-name")
    private List<WebElement> versionNames;

    public JiraProjectVersions(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<String> getVersionNames() {
        List<String> versionNamesList = new ArrayList<>();
        for (WebElement version : versionNames){
            versionNamesList.add(waitUntilElementLoaded(version).getText());
        }
        return versionNamesList;
    }

    public void addNewVersion(String newVersionName, String newVersionDescription) {
        waitUntilElementLoaded(nameInputField).sendKeys(newVersionName);
        descriptionInputField.sendKeys(newVersionDescription);
        clickOnElement(addVersionButton);
    }

    public void deleteVersion(String versionName) {
        Actions actions = new Actions(driver);
        String xpath = "//tr[@data-name = \"" + versionName + "\"]/td[@class = 'aui-restfultable-operations']";
        WebElement element = driver.findElement(By.xpath(xpath));
        actions.moveToElement(element).moveToElement(element).build().perform();
        clickOnElement(driver.findElement(By.xpath(xpath + "/a")));
        clickOnElement(deleteVersionButton);
        clickOnElement(confirmDeleteButton);
    }

}
