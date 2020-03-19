package com.kolosg.Jira.testautomation.features;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JiraBrowseComponents extends JiraFeatureBuild{

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

    public JiraBrowseComponents (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addNewComponent(String newComponentName) {
        waitUntilElementLoaded(componentNameInputField).sendKeys(newComponentName);
        clickOnElement(assignmentDropDownMenu);
        assignmentDropDownMenu.sendKeys("Unassigned" + Keys.ENTER);
        clickOnElement(addButton);
    }

    public boolean validateNewComponent(String newComponentName){
        return newComponentName.equalsIgnoreCase(existingComponents.get(0).getText());
    }

    public void deleteTestComponent(String newComponentName) {
        if (validateNewComponent(newComponentName)) {
            clickOnElement(actionsButtons.get(0));
            clickOnElement(deleteButtons.get(0));
            clickOnElement(confirmDeleteButton);
        }
    }

}
