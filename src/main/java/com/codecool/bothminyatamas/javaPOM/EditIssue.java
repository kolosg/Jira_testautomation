package com.codecool.bothminyatamas.javaPOM;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditIssue extends BasePOM{
    @FindBy(id = "edit-issue")
    WebElement editIssueButton;

    @FindBy(id = "summary")
    WebElement summaryField;

    @FindBy(id = "edit-issue-submit")
    WebElement updateButton;

    @FindBy(xpath = "//*[@id='summary-val']")
    WebElement editableNameField;

    @FindBy(xpath = "//*[@id='edit-issue-dialog']//*[@class='cancel']")
    WebElement cancelLinkOnEditIssueModal;

    @FindBy(xpath = "//*[@id='edit-issue-dialog']")
    WebElement editModal;

    @FindBy(className = "simple-issue-list")
    WebElement projectIssueList;

    @FindBy(xpath = "//*[@class='projects-sidebar']//*[@class='jira-project-avatar']")
    WebElement projectImage;


    public EditIssue(WebDriverWait wait, WebDriver driver) {
        super(wait);
        PageFactory.initElements(driver, this);
    }

    public void editIssueName(String newName, boolean saveTheName){
        clickOnElement(editIssueButton);
        waitUntilElementLoaded(summaryField);
        summaryField.clear();
        summaryField.sendKeys(newName);
        if(saveTheName) {
            updateButton.click();
        }
        else {
            cancelLinkOnEditIssueModal.click();
        }
        waitUntilElementNotVisible(editModal);
    }

    public String getIssueCurrentName(){
        waitUntilElementLoaded(editableNameField);
        return editableNameField.getText();
    }

    public boolean isPresentProjectIssueList(){
        waitUntilElementLoaded(projectImage);
        return projectIssueList.isDisplayed();
    }
}
