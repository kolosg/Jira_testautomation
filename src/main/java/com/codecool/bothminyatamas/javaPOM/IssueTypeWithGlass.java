package com.codecool.bothminyatamas.javaPOM;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IssueTypeWithGlass extends BasePOM {

    @FindBy(className = "project-config-itemlist")
    WebElement issueTypeListsFromSummary;

    @FindBy(xpath = "/html/body/aui-dropdown-menu/div/aui-section")
    WebElement issueTypeListsFromDocumentation;

    @FindBy(xpath = "/html/body/div[1]/section/div[2]/div/section/header/nav/div/div[1]/ul/li[2]/a/div")
    WebElement issueTypeDropdown;


    public IssueTypeWithGlass(WebDriverWait wait, WebDriver driver) {
        super(wait);
        PageFactory.initElements(driver, this);
    }

    public WebElement getItemListFromSummary(){
        waitUntilElementLoaded(issueTypeListsFromSummary);
        System.out.println(issueTypeListsFromSummary.getText());
        return issueTypeListsFromSummary;
    }

    public WebElement getItemListFromDocumentation(){
        waitUntilElementClickable(issueTypeDropdown);
        issueTypeDropdown.click();
        waitUntilElementLoaded(issueTypeListsFromDocumentation);
        System.out.println(issueTypeListsFromDocumentation.getText());
        return issueTypeListsFromDocumentation;
    }

}
