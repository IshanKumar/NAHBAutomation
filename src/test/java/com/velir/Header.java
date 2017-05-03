package com.velir;

import com.velir.baseclass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ishan.kumar on 1/9/2017.
 */
public class Header extends SetupClass {

    @Test
    public void components(){

        helper.getURL(ENV);


        Assert.assertTrue(helper.isImagePresent(By.cssSelector(".logo>a>img")));

        Assert.assertEquals(helper.getElementText(By.cssSelector(".tagline")), "National Association of Home Builders");



        List<String> actualValues = helper.getElementsText(By.cssSelector("#UtilityNavigation>a"));

        helper.log(actualValues);

        List<String> expectedValues= Arrays.asList("About NAHB", "BuilderBooks", "HBA Resources", "How To Join", "Shop","(0)");

        Assert.assertEquals(actualValues, expectedValues);


        Assert.assertTrue(helper.isElementPresent(By.cssSelector(".search_submit")));

        Assert.assertTrue(helper.isElementPresent(By.cssSelector("#searchquery")));




    }

    @Test
    public void logo() {

        helper.getURL(ENV + "/en/learn/course-overviews.aspx");

        helper.click(By.cssSelector(".logo>a>img"));

       Assert.assertEquals(driver.getCurrentUrl(), ENV + '/');
    }

    @Test
    public void primarynavigation(){


        helper.getURL(ENV);


        helper.click(By.cssSelector(".cyan>li:nth-child(1)>a:nth-child(2)"));
        helper.waitForSeconds(6);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".col.left>div>h1")), "Login");


        helper.click(By.cssSelector(".cyan>li:nth-child(2)>a:nth-child(2)"));
        helper.waitForSeconds(6);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "For Industry Professionals");

        helper.click(By.cssSelector(".cyan>li:nth-child(3)>a:nth-child(2)"));
        helper.waitForSeconds(6);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "For Consumers");


        helper.click(By.cssSelector(".blue>li:nth-child(1)>a:nth-child(2)"));
        helper.waitForSeconds(6);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "Find");

        helper.click(By.cssSelector(".blue>li:nth-child(2)>a:nth-child(2)"));
        helper.waitForSeconds(6);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "Research");


        helper.click(By.cssSelector(".blue>li:nth-child(3)>a:nth-child(2)"));
        helper.waitForSeconds(6);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "Learn");


        helper.click(By.cssSelector(".blue>li:nth-child(4)>a:nth-child(2)"));
        helper.waitForSeconds(6);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "Advocate");


        helper.click(By.cssSelector(".blue>li:nth-child(6)>a:nth-child(2)"));
        helper.waitForSeconds(6);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".col.left>div>h1")), "Login");


        if(!BROWSER.equalsIgnoreCase("FF")) {
            helper.getURL(ENV);

            helper.waitForSeconds(2);

           // secondaryRepeater(By.cssSelector(".cyan>li:nth-child(1)>div:nth-child(3)>div:nth-child(2)>ul:nth-child(1)>li:nth-child(2)>a:nth-child(1)"));
            secondaryRepeater(By.cssSelector(".cyan>li:nth-child(1)>div:nth-child(3)>div:nth-child(2)>ul:nth-child(1)>li:nth-child(5)>a:nth-child(1)"));
            secondaryRepeater(By.cssSelector(".cyan>li:nth-child(1)>div:nth-child(3)>div:nth-child(2)>ul:nth-child(1)>li:nth-child(6)>a:nth-child(1)"));

        }

    }

    @Test
    public void tertiaryNavigation(){

        helper.getURL(ENV);

        helper.click(By.cssSelector("#UtilityNavigation>a:nth-child(1)"));
        helper.waitForSeconds(8);
        Assert.assertEquals(driver.getCurrentUrl(), ENV + "/en/about-nahb.aspx");


        helper.click(By.cssSelector("#UtilityNavigation>a:nth-child(7)"));
        helper.waitForSeconds(8);
        Assert.assertEquals(driver.getCurrentUrl(), ENV + "/en/industry-professionals/how-to-join.aspx");

        helper.click(By.cssSelector("#UtilityNavigation>a:nth-child(5)"));
        helper.waitForSeconds(8);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".col.left>div>h1")), "Login");


        helper.click(By.cssSelector("#UtilityNavigation>a:nth-child(3)"));
        helper.waitForSeconds(16);

        Assert.assertEquals(driver.getCurrentUrl(),"https://builderbooks.com/");


    }

    @Test
    public void searchFunction() {

        String searchText = "construct";
        helper.getURL(ENV );

        helper.sendKeys(By.cssSelector("#searchquery"), searchText);

        driver.findElement(By.cssSelector("#searchquery")).sendKeys(Keys.ENTER);

        helper.waitForSeconds(4);

        String actualText = helper.getElementText(By.cssSelector(".coveo-summary-section"));


        //User should be redirected to find search result page and the result text should be visible as "Results 171-180 of 950 in 0.27 second"
        Assert.assertTrue(actualText.contains("Results 1-10 of") && actualText.contains("in") && actualText.contains("second"));


        // Search results matching the keyword should be displayed
        Assert.assertTrue(helper.allElementsContain(helper.getElementsText(By.cssSelector(".item.CoveoResult>p>a")), searchText));

        //Clicking on pagination should display the records properly
        helper.click(By.cssSelector(".CoveoPager>ul>li:nth-child(2)>a"));
        helper.waitForSeconds(4);
        actualText = helper.getElementText(By.cssSelector(".coveo-summary-section"));
        Assert.assertTrue(actualText.contains("Results 11-20 of") && actualText.contains("in") && actualText.contains("second"));


        //Selecting filter option should filter the results as per selection
        helper.click(By.xpath("//div[5]/div[2]/ul/li[5]/label/div/span[1]"));
        helper.waitForSeconds(4);
        actualText = helper.getElementText(By.cssSelector(".coveo-summary-section"));
        Assert.assertTrue(actualText.contains("Results 1-3 of 3") && actualText.contains("in") && actualText.contains("second"));







    }

    private void secondaryRepeater(By clickLocator) {
        WebElement hoverElement = driver.findElement(By.cssSelector(".cyan>li:nth-child(1)>a:nth-child(2)"));

        WebElement clickElement = driver.findElement(clickLocator);


        Actions builder = new Actions(driver);
        builder.moveToElement(hoverElement).perform();
        builder.moveToElement(clickElement).click().perform();
        helper.waitForSeconds(4);

        Assert.assertEquals(helper.getElementText(By.cssSelector(".col.left>div>h1")), "Login");
    }




}
