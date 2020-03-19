package com.kolosg.Jira.testautomation.features;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class JiraGlassDocumentation extends JiraFeatureBuild {

    private final String jiraGlassDocumentationURL = Util.BASE_URL + "/projects/PP4?selectedItem=com.codecanvas.glass:glass";

    @FindBy(xpath = "//span[@title='Glass Documentation']")
    private WebElement glassDocumentationButton;

    @FindBy(xpath = "//*[@id='components-table']//*[@class='items']")
    private WebElement glassComponentsTable;

    @FindBy(xpath = "//*[@id='aui-uid-2']")
    private WebElement glassVersionFilterButton;

    @FindBy(xpath = "//*[@id='glass-permissions-nav']/a")
    private WebElement glassPermissionFilterButton;

    @FindBy(className = "glass-true-icon")
    private WebElement glassTrueIcon;

    @FindBy(xpath = "//*[@id='versions-table']/tbody[2]/tr/td[@class='versions-table__name']")
    private List<WebElement> glassVersionNames;

    @FindBy(xpath = "//*[@id='components-table']/tbody[2]/tr/td[@class='components-table__name']")
    private List<WebElement> glassComponentNames;

    @FindBy(xpath = "//*[@id='glass-permissions-panel']/div/table/tbody/tr[@class='permtr']")
    private List<WebElement> glassPermissionRows;

      @FindBy(xpath = "//*[@id='glass-permissions-panel']/div/table/tbody/tr/td/div/p[@class='title']/b")
    private List<WebElement> glassPermissionNames;

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

    public List<String> getGlassPermissionNames() {
          return glassPermissionNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickOnVersions() {
        clickOnElement(glassVersionFilterButton);
    }

    public void clickOnPermissions() {
        clickOnElement(glassPermissionFilterButton);
    }

    public boolean getSelectedPermissionGranting(String permission) {
        System.out.println(getGlassPermissionNames());
        int permissionRow = getGlassPermissionNames().indexOf(permission);
        WebElement selectedPermission = glassPermissionRows.get(permissionRow);
        try {
            selectedPermission.findElement(By.xpath(".//td[3]/div"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getJiraGlassDocumentationURL() {
        return jiraGlassDocumentationURL;
    }
}
