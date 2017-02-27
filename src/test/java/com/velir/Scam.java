package com.velir;

import com.velir.baseclass.SetupClass;
import com.velir.pageobject.GeneralPage;
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


    public GeneralPage page;

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
        page = new GeneralPage(driver);
        page.loginRepeat(configuration.getString("emailAddress2"), "Pass12345");


        helper.getURL(ENV + "/en/find/Student Chapter Alumni Member.aspx");


        Assert.assertEquals(driver.findElements(By.cssSelector(".item.CoveoResult")).size(), 4);

        helper.sendKeys(By.cssSelector("#zip"), "10001");

        Select dropdown = new Select(driver.findElement(By.id("selectedDistance")));
        dropdown.selectByIndex(1);
        helper.waitForSeconds(2);

        helper.click(By.cssSelector("#submitSearchZipBtn"));

        helper.waitForSeconds(4);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".CoveoQuerySummary>span")), "Result 1 of 1");
        Assert.assertEquals(driver.findElements(By.cssSelector(".item.CoveoResult")).size(), 1);


    }


    @Test
    public void nahbLogout() {


        helper.getURL(ENV + "/en/find/Student Chapter Alumni Member.aspx");


        helper.sendKeys(By.cssSelector("#zip"), "9810");

        helper.waitForSeconds(2);

        helper.click(By.cssSelector("#submitSearchZipBtn"));

        helper.waitForSeconds(4);

        helper.click(By.partialLinkText("Purchase Student Chapter Alumni Membership"));

        GeneralPage page = new GeneralPage(driver);

        page.loginRepeat(configuration.getString("emailAddress1"), "Pass12345");

        helper.waitForSeconds(2);

        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "Verify and Update Your Profile");

        Assert.assertEquals(helper.getElementText(By.cssSelector("#contentregion_0_btnSave")),"Update & Continue");



    }

    @Test
    public void nahbLoggedin() {


        helper.getURL(ENV + "/login?NoReferrer=1");

        GeneralPage page = new GeneralPage(driver);

        page.loginRepeat(configuration.getString("emailAddress1"), "Pass12345");


        helper.getURL(ENV + "/en/find/Student Chapter Alumni Member.aspx");


        helper.sendKeys(By.cssSelector("#zip"), "9810");

        helper.waitForSeconds(2);

        helper.click(By.cssSelector("#submitSearchZipBtn"));

        helper.waitForSeconds(4);

        helper.click(By.partialLinkText("Purchase Student Chapter Alumni Membership"));



        helper.waitForSeconds(2);

        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "Verify and Update Your Profile");

        Assert.assertEquals(helper.getElementText(By.cssSelector("#contentregion_0_btnSave")),"Update & Continue");



    }


    @Test
    public void notEligibletoNAHB() {

        helper.getURL(ENV + "/login?NoReferrer=1");
        page = new GeneralPage(driver);
        page.loginRepeat(configuration.getString("emailAddress2"), "Pass12345");


        helper.getURL(ENV + "/en/find/Student Chapter Alumni Member.aspx");
        helper.sendKeys(By.cssSelector("#zip"), "10001");
        helper.click(By.cssSelector("#submitSearchZipBtn"));

        helper.waitForSeconds(4);
        helper.click(By.partialLinkText("Purchase Student Chapter Alumni Membership"));
        helper.waitForSeconds(2);

        helper.sendKeys(By.id("contentregion_0_txtEmail2"), configuration.getString("emailAddress2"));
        helper.click(By.id("contentregion_0_btnSave"));
        helper.waitForSeconds(4);

        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "You Might Be Eligible to Join Local Associations");
        Assert.assertEquals(driver.getCurrentUrl(),ENV+"/en/scam_not_eligible.aspx");
        Assert.assertEquals(helper.getElementText(By.cssSelector("#header-cart-count")),"0");


    }






}
