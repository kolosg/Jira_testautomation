package com.kolosg.Jira.testautomation.features;

import com.kolosg.Jira.testautomation.utility.Util;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class JiraProjectComponents extends JiraFeatureBuild{

    private final String jiraProjectComponentURL = Util.BASE_URL + "/projects/PP4?selectedItem=com.atlassian.jira.jira-projects-plugin:components-page";

    @FindBy(xpath = "//*[@id='components-add__component']/div[1]/input")
    private WebElement componentNameInputField;

    @FindBy(xpath = "//*[@id='assigneeType-field']")
    private WebElement assignmentDropDownMenu;

    @FindBy(xpath = "//*[@id='components-add__component']/div[5]/button")
    private WebElement addButton;

    @FindBy(xpath = "//*[@id='components-table']/tbody[2]/tr/td[@class='components-table__name']")
    private List<WebElement> existingComponents;

    @FindBy(xpath = "//*[@id='components-table']/tbody[2]/tr/td[@class='dynamic-table__actions']/div/a/span")
    private List<WebElement> actionsButtons;

    @FindBy(xpath = "//a[text()='Delete'][@class='component-delete-dialog deletecomponent_link']")
    private List<WebElement> deleteButtons;

    @FindBy(xpath = "//*[@id='submit']")
    private WebElement confirmDeleteButton;

    public JiraProjectComponents(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addNewComponent(String newComponentName) {
        waitUntilElementLoaded(componentNameInputField).sendKeys(newComponentName);
        clickOnElement(assignmentDropDownMenu);
        assignmentDropDownMenu.sendKeys("Unassigned" + Keys.ENTER);
        clickOnElement(addButton);
    }

    public List<String> validateNewComponent(){
        waitUntilElementClickable(componentNameInputField);
        return existingComponents.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void deleteTestComponent(String newComponentName) {
        int componentIndex = validateNewComponent().indexOf(newComponentName);
        clickOnElement(actionsButtons.get(componentIndex));
        clickOnElement(deleteButtons.get(existingComponents.size() - 1));
        clickOnElement(confirmDeleteButton);
    }

    public String getJiraProjectComponentURL() {
        return jiraProjectComponentURL;
    }
}
