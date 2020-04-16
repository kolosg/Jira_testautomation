package com.kolosg.Jira.testautomaiton.features.tests;

import com.kolosg.Jira.testautomation.features.JiraBrowseProject;
import com.kolosg.Jira.testautomation.features.JiraLogin;
import com.kolosg.Jira.testautomation.utility.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static com.kolosg.Jira.testautomation.utility.Util.BASE_URL;

public class JiraBrowseProjectsTest {

    private JiraLogin login;
    private JiraBrowseProject jiraBrowseProject;

    @BeforeEach
    void setUp() throws MalformedURLException {
        login = new JiraLogin(Util.createDriver(), "yes");
        jiraBrowseProject = new JiraBrowseProject(login.getDriver());
    }

    @AfterEach
    void tearDown() {
        login.quitDriver();
    }

    @Test
    void validateProjectsExist() {
        Util.navigateToURL(login.getDriver(), BASE_URL);
        Assertions.assertTrue(jiraBrowseProject.validateProjectsExist1());
    }

}
