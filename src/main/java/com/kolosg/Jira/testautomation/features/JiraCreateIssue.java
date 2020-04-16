package com.kolosg.Jira.testautomation.features;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class JiraCreateIssue extends JiraFeatureBuild{
    @FindBy(id = "create_link")
    WebElement createButton;

    @FindBy(xpath = "//label[text()='Project']")
    WebElement projectField;

    @FindBy(xpath = "//label[text()='Issue Type']")
    WebElement issueTypeField;

    @FindBy(xpath = "//label[text()='Summary']")
    WebElement summaryField;

    @FindBy(xpath = "//*[@id='create-issue-submit']")
    WebElement createIssueButton;

    @FindBy(xpath = "//a[@class='cancel']")
    WebElement cancelButton;

    @FindBy(xpath = "//*[@id='summary-val']")
    WebElement conformationName;

    @FindBy(xpath = "//*[@id='opsbar-operations_more']/span")
    WebElement moreButton;

    @FindBy(xpath = "//*[@id='delete-issue']/a")
    WebElement deleteButton;

    @FindBy(xpath = "//*[@id='create-issue-dialog']")
    WebElement createIssueScreen;

    @FindBy(xpath = "//*[@id='delete-issue-submit']")
    WebElement confirmDeleteButton;

    public JiraCreateIssue(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void selectFromDropdown(WebElement element, String name) {
        waitUntilElementTextFound(element);
        element.click();
        driver.switchTo().activeElement().sendKeys(name + Keys.ENTER);
    }

    public void createNewIssue(String projectName, String issueName, String summary) {
        clickOnElement(createButton);
        selectFromDropdown(projectField, projectName);
        selectFromDropdown(issueTypeField, issueName);
        selectFromDropdown(summaryField, summary);
        clickOnElement(createIssueButton);
    }

    public boolean validateSuccessfulIssueCreation() {
        try {
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.partialLinkText(" has been successfully created."))));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String filteredURL(String testIssueName) {
        System.out.println(Util.BASE_URL + "/issues/?jql=text%20~%20\"" + testIssueName + "\"");
        return Util.BASE_URL + "/issues/?jql=text%20~%20\"" + testIssueName + "\"";
    }

    public void clearUpTestIssues() {
        clickOnElement(moreButton);
        clickOnElement(deleteButton);
        clickOnElement(confirmDeleteButton);
        }

    }
