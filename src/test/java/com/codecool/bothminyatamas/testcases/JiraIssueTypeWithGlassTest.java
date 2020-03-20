package com.codecool.bothminyatamas.testcases;

import com.codecool.bothminyatamas.javaPOM.IssueTypeWithGlass;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;

public class JiraIssueTypeWithGlassTest extends BaseTest{
    private JiraLogin login = new JiraLogin(driver);
    private IssueTypeWithGlass issueTypeWithGlass = new IssueTypeWithGlass(wait,driver);

    @BeforeEach
    void loginToJira(){
        driver.get(Util.BASE_URL);
        driver.manage().window().maximize();
        login.loginAttempt(Util.USERNAME, Util.PASSWORD);
        login.waitForSuccessfulLogin();
    }

    @Test
    void verifyIssueTypes(){
        Util.navigateToURL(driver,Util.BASE_URL + "/plugins/servlet/project-config/PP1/summary");
        String issueTypesFromSummary = issueTypeWithGlass.getItemListFromSummary().getText().toLowerCase();
        Util.navigateToURL(driver,Util.BASE_URL + "/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        String issueTypesFromDocumentation = issueTypeWithGlass.getItemListFromDocumentation().getText().toLowerCase();

        issueTypesFromSummary = issueTypeWithGlass.removeDuplicatedWordsFromString(issueTypesFromSummary);
        issueTypesFromDocumentation = issueTypeWithGlass.removeDuplicatedWordsFromString(issueTypesFromDocumentation);

        issueTypesFromSummary = issueTypeWithGlass.orderByAlphabeticcalyWordsInAString(issueTypesFromSummary);
        issueTypesFromDocumentation = issueTypeWithGlass.orderByAlphabeticcalyWordsInAString(issueTypesFromDocumentation);

        Assertions.assertEquals(issueTypesFromSummary, issueTypesFromDocumentation);
    }
}
