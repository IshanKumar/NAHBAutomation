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
