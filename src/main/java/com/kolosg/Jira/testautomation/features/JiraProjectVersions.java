package com.kolosg.Jira.testautomation.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    @FindBy(className = "project-config-version-name")
    private WebElement versionName;

   @FindBy(className = "aui-restfultable-editable")
    private WebElement versionDescription;

    private List<WebElement> versionNameElements;
    private List<WebElement> versionDescriptionElements;



    public JiraProjectVersions(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void collectVersions() {
        versionNameElements = driver.findElements(By.className("project-config-version-name"));
    }

    public void collectDescriptions() {
        versionDescriptionElements =  driver.findElements(By.className("aui-restfultable-editable"));
    }

    public List<String> getVersionNames() {
        collectVersions();
        List<String> versionNames = new ArrayList<>();
        for (WebElement version: versionNameElements){
            versionNames.add(version.getText());
        }
        return versionNames;
    }

    public List<String> getVersionDescriptions() {
        collectDescriptions();
        List<String> versionDescriptions = new ArrayList<>();
        for (WebElement description: versionDescriptionElements){
            versionDescriptions.add(description.getText());
        }
        return versionDescriptions;
    }

    public void addNewVersion(String newVersionName, String newVersionDescription) {
        waitUntilElementLoaded(nameInputField);
        nameInputField.sendKeys(newVersionName);
        descriptionInputField.sendKeys(newVersionDescription);
        clickOnElement(addVersionButton);
    }



}
