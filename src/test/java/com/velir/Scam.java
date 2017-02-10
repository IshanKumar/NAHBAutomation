package com.velir;

import com.velir.baseclass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ishan.kumar on 1/9/2017.
 */
public class Scam extends SetupClass {


    @Test
    public void displayLogout() {


        helper.getURL(ENV + "/en/find/Student Chapter Alumni Member.aspx");


        Assert.assertEquals(driver.findElements(By.cssSelector(".item.CoveoResult")).size(), 4);

        helper.sendKeys(By.cssSelector("#zip"), "10001");

        Select dropdown = new Select(driver.findElement(By.id("selectedDistance")));
        dropdown.selectByIndex(1);
        helper.waitForSeconds(2);

        helper.click(By.cssSelector("#submitSearchZipBtn"));

        Assert.assertEquals(helper.getElementText(By.cssSelector(".CoveoQuerySummary>span")), "Result 1 of 1");


        Assert.assertEquals(driver.findElements(By.cssSelector(".item.CoveoResult")).size(), 1);






    }

    @Test
    public void displayLogin() {


        helper.getURL(ENV + "/login?NoReferrer=1");

        helper.waitForSeconds(4);

        helper.sendKeys(By.id("contentregion_0_txtUserName"), "qaqa4@yopmail.com");

        helper.sendKeys(By.id("contentregion_0_txtPassword"), "Pass12345");

        helper.click(By.id("contentregion_0_btnSubmit"));

        helper.waitForSeconds(4);


        helper.getURL(ENV + "/en/find/Student Chapter Alumni Member.aspx");


        Assert.assertEquals(driver.findElements(By.cssSelector(".item.CoveoResult")).size(), 4);

        helper.sendKeys(By.cssSelector("#zip"), "10001");

        Select dropdown = new Select(driver.findElement(By.id("selectedDistance")));
        dropdown.selectByIndex(1);
        helper.waitForSeconds(2);

        helper.click(By.cssSelector("#submitSearchZipBtn"));

        Assert.assertEquals(helper.getElementText(By.cssSelector(".CoveoQuerySummary>span")), "Result 1 of 1");
        Assert.assertEquals(driver.findElements(By.cssSelector(".item.CoveoResult")).size(), 1);


    }




}
