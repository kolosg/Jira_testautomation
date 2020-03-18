package com.kolosg.Jira.testautomation.features;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath = "//*[@id='aui-flag-container']/div/div")
    WebElement conformationPopup;

    @FindBy(xpath = "//span[@class='dropdown-text'][text()='More']")
    WebElement moreButton;

    @FindBy(xpath = "//*[@id='delete-issue']/a")
    WebElement deleteButton;

    @FindBy(xpath = "//*[@id='delete-issue-submit']")
    WebElement confirmDeleteButton;

    @FindBy(xpath = "//*[@id='content']/div[1]/div[3]/div/div/div/div/div/div/div/div[1]/div[1]/div/div[1]/div[2]/div/ol/li")
    List<WebElement> collectedIssues;

    private final String searchForTestIssuesURL = Util.BASE_URL + "/issues/?jql=text%20~%20\"testSummaryVerificationMessage\"";

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

    public void clearUpTestIssues() {
        Util.navigateToURL(driver, searchForTestIssuesURL);
        for (WebElement issue : collectedIssues) {
            clickOnElement(issue);
            clickOnElement(moreButton);
            clickOnElement(deleteButton);
            clickOnElement(confirmDeleteButton);
        }

    }

}
