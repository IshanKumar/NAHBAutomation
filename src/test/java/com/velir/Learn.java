package com.velir;

import com.velir.baseclass.SetupClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ishan.kumar on 1/17/2017.
 */
public class Learn extends SetupClass {

    @Test
    public void courseOverview(){

        helper.getURL(ENV+"/en/learn.aspx");

        Assert.assertEquals(helper.getElementText(By.cssSelector("#contentregion_0_fullwidthcontent_1_hlFull")), "Full Course Overview");

        helper.click(By.cssSelector("#contentregion_0_fullwidthcontent_1_hlFull"));

        helper.waitForSeconds(6);

        Assert.assertEquals(helper.getElementText(By.cssSelector(".head>h1")), "Effective Marketing on a Shoestring Budget Course");

        Assert.assertTrue(helper.getElementText(By.xpath(".//*[@id='Description']/div[1]/div/ul/li[4]/p[1]")).contains("The online course Effective Marketing on a Shoestring Budget course provides planning tools that work in anyone's budget."));

    }
}
