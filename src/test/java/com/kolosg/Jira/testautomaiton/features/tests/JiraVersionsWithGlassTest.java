package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraGlassDocumentation;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.features.JiraProjectVersions;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.kolosg.Jira.testautomation.utility.Util.BASE_URL;

public class JiraVersionsWithGlassTest {

    private JiraLogin login;
    private JiraGlassDocumentation jiraGlassDocumentation;
    private JiraProjectVersions jiraProjectVersions;
    private final String loginTestData = "/login_test_data.csv";

    @BeforeEach
    void setUp() {
        login = new JiraLogin(Util.createDriver("Chrome"));
        jiraGlassDocumentation = new JiraGlassDocumentation(login.getDriver());
        jiraProjectVersions = new JiraProjectVersions(login.getDriver());
        Util.navigateToURL(login.getDriver(), BASE_URL + "/secure/Dashboard.jspa");
        login.loginAttempt(Util.USERNAME, Util.PASSWORD);
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @Test
    void validateNewProjectVersionOnGlass(){
        List<String> versionNames;
        List<String> versionDescriptions;
        List<String> glassVersionNames;
        List<String> glassVersionDescriptions;
        Util.openNewTab(login.getDriver());
        ArrayList<String> tabs = new ArrayList<String>(login.getDriver().getWindowHandles());
        login.getDriver().switchTo().window(tabs.get(0));
        Util.navigateToURL(login.getDriver(), BASE_URL + "/plugins/servlet/project-config/PP4/versions");
        jiraProjectVersions.addNewVersion("9999ProjectVersion", "test description");
        versionNames = jiraProjectVersions.getVersionNames();
        versionDescriptions = jiraProjectVersions.getVersionDescriptions();
        login.getDriver().switchTo().window(tabs.get(1));
        Util.navigateToURL(jiraProjectVersions.getDriver(), BASE_URL + "/projects/PP4?selectedItem=com.codecanvas.glass:glass");
        jiraGlassDocumentation.clickOnVersions();
        glassVersionNames = jiraGlassDocumentation.getVersionNames();
        glassVersionDescriptions = jiraGlassDocumentation.getGlassVersionDescriptions();
        Assertions.assertEquals(versionNames.removeAll(Arrays.asList(null, "")), glassVersionNames.removeAll(Arrays.asList(null, "")));
    }
}
