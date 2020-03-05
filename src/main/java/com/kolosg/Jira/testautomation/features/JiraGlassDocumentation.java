package com.kolosg.Jira.testautomation.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class JiraGlassDocumentation extends JiraFeatureBuild {

    @FindBy(xpath = "//span[@title='Glass Documentation']")
    private WebElement glassDocumentationButton;

    @FindBy(xpath = "//*[@id='components-table']//*[@class='items']")
    private WebElement glassComponentsTable;

    @FindBy(xpath = "//*[@id='aui-uid-2']")
    private WebElement glassVersionFilterButton;

    @FindBy(className = "item-state-ready")
    private WebElement glassVersionName;

   @FindBy(className = "versions-table__description")
    private WebElement glassVersionDescriptionElement;

    private List<WebElement> glassVersionNameElements;
    private List<WebElement> glassVersionDescriptionElements;


    public JiraGlassDocumentation(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void collectGlassVersions() {
        glassVersionNameElements = driver.findElements((By) glassVersionName);
    }

    public void collectGlassDescriptions() {
        glassVersionDescriptionElements = driver.findElements((By) glassVersionDescriptionElement);
    }

    public List<String> getVersionNames() {
        collectGlassVersions();
        List<String> versionNames = new ArrayList<>();
        for (WebElement version: glassVersionNameElements){
            versionNames.add(version.getText());
        }
        return versionNames;
    }

    public List<String> getGlassVersionDescriptions() {
        collectGlassDescriptions();
        List<String> versionDescriptions = new ArrayList<>();
        for (WebElement description: glassVersionDescriptionElements){
            versionDescriptions.add(description.getText());
        }
        return versionDescriptions;
    }

    public void clickOnVersions() {
        clickOnElement(glassVersionFilterButton);
    }

}
