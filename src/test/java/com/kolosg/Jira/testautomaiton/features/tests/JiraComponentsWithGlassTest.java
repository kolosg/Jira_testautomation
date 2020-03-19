package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraGlassDocumentation;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.features.JiraProjectComponents;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JiraComponentsWithGlassTest {
    private JiraLogin login;
    private JiraGlassDocumentation jiraGlassDocumentation;
    private JiraProjectComponents jiraProjectComponents;

    @BeforeEach
    void setUp() {
        login = new JiraLogin(Util.createDriver("Chrome"));
        jiraGlassDocumentation = new JiraGlassDocumentation(login.getDriver());
        jiraProjectComponents = new JiraProjectComponents(login.getDriver());
        Util.navigateToURL(login.getDriver(), Util.BASE_URL);
        login.loginAttempt(Util.USERNAME, Util.PASSWORD);
        login.waitForSuccessfulLogin();
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @Test
    void validateNewProjectVersionOnGlass(){
        List<String> componentNames;
        String testComponentName = "Random component: " + Util.generateRandomNumberInRange(100);
        Util.navigateToURL(login.getDriver(), Util.BASE_URL + "/projects/PP4?selectedItem=com.atlassian.jira.jira-projects-plugin:components-page");
        jiraProjectComponents.addNewComponent( testComponentName);
        Util.navigateToURL(jiraProjectComponents.getDriver(), Util.BASE_URL + "/projects/PP4?selectedItem=com.codecanvas.glass:glass");
        componentNames = jiraGlassDocumentation.getGlassComponentNames();
        Util.navigateToURL(login.getDriver(), Util.BASE_URL + "/projects/PP4?selectedItem=com.atlassian.jira.jira-projects-plugin:components-page");
        Assertions.assertEquals(jiraProjectComponents.validateNewComponent().contains(testComponentName), componentNames.contains(testComponentName));
        //jiraProjectComponents.deleteTestComponent(testComponentName);
    }
}
