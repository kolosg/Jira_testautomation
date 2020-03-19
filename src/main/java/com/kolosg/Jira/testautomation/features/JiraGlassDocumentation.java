package com.kolosg.Jira.testautomation.features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class JiraGlassDocumentation extends JiraFeatureBuild {

    @FindBy(xpath = "//span[@title='Glass Documentation']")
    private WebElement glassDocumentationButton;

    @FindBy(xpath = "//*[@id='components-table']//*[@class='items']")
    private WebElement glassComponentsTable;

    @FindBy(xpath = "//*[@id='aui-uid-2']")
    private WebElement glassVersionFilterButton;

    @FindBy(xpath = "//*[@id='versions-table']/tbody[2]/tr/td[@class='versions-table__name']")
    private List<WebElement> glassVersionNames;

    @FindBy(xpath = "///*[@id='components-table']/tbody[2]/tr/td[@class='components-table__name']")
    private List<WebElement> glassComponentNames;

    public JiraGlassDocumentation(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<String> getGlassVersionNames() {
        waitUntilElementLoaded(glassVersionNames.get(0));
        return glassVersionNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getGlassComponentNames() {
        waitUntilElementLoaded(glassComponentNames.get(0));
        return glassComponentNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickOnVersions() {
        clickOnElement(glassVersionFilterButton);
    }

}
