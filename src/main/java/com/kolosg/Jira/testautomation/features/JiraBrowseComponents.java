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

    @FindBy(xpath = "//*[@id='components-table']/tbody[2]/tr")
    private List<WebElement> existingComponents;

    public JiraBrowseComponents (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /*public List<String> getComponentNames() {
        List<String> componentNamesList = new ArrayList<>();
        for (WebElement component : existingComponents){
            componentNamesList.add(waitUntilElementLoaded(component).getText());
        }
        return componentNamesList;
    }*/

    public void addNewComponent(String newComponentName) {
        waitUntilElementLoaded(componentNameInputField).sendKeys(newComponentName);
        clickOnElement(assignmentDropDownMenu);
        assignmentDropDownMenu.sendKeys("Unassigned" + Keys.ENTER);
        clickOnElement(addButton);
    }


}
