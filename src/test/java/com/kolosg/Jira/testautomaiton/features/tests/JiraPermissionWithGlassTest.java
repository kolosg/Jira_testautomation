package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraGlassDocumentation;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.features.JiraProjectPermissions;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JiraPermissionWithGlassTest {

    private JiraLogin login;
    private JiraGlassDocumentation jiraGlassDocumentation;
    private JiraProjectPermissions jiraProjectPermissions;

    private final String projectPermissionNames = "/project_permission_fields.csv";


    @BeforeEach
    void setUp() {
        login = new JiraLogin(Util.createDriver("Chrome"));
        jiraGlassDocumentation = new JiraGlassDocumentation(login.getDriver());
        jiraProjectPermissions = new JiraProjectPermissions(login.getDriver());
        Util.navigateToURL(login.getDriver(), Util.BASE_URL);
        login.loginAttempt(Util.USERNAME, Util.PASSWORD);
        login.waitForSuccessfulLogin();
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @Test
    void getasd() {
        Util.navigateToURL(login.getDriver(), Util.BASE_URL + "/plugins/servlet/project-config/PP4/permissions");
        System.out.println(jiraProjectPermissions.getSelectedPermissionGranting("Browse Projects"));

    }



}
