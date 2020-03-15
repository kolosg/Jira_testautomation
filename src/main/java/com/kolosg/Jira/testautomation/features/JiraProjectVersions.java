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

    @FindBy(xpath = "//*[@id=\"AJS_DROPDOWN_LISTITEM__149\"]/a")
    private WebElement deleteVersionButton;

    @FindBy(xpath = "//*[@id=\"submit\"]")
    private WebElement confirmDeleteButton;

    @FindBy(className = "project-config-version-name")
    private List<WebElement> versionNames;

   @FindBy(className = "aui-restfultable-editable")
    private List<WebElement> versionDescriptions;

    public JiraProjectVersions(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public List<String> getVersionNames() {
        List<String> versionNamesList = new ArrayList<>();
        waitUntilElementLoaded(versionNames.get(0));
        for (WebElement version : versionNames){
            versionNamesList.add(version.getText());
        }
        System.out.println(versionNamesList);
        return versionNamesList;
    }

    public List<String> getVersionDescriptions() {
        List<String> versionDescriptionsList = new ArrayList<>();
        waitUntilElementLoaded(versionDescriptions.get(0));
        for (WebElement description: versionDescriptions){
            versionDescriptionsList.add(description.getText());
        }
        return versionDescriptionsList;
    }

    public void addNewVersion(String newVersionName, String newVersionDescription) {
        waitUntilElementLoaded(nameInputField).sendKeys(newVersionName);
        descriptionInputField.sendKeys(newVersionDescription);
        clickOnElement(addVersionButton);
    }

    public void deleteVersion() {
        clickOnElement(deleteVersionButton);
        clickOnElement(confirmDeleteButton);
    }


}
