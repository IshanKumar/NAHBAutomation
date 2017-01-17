package com.velir;

import com.velir.baseclass.SetupClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ishan.kumar on 7/27/2016.
 */
public class Homepage extends SetupClass {

    @Test
    public void carousel(){


        helper.getURL(ENV);



    }


    @Test
    public void dropdownFunction(){



        helper.getURL(ENV);
        helper.click(By.cssSelector(".menu_topic.btn.openAllTopics"));

        List<String> actualValues = helper.getElementsText(By.cssSelector(".all_topics>li>a"));

        helper.log(actualValues);

        List<String> expectedValues= Arrays.asList("All Topics", "Design","Economic Data","Education","Environment","Flood Insurance","Housing Finance","Multifamily","Remodeling","Safety","Workforce");

        Assert.assertEquals(actualValues,expectedValues);

        helper.click(By.cssSelector("#contentregion_3_rptTopicPopout_hlLink_6"));

        Assert.assertEquals(driver.findElements(By.linkText("#housing-finance-reform")).size(), 2);




    }


    @Test
    public void loadMore(){



        helper.getURL(ENV);


        helper.click(By.cssSelector(".menu_topic.btn.openAllTopics"));

        helper.click(By.cssSelector("#contentregion_3_rptTopicPopout_hlLink_1"));

        helper.waitForSeconds(4);

        Assert.assertEquals(driver.findElements(By.linkText("#design")).size(), 1);

        helper.waitForSeconds(4);

        helper.click(By.cssSelector(".menu_topic.btn.openAllTopics"));

        helper.click(By.cssSelector("#contentregion_3_rptTopicPopout_hlLink_0"));

        Assert.assertFalse(helper.isElementDisplayed(By.xpath(".//*[@id='more_topics']/div/div")));

        helper.click(By.linkText("Load More"));

        Assert.assertTrue(helper.isElementDisplayed(By.xpath(".//*[@id='more_topics']/div/div")));


        Assert.assertEquals(helper.getElementText(By.xpath(".//*[@id='contentregion_3_rptTopics_divRow_0']/div/div[6]/a")),"Load Less");


    }

    //incomplete
    @Test
    public void upcomingEvents(){

        helper.getURL(ENV);

        Assert.assertEquals(helper.getElementText(By.xpath(".//*[@id='upcoming_events']/div/div[1]/h1")), "Upcoming Events & Education");
        Assert.assertEquals(helper.getElementText(By.cssSelector(".title>a")),"2016 Association Leadership Institute");

        Assert.assertEquals(helper.getElementText(By.cssSelector(".event>p")),"Dynamic education on key association functions, intersecting with insightful leadership development and team collaboration");


    }

    @Test
    public void upcomingEventsLink(){


        By eventsLink = By.xpath(".//*[@id='upcoming_events']/div/div[2]/h2/a");

        helper.getURL(ENV);

        Assert.assertEquals(driver.findElement(eventsLink).getAttribute("href"),ENV+"/all-events");

        helper.click(eventsLink);

    }




}
