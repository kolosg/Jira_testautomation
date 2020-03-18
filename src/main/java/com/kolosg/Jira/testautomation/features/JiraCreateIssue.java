package com.kolosg.Jira.testautomation.features;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


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

    @FindBy(xpath = "//*[@id='aui-flag-container']/div/div")
    WebElement conformationPopup;

    public JiraCreateIssue(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    void selectFromDropdown(WebElement element, String select) {
        try {
            clickOnElement(element);
            element.sendKeys(select + Keys.ENTER);
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
