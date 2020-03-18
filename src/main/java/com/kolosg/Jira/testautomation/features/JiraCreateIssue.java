package com.kolosg.Jira.testautomation.features;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class JiraCreateIssue extends JiraFeatureBuild{
    @FindBy(id = "create_link")
    WebElement createButton;

    @FindBy(id = "project-field")
    WebElement projectField;

    @FindBy(id = "issuetype-field")
    WebElement issueTypeField;

    @FindBy(id = "summary")
    WebElement summaryField;

    @FindBy(id = "create-issue-submit")
    WebElement createIssueButton;

    @FindBy(xpath = "//a[@class='cancel']")
    WebElement cancelButton;

    @FindBy(partialLinkText = "has been successfully created.")
    WebElement conformationPopup;



    public JiraCreateIssue(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    void selectFromDropdown(WebElement element, String select) {
        try {
            clickOnElement(element);
            element.sendKeys(select);
            element.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void fillInSummaryField(String summaryMessage) {
        waitUntilElementClickable(summaryField);
        summaryField.sendKeys(summaryMessage);
    }

    public void createNewIssue(String projectName, String issueName, String summary) {
        clickOnElement(createButton);
        selectFromDropdown(projectField, projectName);
        selectFromDropdown(issueTypeField, issueName);
        fillInSummaryField(summary);
        clickOnElement(createIssueButton);
    }

    public boolean validateSuccessfulIssueCreation() {
        try {
            waitUntilElementLoaded(conformationPopup);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
