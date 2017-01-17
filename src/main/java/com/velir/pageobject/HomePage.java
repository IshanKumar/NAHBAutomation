package com.velir.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by ishan.kumar on 7/24/2015.
 */
public class HomePage extends GeneralPage {

     // private WebDriver driver;
    public HomePage(WebDriver driver) {
        super(driver);
    }


    By headerLogo=By.cssSelector(".logo");


    public String getHeaderText(){

        helper.waitForSeconds(20);

        return driver.getTitle();

    }

    public void loginProcess(String username, String password) {




    }






}
