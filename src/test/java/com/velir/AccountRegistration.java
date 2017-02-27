package com.velir;

import com.velir.baseclass.SetupClass;
import com.velir.pageobject.GeneralPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ishan.kumar on 1/13/2017.
 */
public class AccountRegistration extends SetupClass {



    public GeneralPage page;





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


        page = new GeneralPage(driver);

        helper.getURL(ENV + "/login?NoReferrer=1");

        page.loginRepeat("qaqa4", "Pass12345");

        Assert.assertEquals(helper.getElementText(By.id("contentregion_0_errorMessage")), "The username or password you entered is invalid.");


        page.loginRepeat(configuration.getString("emailAddress2"), "Pass12345");


        Assert.assertEquals(helper.getElementText(By.cssSelector(".nav_btn4")), "WELCOME LARRY");

    }

    @Test
    public void forgotPassword() {

        helper.getURL(ENV + "/en/forgot-password.aspx");
        page = new GeneralPage(driver);

        String emailAddress = configuration.getString("emailAddress1");

        helper.sendKeys(By.id("contentregion_1_txtUserName"), emailAddress);

        helper.click(By.id("contentregion_1_btnUsername"));

        helper.waitForSeconds(2);

        forgotRepeat("qaqa", "Incorrect answer");

        forgotRepeat("qa", "Your password has been successfully changed. Please return to the login page to login.");

        helper.getURL(ENV + "/login?NoReferrer=1");


        page.loginRepeat(emailAddress, "Pass12345");


        Assert.assertEquals(helper.getElementText(By.cssSelector(".nav_btn4")), "WELCOME FIRSTNAME");


    }


    @Test
    public void toggleOnDashboard(){

        helper.getURL(ENV + "/login?NoReferrer=1");
        page = new GeneralPage(driver);

        page.loginRepeat(configuration.getString("emailAddress2"), "Pass12345");

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
        page = new GeneralPage(driver);

        By iconLocator = By.id("headerregion_1_breadcrumbregion_0_ctl00_lbAddBookmark");

        page.loginRepeat(configuration.getString("emailAddress2"), "Pass12345");

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

        page = new GeneralPage(driver);

        page.loginRepeat("qaqa4@yopmail.com", "Pass12345");

        helper.waitForSeconds(6);

        Assert.assertEquals(helper.getElementText(By.cssSelector(".nav_btn4")), "WELCOME LARRY");

        helper.getURL(ENV + "/member-pages/my-profile");

        helper.waitForSeconds(4);

        updateProfileRepeat("LARRYQA");

        updateProfileRepeat("LARRY");



    }


    @Test
    public void dashboardContent() {

        helper.getURL(ENV + "/login?NoReferrer=1");
        page = new GeneralPage(driver);

        page.loginRepeat(configuration.getString("emailAddress2"), "Pass12345");
        helper.waitForSeconds(3);



        Assert.assertEquals(driver.getCurrentUrl(), ENV + "/member-pages/my-dashboard");

        Assert.assertEquals(helper.getElementText(By.cssSelector(".right>h1")), "LARRY Roderick");


        Assert.assertTrue(helper.isImagePresent(By.cssSelector("#contentregion_0_membercontainertop_0_imgUser")));


        List<String> actualValues = helper.getElementsText(By.cssSelector(".right>p"));

        helper.log(actualValues);

        List<String> expectedValues= Arrays.asList("BRYAN, TX", "qaqa4@yopmail.com", "NAHB PIN: 1548675", "Spike Credits: 0.00");

        Assert.assertEquals(actualValues, expectedValues);


       Assert.assertEquals(driver.findElements(By.cssSelector(".item.CoveoResult>p>a")).size(), 3);





    }


    //default is all 15 selected
    @Test
    public void interestsEditProfile() {

        By checkboxToClick = By.cssSelector("#contentregion_2_ctl02_rptInterests_cbOption_28");

        By checkboxToView = By.cssSelector("#contentregion_2_ctl02_rptInterests_cbOption_30");
        By saveChanges =By.cssSelector("#contentregion_2_ctl02_btnSave");

        helper.getURL(ENV + "/login?NoReferrer=1");
        page = new GeneralPage(driver);
        page.loginRepeat(configuration.getString("emailAddress2"), "Pass12345");


        helper.getURL(ENV + "/en/member-pages/my-profile.aspx");
        helper.waitForSeconds(3);

        helper.click(By.cssSelector(".tab3.tab"));

        if(!driver.findElement(checkboxToClick).isSelected()){

            helper.click(checkboxToClick);
            helper.click(saveChanges);
        }

        Assert.assertTrue(driver.findElement(checkboxToClick).isSelected());
        Assert.assertFalse(driver.findElement(checkboxToView).isEnabled());


        helper.click(checkboxToClick);
        helper.click(saveChanges);


        Assert.assertFalse(driver.findElement(checkboxToClick).isSelected());
        Assert.assertTrue(driver.findElement(checkboxToView).isEnabled());
        Assert.assertTrue(driver.findElement(checkboxToClick).isEnabled());



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



}
