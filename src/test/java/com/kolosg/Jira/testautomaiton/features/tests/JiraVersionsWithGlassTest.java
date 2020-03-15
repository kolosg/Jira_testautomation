package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraGlassDocumentation;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.features.JiraProjectVersions;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class JiraVersionsWithGlassTest {

    private JiraLogin login;
    private JiraGlassDocumentation jiraGlassDocumentation;
    private JiraProjectVersions jiraProjectVersions;

    @BeforeEach
    void setUp() {
        login = new JiraLogin(Util.createDriver("Chrome"));
        jiraGlassDocumentation = new JiraGlassDocumentation(login.getDriver());
        jiraProjectVersions = new JiraProjectVersions(login.getDriver());
        Util.navigateToURL(login.getDriver(), Util.BASE_URL + "/secure/Dashboard.jspa");
        login.loginAttempt(Util.USERNAME, Util.PASSWORD);
        login.waitForSuccessfulLogin();
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @Test
    void validateNewProjectVersionOnGlass(){
        List<String> versionNames;
        Util.navigateToURL(login.getDriver(), Util.BASE_URL + "/plugins/servlet/project-config/PP4/versions");
        jiraProjectVersions.addNewVersion("Random version: " + Util.generateRandomNumberInRange(100), "test description");
        versionNames = jiraProjectVersions.getVersionNames();
        Util.navigateToURL(jiraProjectVersions.getDriver(), Util.BASE_URL + "/projects/PP4?selectedItem=com.codecanvas.glass:glass");
        jiraGlassDocumentation.clickOnVersions();
        Assertions.assertEquals(versionNames.size(), jiraGlassDocumentation.getGlassVersionNames().size());
        //jiraProjectVersions.deleteVersion();
    }
}
