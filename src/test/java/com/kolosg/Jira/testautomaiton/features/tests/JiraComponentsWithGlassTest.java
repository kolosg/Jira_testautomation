package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraGlassDocumentation;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.features.JiraProjectComponents;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.List;

public class JiraComponentsWithGlassTest {
    private JiraLogin login;
    private JiraGlassDocumentation jiraGlassDocumentation;
    private JiraProjectComponents jiraProjectComponents;

    @BeforeEach
    void setUp() throws MalformedURLException {
        login = new JiraLogin("s");
        jiraGlassDocumentation = new JiraGlassDocumentation(login.getDriver());
        jiraProjectComponents = new JiraProjectComponents(login.getDriver());
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @Test
    void validateNewProjectVersionOnGlass(){
        List<String> componentNames;
        String testComponentName = "Random component: " + Util.generateRandomNumberInRange(100);
        Util.navigateToURL(login.getDriver(), jiraProjectComponents.getJiraProjectComponentURL());
        jiraProjectComponents.addNewComponent( testComponentName);
        Util.navigateToURL(jiraProjectComponents.getDriver(), jiraGlassDocumentation.getJiraGlassDocumentationURL());
        componentNames = jiraGlassDocumentation.getGlassComponentNames();
        Util.navigateToURL(login.getDriver(), jiraGlassDocumentation.getJiraGlassDocumentationURL());
        Assertions.assertEquals(jiraProjectComponents.validateNewComponent().contains(testComponentName), componentNames.contains(testComponentName));
        jiraProjectComponents.deleteTestComponent(testComponentName);
    }
}
