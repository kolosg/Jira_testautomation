package com.kolosg.Jira.testautomation.features;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JiraBrowseProject extends JiraFeatureBuild{

    @FindBy(className = "aui-page-header")
    WebElement pageHeader;

    @FindBy(className = "cell-type-name")
    WebElement projectNames;

    @FindBy(partialLinkText = "JETI")
    WebElement jetiProject;

    @FindBy(xpath = "//*[@id='projects']/div/table/tbody/tr[2]/td[1]/a")
    WebElement coalaProject;

    @FindBy(partialLinkText = "TOUCAN")
    WebElement toucanProject;

    @FindBy(id ="issuedetails")
    WebElement issueDetails;

    private List<WebElement> projects;

    public JiraBrowseProject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void collectProjects() {
        projects = driver.findElements(By.className("aui-avatar aui-avatar-small aui-avatar-project jira-system-avatar"));
    }

    public boolean validateProjectsExist1() {
        waitUntilElementLoaded(pageHeader);
        //collectProjects();
        //System.out.println(projects);
        return jetiProject.isDisplayed() && coalaProject.isDisplayed() && toucanProject.isDisplayed();
    }

    public void clickOnProjectName(WebElement projectName) {
        clickOnElement(projectName);
    }


}
