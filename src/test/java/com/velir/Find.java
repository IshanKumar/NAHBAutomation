package com.velir;

import com.velir.baseclass.SetupClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ishan.kumar on 1/17/2017.
 */
public class Find extends SetupClass {

    @Test
    public void findExecutiveBoard(){

        helper.getURL(ENV + "/en/find/directory-executiveboard.aspx");

        helper.waitForSeconds(6);


        Assert.assertTrue(helper.getElementText(By.cssSelector(".CoveoQuerySummary>span")).contains("Results 1-10 of"));

        Assert.assertEquals(driver.findElements(By.cssSelector(".coveo-caption")).size(), 10);


        List<String> actualValues = helper.getElementsText(By.cssSelector(".CoveoSort"));

        helper.log(actualValues);

        List<String> expectedValues= Arrays.asList("Last Name","First Name");

        Assert.assertEquals(actualValues, expectedValues);

        Assert.assertEquals(driver.findElements(By.cssSelector(".CoveoPager>ul>li")).size(), 11);

        Assert.assertFalse(helper.getElementText(By.xpath(".//*[@id='box1']/div[1]/p[1]")).contains("Name: A"));
        helper.click(By.xpath(".//div[2]/div[2]/span"));
        helper.waitForSeconds(6);
        Assert.assertTrue(helper.getElementText(By.xpath(".//*[@id='box1']/div[1]/p[1]")).contains("Name: A"));

        helper.click(By.xpath(".//div[2]/ul/li[5]/label/div/span[1]"));
        helper.waitForSeconds(6);
        Assert.assertTrue(helper.getElementText(By.cssSelector(".CoveoQuerySummary>span")).contains("Results 1-5 of 5"));



    }



    @Test
    public void findLocalAssociation(){

        helper.getURL(ENV + "/en/find/directory-hba.aspx");
        helper.waitForSeconds(4);

        Assert.assertTrue(helper.getElementText(By.cssSelector(".CoveoQuerySummary>span")).contains("Results 1-10 of"));

        Assert.assertEquals(driver.findElements(By.cssSelector(".coveo-caption")).size(), 15);


        List<String> actualValues = helper.getElementsText(By.cssSelector(".CoveoSort"));
        helper.log(actualValues);
        List<String> expectedValues= Arrays.asList("Local Number");
        Assert.assertEquals(actualValues, expectedValues);


        Assert.assertEquals(driver.findElements(By.cssSelector(".CoveoPager>ul>li")).size(), 11);


        helper.click(By.xpath("//div[3]/div[2]/ul/li[5]/label/div/span[1]"));
        helper.waitForSeconds(4);
        Assert.assertTrue(helper.getElementText(By.cssSelector(".CoveoQuerySummary>span")).contains("Results 1-4 of 4"));


        Assert.assertTrue(driver.findElement(By.xpath("//div[1]/div[2]/h1")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='submitSearchCityBtn']")).isDisplayed());

        helper.click(By.cssSelector(".hide_search>a"));

        Assert.assertFalse(driver.findElement(By.xpath("//div[1]/div[2]/h1")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath(".//*[@id='submitSearchCityBtn']")).isDisplayed());


    }

    @Test
    public void staffDirectory() {

        helper.getURL(ENV + "/en/find/directory-nahbstaff.aspx");
        helper.waitForSeconds(4);

        List<String> actualValues = helper.getElementsText(By.xpath(".//*[@id='box2']/div[1]/p/strong"));
        helper.log(actualValues);
        List<String> expectedValues= Arrays.asList("Name:","Department:","Phone:");
        Assert.assertEquals(actualValues, expectedValues);


        Assert.assertFalse(helper.isElementDisplayed(By.xpath(".//*[@id='box2']/div[2]/p[4]/a")));

        helper.click(By.xpath(".//*[@id='box2']/div[1]/a/label"));

        Assert.assertTrue(helper.isElementDisplayed(By.xpath(".//*[@id='box2']/div[2]/p[4]/a")));

    }






    @Test
    public void findLink(){

        helper.getURL(ENV);

        helper.click(By.cssSelector(".blue>li:nth-child(1)>a:nth-child(2)"));
        helper.waitForSeconds(6);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "Find");

        List<String> actualValues = helper.getElementsText(By.cssSelector(".right>h1>a"));

        helper.log(actualValues);

        List<String> expectedValues= Arrays.asList("Award Programs","Committee Members","Council Members","Designees","Local Associations","Meetings and Events");

        Assert.assertEquals(actualValues,expectedValues);
    }



}
