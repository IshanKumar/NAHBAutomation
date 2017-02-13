package com.velir;

import com.velir.baseclass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ishan.kumar on 1/13/2017.
 */
public class AccountRegistration extends SetupClass {



//        emailAddress2 = configuration.getString("emailAddress2");
//        emailAddress1 = configuration.getString("emailAddress1");



    @Test
    public void withoutPinCode(){

        helper.getURL(ENV + "/en/register.aspx");

        String userName = helper.getUUID()+"@yopmail.com";

        helper.click(By.id("contentregion_0_cbPin"));
        helper.click(By.id("contentregion_0_chkPrivacyPolicy"));

        helper.sendKeys(By.id("contentregion_0_txtUserName"), userName);

        helper.sendKeys(By.id("contentregion_0_txtPassword"), "Pass12345");

        new Select(driver.findElement(By.id("contentregion_0_ddlSecurityQuestions"))).selectByVisibleText("What city were you born in?");

        helper.sendKeys(By.id("contentregion_0_txtSecurityAnswer"), "qa");



        helper.sendKeys(By.id("contentregion_0_txtFirstName"), "FirstName");
        helper.sendKeys(By.id("contentregion_0_txtLastName"), "LastName");
        helper.sendKeys(By.id("contentregion_0_txtAddress1"), "Elm Street");

        helper.sendKeys(By.id("contentregion_0_txtCity"), "Somerville");



        new Select(driver.findElement(By.id("ddlState"))).selectByVisibleText("Alabama");

        helper.sendKeys(By.id("contentregion_0_txtZip2"), "02144");

        helper.sendKeys(By.id("contentregion_0_txtEmail"), userName);

        helper.sendKeys(By.id("contentregion_0_txtEmail2"), userName);



        helper.click(By.id("contentregion_0_btnRegister"));

        helper.waitForSeconds(4);


        Assert.assertEquals(helper.getElementText(By.cssSelector(".registration-success>p:nth-child(1)")),"You were registered successfully. Please visit the My Dashboard to view and update your information.");


    }

    @Test
    public void login() {



        helper.getURL(ENV + "/login?NoReferrer=1");

        loginRepeat("qaqa4","Pass12345");


        Assert.assertEquals(helper.getElementText(By.id("contentregion_0_errorMessage")), "The username or password you entered is invalid.");


        //loginRepeat(emailAddress2,"Pass12345");

        helper.waitForSeconds(6);

        Assert.assertEquals(helper.getElementText(By.cssSelector(".nav_btn4")), "WELCOME LARRY");

    }

    @Test
    public void forgotPassword() {

        helper.getURL(ENV + "/en/forgot-password.aspx");

        String emailAddress = "qaqa2@yopmail.com";

        helper.sendKeys(By.id("contentregion_1_txtUserName"), emailAddress);

        helper.click(By.id("contentregion_1_btnUsername"));

        helper.waitForSeconds(2);

        forgotRepeat("qaqa", "Incorrect answer");

        forgotRepeat("qa", "Your password has been successfully changed. Please return to the login page to login.");

        helper.getURL(ENV + "/login?NoReferrer=1");


        loginRepeat(emailAddress,"Pass12345");

        helper.waitForSeconds(4);

        Assert.assertEquals(helper.getElementText(By.cssSelector(".nav_btn4")), "WELCOME FIRSTNAME");



    }


    @Test
    public void toggleOnDashboard(){

        helper.getURL(ENV + "/login?NoReferrer=1");

        loginRepeat("qaqa4@yopmail.com","Pass12345");

        Assert.assertTrue(helper.isElementDisplayed(By.cssSelector(".bookmarks")));


        helper.click(By.id("contentregion_0_membercontainertop_1_bookmarks"));

        helper.waitForSeconds(14);

        Assert.assertFalse(helper.isElementDisplayed(By.cssSelector(".bookmarks")));

        helper.click(By.id("contentregion_0_membercontainertop_1_bookmarks"));
        helper.waitForSeconds(6);

        Assert.assertTrue(helper.isElementDisplayed(By.cssSelector(".bookmarks")));



    }

    @Test
    public void bookmark(){

        helper.getURL(ENV + "/login?NoReferrer=1");

        By iconLocator = By.id("headerregion_1_breadcrumbregion_0_ctl00_lbAddBookmark");

        loginRepeat("qaqa4@yopmail.com","Pass12345");

        helper.getURL(ENV + "/en/research/housing-economics/housing-indexes.aspx");

        Assert.assertTrue(helper.isElementDisplayed(iconLocator));

        helper.click(iconLocator);

        Assert.assertFalse(helper.isElementPresent(iconLocator));

        helper.getURL(ENV + "/en/member-pages/my-dashboard.aspx");

        Assert.assertEquals(helper.getElementText(By.id("contentregion_0_membercontainerright_0_rptBookmarks_hlBookmark_1")), "Housing Indexes");

        helper.click(By.id("contentregion_0_membercontainerright_0_rptBookmarks_lbDelete_1"));

        helper.waitForSeconds(14);

        Assert.assertFalse(helper.isElementPresent(By.id("contentregion_0_membercontainerright_0_rptBookmarks_hlBookmark_1")));


    }


    @Test
    public void updateProfile() {

        helper.getURL(ENV + "/login?NoReferrer=1");

        loginRepeat("qaqa4@yopmail.com","Pass12345");

        helper.waitForSeconds(6);

        Assert.assertEquals(helper.getElementText(By.cssSelector(".nav_btn4")), "WELCOME LARRY");

        helper.getURL(ENV + "/member-pages/my-profile");

        helper.waitForSeconds(4);

        updateProfileRepeat("LARRYQA");

        updateProfileRepeat("LARRY");




    }

    private void updateProfileRepeat(String userName) {
        helper.sendKeys(By.id("contentregion_0_txtFirstName"), userName);

        helper.sendKeys(By.cssSelector("#contentregion_0_txtEmail2"),"qaqa4@yopmail.com");


        helper.click(By.cssSelector("#contentregion_0_btnSave"));

        helper.waitForSeconds(6);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".nav_btn4")), "WELCOME " + userName);

    }

    private void forgotRepeat(String answer, String message) {
        helper.sendKeys(By.id("contentregion_1_txtQuestion"), answer);
        helper.sendKeys(By.id("contentregion_1_txtNewPass"), "Pass12345");
        helper.click(By.id("contentregion_1_btnQuestion"));
        helper.waitForSeconds(4);
        Assert.assertEquals(helper.getElementText(By.id("divLog")), message);
    }

    private void loginRepeat(String username, String password) {

        helper.waitForSeconds(4);

        helper.sendKeys(By.id("contentregion_0_txtUserName"), username);

        helper.sendKeys(By.id("contentregion_0_txtPassword"), password);

        helper.click(By.id("contentregion_0_btnSubmit"));
    }


}
