package com.velir;

import com.velir.baseclass.SetupClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ishan.kumar on 1/9/2017.
 */
public class Shop extends SetupClass {


    @Test
    public void productsLink() {


        helper.getURL(ENV);


        helper.click(By.cssSelector("#UtilityNavigation>a:nth-child(9)"));
        helper.waitForSeconds(6);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "Products");

        Assert.assertEquals(driver.getCurrentUrl(), ENV + "/en/Products.aspx");

        List<String> actualValues = helper.getElementsText(By.cssSelector(".title>h1"));

        helper.log(actualValues);

        List<String> expectedValues= Arrays.asList("Featured Products","New Arrivals","Product Categories");

        Assert.assertEquals(actualValues, expectedValues);




    }

    @Test
    public void categories() {


        helper.getURL(ENV + "/en/Products.aspx");




        List<String> actualValues = helper.getElementsAttribute(By.cssSelector(".category>a"),"href");

        helper.log(actualValues);

        List<String> expectedValues= Arrays.asList(ENV+"/en/Products/Education.aspx",ENV+"/en/Products/Events.aspx",ENV+"/en/Products/IBS-recordings.aspx",ENV+"/en/Products/Memberships.aspx",ENV+"/en/Products/Miscellaneous.aspx",ENV+"/en/Products/Publications.aspx",ENV+"/en/Products/Webinars.aspx",ENV+"/en/Products/Builder%20Books.aspx");

        Assert.assertEquals(actualValues, expectedValues);
    }



}
