package com.kolosg.Jira.testautomation.features;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class JiraFeatureBuild {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final int TIMEOUT_FOR_LOADING = Integer.parseInt(Util.getEnvironmentVariable("timeout_limit"));

    protected JiraFeatureBuild(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT_FOR_LOADING);
    }


}
