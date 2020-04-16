package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraBrowseIssues;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.net.MalformedURLException;

public class JiraBrowseIssuesTest {

    private JiraLogin login;
    private JiraBrowseIssues jiraBrowseIssue;

    private final String browseIssuesTestData = "/projects_and_minimum_issues.csv";


    @BeforeEach
    void setUp() throws MalformedURLException {
        login = new JiraLogin(Util.createDriver(), "yes");
        jiraBrowseIssue = new JiraBrowseIssues(login.getDriver());
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = browseIssuesTestData, numLinesToSkip = 1)
    void validateProjectNumberOfIssues(String project, int minimumNumberOfIssues) {
        jiraBrowseIssue.navigateToFilteredSearchURL();
        boolean result = jiraBrowseIssue.validateExpectedNumberOfIssuesOnProject(project, minimumNumberOfIssues);
        Assertions.assertTrue(result);
    }

}
