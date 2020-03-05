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
    private WebElement versionName;

    private List<WebElement> versionNameElements;


    protected JiraGlassDocumentation(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void collectVersions() {
        versionNameElements = driver.findElements((By) versionName);
    }

    public List<String> getVersionNames() {
        List<String> versionNames = new ArrayList<>();
        for (WebElement version: versionNameElements){
            versionNames.add(version.getText());
        }
        return versionNames;
    }

}
