package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraGlassDocumentation;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.features.JiraProjectVersions;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.List;

public class JiraVersionsWithGlassTest {

    private JiraLogin login;
    private JiraGlassDocumentation jiraGlassDocumentation;
    private JiraProjectVersions jiraProjectVersions;

    @BeforeEach
    void setUp() throws MalformedURLException {
        login = new JiraLogin(Util.createDriver());
        login.setUpLogin();
        jiraGlassDocumentation = new JiraGlassDocumentation(login.getDriver());
        jiraProjectVersions = new JiraProjectVersions(login.getDriver());
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @Test
    void validateNewProjectVersionOnGlass(){
        List<String> versionNames;
        String testVersionName = "Random version: " + Util.generateRandomNumberInRange(100);
        Util.navigateToURL(login.getDriver(), jiraProjectVersions.getJiraProjectVersionURL());
        jiraProjectVersions.addNewVersion(testVersionName);
        versionNames = jiraProjectVersions.getVersionNames();
        Util.navigateToURL(jiraProjectVersions.getDriver(), jiraGlassDocumentation.getJiraGlassDocumentationURL());
        jiraGlassDocumentation.clickOnVersions();
        Assertions.assertEquals(versionNames.contains(testVersionName), jiraGlassDocumentation.getGlassVersionNames().contains(testVersionName));
        Util.navigateToURL(login.getDriver(), jiraProjectVersions.getJiraProjectVersionURL());
        jiraProjectVersions.deleteVersion(testVersionName);
    }
}
