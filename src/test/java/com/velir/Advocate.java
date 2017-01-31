package com.velir;

import com.velir.baseclass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ishan.kumar on 1/9/2017.
 */
public class Advocate extends SetupClass {


    @Test
    public void pageDisplay() {


        helper.getURL(ENV);


        helper.click(By.cssSelector(".blue>li:nth-child(4)>a:nth-child(2)"));
        helper.waitForSeconds(6);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "Advocate");

        List<String> actualValues = helper.getElementsText(By.cssSelector(".posts>div"));

        helper.log(actualValues);

        List<String> expectedValues= Arrays.asList("BuilderLink","A Letter from Chief Lobbyist Jim Tobin");
        Assert.assertTrue(actualValues.get(0).contains(expectedValues.get(0)));
        Assert.assertTrue(actualValues.get(1).contains(expectedValues.get(1)));

        Assert.assertEquals(helper.getElementsText(By.cssSelector(".sidebar-nav>ul>li>ul>li")).size(), 5);



        Assert.assertEquals(helper.getElementText(By.cssSelector(".col>a")), "Search all Related Topics");


        Assert.assertEquals(helper.getElementsText(By.cssSelector(".topic")).size(), 12);

        Assert.assertTrue(helper.isImagePresent(By.cssSelector(".header_slide.cycle-slide.cycle-slide-active>img")));

    }

    @Test
    public void builderLink() {


        helper.getURL(ENV + "/en/advocate.aspx");


        helper.click(By.cssSelector("a[id$=aLink_1]"));
        helper.waitForSeconds(6);
        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "BuilderLink");

        Assert.assertTrue(driver.findElement(By.xpath("//div[1]/div[1]/p[3]/a")).getAttribute("href").contains("http://www.nahbclassic.org/form.aspx?formID=2850"));


        Assert.assertEquals(helper.getElementText(By.cssSelector("strong")), "Michael Blake Bezruki");
    }



}
