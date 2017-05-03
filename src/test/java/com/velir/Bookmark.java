package com.velir;

import com.velir.baseclass.SetupClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ishan.kumar on 1/17/2017.
 */
public class Bookmark extends SetupClass {

    @Test
    public void bookmarking(){

        helper.getURL(ENV + "/login?NoReferrer=1");

        By iconLocator = By.id("headerregion_1_breadcrumbregion_0_ctl00_lbAddBookmark");

        loginRepeat("qaqa4@yopmail.com");

        helper.getURL(ENV + "/en/research/housing-economics/housing-indexes.aspx");

        Assert.assertTrue(helper.isElementDisplayed(iconLocator));

        helper.click(iconLocator);

        Assert.assertFalse(helper.isElementPresent(iconLocator));

        helper.getURL(ENV + "/en/member-pages/my-dashboard.aspx");


        helper.click(By.id("contentregion_0_membercontainerright_0_rptBookmarks_lbDelete_1"));

        helper.waitForSeconds(14);

        Assert.assertFalse(helper.isElementPresent(By.id("contentregion_0_membercontainerright_0_rptBookmarks_hlBookmark_1")));


    }


    @Test
    public void bookmarkSection(){

        helper.getURL(ENV + "/login?NoReferrer=1");

        By iconLocator = By.id("headerregion_1_breadcrumbregion_0_ctl00_lbAddBookmark");
        By bookmarkLink = By.id("contentregion_0_membercontainerright_0_rptBookmarks_hlBookmark_1");

        loginRepeat("qaqa4@yopmail.com");

        helper.getURL(ENV + "/en/research/housing-economics/housing-indexes.aspx");

        Assert.assertTrue(helper.isElementDisplayed(iconLocator));

        helper.click(iconLocator);

        Assert.assertFalse(helper.isElementPresent(iconLocator));

        helper.getURL(ENV + "/en/member-pages/my-dashboard.aspx");

        Assert.assertEquals(helper.getElementText(bookmarkLink), "Housing Indexes");


        Assert.assertEquals(driver.findElement(bookmarkLink).getAttribute("href"),ENV + "/en/research/housing-economics/housing-indexes.aspx");


        helper.click(By.id("contentregion_0_membercontainerright_0_rptBookmarks_lbDelete_1"));

        helper.waitForSeconds(24);

        Assert.assertFalse(helper.isElementPresent(By.id("contentregion_0_membercontainerright_0_rptBookmarks_hlBookmark_1")));


    }

    private void loginRepeat(String username) {

        helper.waitForSeconds(4);

        helper.sendKeys(By.id("contentregion_0_txtUserName"), username);

        helper.sendKeys(By.id("contentregion_0_txtPassword"), "Pass12345");

        helper.click(By.id("contentregion_0_btnSubmit"));
    }
}
