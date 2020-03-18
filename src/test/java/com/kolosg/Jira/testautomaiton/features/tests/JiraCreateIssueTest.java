package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraCreateIssue;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JiraCreateIssueTest{

    private JiraLogin login;
    private JiraCreateIssue createIssue;
    private final String createIssueData = "/create_issue_combinations.csv";



    @BeforeEach
    void setUp() {
        login = new JiraLogin(Util.createDriver("Chrome"));
        createIssue = new JiraCreateIssue(login.getDriver());
        Util.navigateToURL(login.getDriver(), Util.BASE_URL);
        login.loginAttempt(Util.USERNAME, Util.PASSWORD);
        login.waitForSuccessfulLogin();
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = createIssueData, numLinesToSkip = 1)
    void testSuccessfulIssueCreation(String projectName, String issueType) {
        createIssue.createNewIssue(projectName, issueType, "test-summary");
        assertTrue(createIssue.validateSuccessfulIssueCreation());
    }


}
