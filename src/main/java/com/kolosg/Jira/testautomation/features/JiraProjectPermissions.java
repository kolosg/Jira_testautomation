package com.kolosg.Jira.testautomation.features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class JiraProjectPermissions extends JiraFeatureBuild {  //table[@class='aui jira-admin-table']/tbody/tr/td/p[@class='title']

    @FindBy(xpath = "//table[@class='aui jira-admin-table']/tbody/tr/td/p[@class='title']")
    List<WebElement> projectPermissions;

    @FindBy(xpath = "//table[@class='aui jira-admin-table']/tbody/tr/td/dl/dd[string-length(text()) > 0]")
    List<WebElement> projectPermissionGrantings;

    @FindBy(xpath = "//table[@class='aui jira-admin-table']/tbody/tr")
    List<WebElement> projectPermissionRows;


    public JiraProjectPermissions(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<String> getProjectPermissionNames() {
        return projectPermissions.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getProjectPermissionGrantings() {
        return projectPermissionGrantings.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getSelectedPermissionGranting(String permission) {
        int permissionRow = getProjectPermissionNames().indexOf(permission);
        WebElement selectedPermission = projectPermissionRows.get(permissionRow);
        return selectedPermission.findElement(By.xpath(".//dd")).getText();
    }

    public boolean validatePermissionGrant(String permission){
        return getSelectedPermissionGranting(permission).equalsIgnoreCase("Any logged in user");
    }

}
