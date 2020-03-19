package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraCreateIssue;
import com.kolosg.Jira.testautomation.features.JiraLogin;
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
        login = new JiraLogin();
        createIssue = new JiraCreateIssue(login.getDriver());
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = createIssueData, numLinesToSkip = 1)
    void testSuccessfulIssueCreation(String projectName, String issueType) {
        createIssue.createNewIssue(projectName, issueType, "testSummaryVerificationMessage");
        assertTrue(createIssue.validateSuccessfulIssueCreation());
        createIssue.clearUpTestIssues();
    }

}