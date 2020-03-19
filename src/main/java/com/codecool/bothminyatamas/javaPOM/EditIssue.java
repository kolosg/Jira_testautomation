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

    @FindBy(xpath = "//*[@id=\"summary-val\"]")
    WebElement editableNameField;

    @FindBy(xpath = "/html/body/div[7]/div[2]/div[1]/div/form/div[2]/div/a")
    WebElement cancelLinkOnEditIssueModal;

    @FindBy(xpath = "/html/body/div[6]")
    WebElement editModal;


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
}
