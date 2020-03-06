package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraBrowseProject;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.kolosg.Jira.testautomation.utility.Util.BASE_URL;

public class JiraBrowseProjectsTest {

    private JiraLogin login;
    private JiraBrowseProject jiraBrowseProject;

    @BeforeEach
    void setUp() {
        login = new JiraLogin(Util.createDriver("Chrome"));
        jiraBrowseProject = new JiraBrowseProject(login.getDriver());
        Util.navigateToURL(login.getDriver(), BASE_URL + "/secure/Dashboard.jspa");
        login.loginAttempt(Util.USERNAME, Util.PASSWORD);
        login.waitForSuccessfulLogin();
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @Test
    void validateProjectsExist() {
        Util.navigateToURL(login.getDriver(), BASE_URL + "/secure/BrowseProjects.jspa");
        Assertions.assertTrue(jiraBrowseProject.validateProjectsExist1());
    }

}
