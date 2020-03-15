package com.kolosg.Jira.testautomation.features;

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

    @FindBy(xpath = "//*[@id='versions-table']/tbody[2]/tr[@class='item-state-ready']")
    private List<WebElement> glassVersionNames;

   @FindBy(className = "versions-table__description")
    private List<WebElement> glassVersionDescriptionElements;


    public JiraGlassDocumentation(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<String> getGlassVersionNames() {
        List<String> versionNames = new ArrayList<>();
        for (WebElement version: glassVersionNames){
            versionNames.add(version.getText());
        }
        System.out.println(versionNames);
        return versionNames;
    }

    public List<String> getGlassVersionDescriptions() {
        List<String> versionDescriptions = new ArrayList<>();
        waitUntilElementLoaded(glassVersionDescriptionElements.get(0));
        for (WebElement description: glassVersionDescriptionElements){
            versionDescriptions.add(description.getText());
        }
        return versionDescriptions;
    }

    public void clickOnVersions() {
        clickOnElement(glassVersionFilterButton);
    }

}
