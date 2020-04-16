package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraCreateIssue;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.net.MalformedURLException;

public class JiraCreateIssueTest{

    private JiraLogin login;
    private JiraCreateIssue createIssue;
    private final String createIssueData = "/create_issue_combinations.csv";

    @BeforeEach
    void setUp() throws MalformedURLException {
        login = new JiraLogin("yes");
        createIssue = new JiraCreateIssue(login.getDriver());
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = createIssueData, numLinesToSkip = 1)
    void testSuccessfulIssueCreation(String projectName, String issueType) {
        String testIssueSummary = "testSummaryVerificationMessage:" + Util.generateRandomNumberInRange(100);
        createIssue.createNewIssue(projectName, issueType, testIssueSummary);
        createIssue.navigateToURL(createIssue.filteredURL(testIssueSummary));
        Assertions.assertTrue(createIssue.validateSuccessfulIssueCreation());
        createIssue.clearUpTestIssues();
    }

}