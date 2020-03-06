package com.kolosg.Jira.testautomation.features;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.kolosg.Jira.testautomation.utility.Util.BASE_URL;

public class JiraBrowseIssues extends JiraFeatureBuild{
    @FindBy(id = "search-title")
    WebElement headerIssuesField;

    @FindBy(className = "issue-link-key")
    List<WebElement> issueNames;

    private final String FILTERED_SEARCH_URL = "/browse/JETI-327?filter=-4&jql=project%20in%20(JETI%2C%20COALA%2C%20TOUCAN)%20order%20by%20created%20DESC";

    public JiraBrowseIssues(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToFilteredSearchURL(){
        Util.navigateToURL(driver, BASE_URL + FILTERED_SEARCH_URL);
        waitForSEC(10);
    }

    public boolean validateExpectedNumberOfIssuesOnProject(String projectName, int expectedMinimumIssues) {
        int issuesFound = 0;
        for (WebElement issueName: issueNames) {
            if (issueName.getText().contains(projectName)) {
                issuesFound++;
            }
        }
        return issuesFound >= expectedMinimumIssues;
    }
}
