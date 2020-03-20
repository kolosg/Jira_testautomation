package com.codecool.bothminyatamas.testcases;

import com.codecool.bothminyatamas.javaPOM.EditIssue;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import java.time.Duration;

public class JiraEditIssuesTest extends BaseTest {
    private JiraLogin login = new JiraLogin(driver);
    private EditIssue editIssue = new EditIssue(wait, driver);

    private String newSummaryName = "New Summary name";
    private String originalSummaryName = "Original summary name";

    @BeforeEach
    void loginToJira(){
        driver.get(Util.BASE_URL);
        driver.manage().window().maximize();
        login.loginAttempt(Util.USERNAME, Util.PASSWORD);
        login.waitForSuccessfulLogin();
    }

    @Test
    public void testToValidateIssueEditing() {
        Util.navigateToURL(driver,Util.BASE_URL + "/projects/MTP/issues/MTP-656");
        editIssue.editIssueName(originalSummaryName,true);
        editIssue.editIssueName(newSummaryName, true);

        //go back to the main page
        Util.navigateToURL(driver,Util.BASE_URL);
        login.waitForSuccessfulLogin();

        //go to the project page
        Util.navigateToURL(driver,Util.BASE_URL + "/projects/MTP/issues/MTP-656");
        Assertions.assertEquals(newSummaryName ,editIssue.getIssueCurrentName());
        editIssue.editIssueName(originalSummaryName, true);
    }

    @Test
    public void test_To_Ensure_Cancelling_Issue_Editing() {

        Util.navigateToURL(driver,Util.BASE_URL + "/projects/MTP/issues/MTP-656");
        editIssue.editIssueName(originalSummaryName, true);

        //go back to the main page
        Util.navigateToURL(driver,Util.BASE_URL);
        login.waitForSuccessfulLogin();

        //go to the project page
        Util.navigateToURL(driver,Util.BASE_URL + "/projects/MTP/issues/MTP-656");
        editIssue.editIssueName(newSummaryName, false);
        wait.withTimeout(Duration.ofSeconds(2));

        //go back to the main page
        Util.navigateToURL(driver,Util.BASE_URL);
        login.waitForSuccessfulLogin();

        //go to the project page
        Util.navigateToURL(driver,Util.BASE_URL + "/projects/MTP/issues/MTP-656");
        Assertions.assertNotEquals(newSummaryName, editIssue.getIssueCurrentName());
    }

    @Test
    public void test_To_Ensure_Empty_Credential_Not_Allowed() {
        Util.navigateToURL(driver,Util.BASE_URL + "projects/MTP/issues/MTP-656");
        editIssue.editIssueName("", true);

        Assertions.assertEquals("You must specify a summary of the issue.",
                driver.findElement(By.xpath("//*[@id=\"edit-issue-dialog\"]/div[2]/div[1]/div/form/div[1]/div/div[1]/div")).getText());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/projects.csv", numLinesToSkip = 1)
    public void edibilityDifferentProjects(String projectName){
        Util.navigateToURL(driver, Util.BASE_URL + "projects/" + projectName);
        Assertions.assertTrue(editIssue.isPresentProjectIssueList());
    }
}
