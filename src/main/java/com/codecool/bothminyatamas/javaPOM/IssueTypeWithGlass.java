package com.codecool.bothminyatamas.javaPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class IssueTypeWithGlass extends BasePOM {

    @FindBy(className = "project-config-itemlist")
    WebElement issueTypeListsFromSummary;

    @FindBy(xpath = "/html/body/aui-dropdown-menu/div/aui-section")
    WebElement issueTypeListsFromDocumentation;

    @FindBy(xpath = "/html/body/div[1]/section/div[2]/div/section/header/nav/div/div[1]/ul/li[2]/a/div")
    WebElement issueTypeDropdown;


    public IssueTypeWithGlass(WebDriverWait wait, WebDriver driver) {
        super(wait);
        PageFactory.initElements(driver, this);
    }

    public WebElement getItemListFromSummary(){
        waitUntilElementLoaded(issueTypeListsFromSummary);
        return issueTypeListsFromSummary;
    }

    public WebElement getItemListFromDocumentation(){
        waitUntilElementClickable(issueTypeDropdown);
        issueTypeDropdown.click();
        waitUntilElementLoaded(issueTypeListsFromDocumentation);
        return issueTypeListsFromDocumentation;
    }

    public String removeDuplicatedWordsFromString(String str) {
        String[] strWords = str.split("\\s+");

        //convert String array to LinkedHashSet to remove duplicates
        LinkedHashSet<String> lhSetWords
                = new LinkedHashSet<String>( Arrays.asList(strWords) );

        //join the words again by space
        StringBuilder sbTemp = new StringBuilder();
        int index = 0;

        for(String s : lhSetWords){

            if(index > 0)
                sbTemp.append(" ");

            sbTemp.append(s);
            index++;
        }

        str = sbTemp.toString();
        return str;
    }

    public String orderByAlphabeticalWordsInAString(String str){
        String[] strWords = str.split("\\s+");
        List<String> al = new ArrayList<String>();
        al = Arrays.asList(strWords);

        String temp;

        for(int i=0; i<5; i++)
        {
            for(int j=1; j<5; j++)
            {
                if(strWords[j-1].compareTo(strWords[j])>0)
                {
                    temp=strWords[j-1];
                    strWords[j-1]=strWords[j];
                    strWords[j]=temp;
                }
            }
        }
        String finalStr = "";
        for (int i=0; i<5; i++){
            finalStr = finalStr + strWords[i] + " ";
        }
        return finalStr;
    }

}
