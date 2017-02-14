package com.velir.pageobject;

import com.velir.utilities.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;




public class GeneralPage  {

    public WebDriver driver;
    public Helper helper;


    // @Parameters({"browser","environment"})
    public GeneralPage(WebDriver driver) {
        this.driver = driver;
        helper =new Helper(driver);
        }



    public void loginRepeat(String username, String password) {

        helper.waitForSeconds(4);

        helper.sendKeys(By.id("contentregion_0_txtUserName"), username);

        helper.sendKeys(By.id("contentregion_0_txtPassword"), password);

        helper.click(By.id("contentregion_0_btnSubmit"));

        helper.waitForSeconds(4);

    }



}