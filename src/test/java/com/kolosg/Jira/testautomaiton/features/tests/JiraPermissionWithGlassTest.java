package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraGlassDocumentation;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.features.JiraProjectPermissions;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class JiraPermissionWithGlassTest {

    private JiraLogin login;
    private JiraGlassDocumentation jiraGlassDocumentation;
    private JiraProjectPermissions jiraProjectPermissions;

    private final String projectPermissionNames = "/project_permission_fields.csv";

    @BeforeEach
    void setUp() {
        login = new JiraLogin();
        jiraGlassDocumentation = new JiraGlassDocumentation(login.getDriver());
        jiraProjectPermissions = new JiraProjectPermissions(login.getDriver());
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = projectPermissionNames, numLinesToSkip = 1)
    void validateProjectPermissionWithGlass(String permissionName) {
        Util.navigateToURL(login.getDriver(), jiraProjectPermissions.getJiraProjectPermissionURL());
        boolean result = jiraProjectPermissions.validatePermissionGrant(permissionName);
        Util.navigateToURL(jiraProjectPermissions.getDriver(), jiraGlassDocumentation.getJiraGlassDocumentationURL());
        jiraGlassDocumentation.clickOnPermissions();
        Assertions.assertTrue(result && jiraGlassDocumentation.getSelectedPermissionGranting(permissionName));
    }



}
