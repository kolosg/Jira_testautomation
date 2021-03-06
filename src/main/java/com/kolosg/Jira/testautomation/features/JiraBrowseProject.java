package com.kolosg.Jira.testautomation.features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JiraBrowseProject extends JiraFeatureBuild{

    @FindBy(className = "cell-type-name")
    WebElement projectNames;

    @FindBy(partialLinkText = "JETI")
    WebElement jetiProject;

    @FindBy(xpath = "//*[@id='projects']/div/table/tbody/tr[2]/td[1]/a")
    WebElement coalaProject;

    @FindBy(partialLinkText = "TOUCAN")
    WebElement toucanProject;

    @FindBy(className = "aui-avatar aui-avatar-small aui-avatar-project jira-system-avatar")
    List<WebElement> projects;

    public JiraBrowseProject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean validateProjectsExist1() {
        return waitUntilElementLoaded(jetiProject).isDisplayed() &&
               waitUntilElementLoaded(coalaProject).isDisplayed() &&
               waitUntilElementLoaded(toucanProject).isDisplayed();
    }

    public void clickOnProjectName(WebElement projectName) {
        clickOnElement(projectName);
    }


}
