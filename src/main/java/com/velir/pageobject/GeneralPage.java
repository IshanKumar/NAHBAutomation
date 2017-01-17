package com.velir.pageobject;

import com.velir.utilities.Helper;
import org.openqa.selenium.WebDriver;

//import org.apache.commons.lang3.StringUtils;

/**
 * Created by ishan.kumar on 2/2/2016.
 */
public class GeneralPage {

    public WebDriver driver;
    public Helper helper;
    //PropertiesConfiguration configuration;
    //protected PropertiesConfiguration expectedData;
    //public String ENV;

   // @Parameters({"browser","environment"})
    public GeneralPage(WebDriver driver) {
        this.driver = driver;
       helper =new Helper(driver);
       // setupClass =new SetupClass();
//        try {
//            configuration = new PropertiesConfiguration("testdata/config.properties");
//            expectedData = new PropertiesConfiguration("testdata/expecteddata.properties");
//            ENV = configuration.getString(environment);
//        }catch (Exception E){
//
//        }
    }

    }