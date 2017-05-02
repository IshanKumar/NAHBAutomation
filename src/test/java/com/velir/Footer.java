package com.velir;

import com.velir.baseclass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ishan.kumar on 1/5/2017.
 */
public class Footer extends SetupClass {

    @Test
    public void components(){


        helper.getURL(ENV);



        List<String> expectedAddress = Arrays.asList("1201 15th Street NW\n" +
                "Washington, DC 20005\n" +
                "Toll free: 1-800-368-5242", "© 2015. All rights reserved.");

        List<String> expectedLinks= Arrays.asList("Terms of use", "Privacy");

        List<String> expectedHeaderLinks= Arrays.asList("Find", "Research","Learn","Advocate","More");

        List<String> expectedSocials= Arrays.asList("https://www.linkedin.com/company/15288", "http://facebook.com/NAHBhome","https://twitter.com/NAHBhome","http://www.houzz.com/pro/nahb/national-association-of-home-builders","http://pinterest.com/NAHBHome");

        // Company name and contact details

        Assert.assertEquals(helper.getElementText(By.cssSelector(".left.contact>h3")), "National Association of Home Builders");

        //Copyright text

        Assert.assertEquals(helper.getElementsText(By.cssSelector(".left.contact>p")), expectedAddress);

        //Terms and Policy links

        Assert.assertEquals(helper.getElementsText(By.cssSelector("#footer-utility-navigation>p>a")), expectedLinks);


        //Share icons

        Assert.assertEquals(helper.getElementsAttribute(By.cssSelector(".nahb-footer-social"),"href"),expectedSocials);


        //Find, Research, Learn, Advocate and More header with links

        Assert.assertEquals(helper.getElementsText(By.cssSelector(".col>h2")), expectedHeaderLinks);

        Assert.assertEquals(helper.getElementsText(By.cssSelector(".col>ul>li>a")).size(), 30);

        //Language selector drop down

        Assert.assertEquals(helper.getElementText(By.cssSelector(".goog-te-menu-value>span:nth-child(1)")), "Select Language");


    }

    @Test
    public void shareOptions(){


        helper.getURL(ENV);


        List<String> expectedSocials= Arrays.asList("https://www.linkedin.com/company/15288", "http://facebook.com/NAHBhome","https://twitter.com/NAHBhome","http://www.houzz.com/pro/nahb/national-association-of-home-builders","http://pinterest.com/NAHBHome");


        for(WebElement we: driver.findElements(By.cssSelector(".nahb-footer-social"))){
            we.click();
            helper.waitForSeconds(2);
        }

        //Share icons

        Assert.assertEquals(helper.getElementsAttribute(By.cssSelector(".nahb-footer-social"), "href"), expectedSocials);


    }


    @Test
    public void linkFunction() {


        helper.getURL(ENV);



        //FIND
        List<String> actualValues = helper.getElementsAttribute(By.xpath(".//*[@id='footer-sitemap-navigation']/div[1]/ul/li/a"), "href");

        helper.log(actualValues);

        List<String> expectedValuesFind= Arrays.asList(ENV+"/en/find/directory-executiveofficers.aspx", ENV+"/en/find/directory-hba.aspx",ENV+"/en/members/committees-and-councils.aspx", ENV+"/en/find/award-programs.aspx", ENV+"/en/about-nahb/social-and-media/online-advertising-opportunities-to-reach-builders.aspx");

        Assert.assertEquals(actualValues, expectedValuesFind);


        //RESEARCH

        actualValues = helper.getElementsAttribute(By.xpath(".//*[@id='footer-sitemap-navigation']/div[2]/ul/li/a"), "href");

        helper.log(actualValues);

        List<String> expectedValuesResearch= Arrays.asList(ENV+"/en/research/nahb-priorities.aspx", ENV+"/en/research/housing-economics.aspx", ENV+"/en/research/legal-issues.aspx", ENV+"/en/members/biz-tools.aspx", ENV+"/en/research/design.aspx");

        Assert.assertEquals(actualValues, expectedValuesResearch);


        //LEARN
        actualValues = helper.getElementsAttribute(By.xpath(".//*[@id='footer-sitemap-navigation']/div[3]/ul/li/a"), "href");

        helper.log(actualValues);

        List<String> expectedValuesLearn= Arrays.asList(ENV+"/en/learn/designations.aspx", ENV+"/en/learn/course-overviews.aspx", ENV+"/en/learn/education-calendar.aspx", ENV+"/en/learn/education-articles/apply-online-to-become-an-nahb-approved-instructor.aspx");

        Assert.assertEquals(actualValues, expectedValuesLearn);


        //ADVOCATE
        actualValues = helper.getElementsAttribute(By.xpath(".//*[@id='footer-sitemap-navigation']/div[4]/ul/li/a"), "href");

        helper.log(actualValues);

        List<String> expectedValuesAdvocate= Arrays.asList(ENV+"/en/advocate/builderlink.aspx",ENV+"/en/advocate/build-pac.aspx", ENV+"/en/advocate/partners-for-housing.aspx", ENV+"/en/advocate/policy-resolutions.aspx");

        Assert.assertEquals(actualValues, expectedValuesAdvocate);


        //PRIVACY

        actualValues = helper.getElementsAttribute(By.xpath(".//*[@id='footer-utility-navigation']/p/a"), "href");

        helper.log(actualValues);

        List<String> expectedValuesPrivacy= Arrays.asList(ENV+"/en/Terms-of-Service.aspx", ENV+"/en/privacy-policy.aspx");
        Assert.assertEquals(actualValues, expectedValuesPrivacy);



    }


    @Test
    public void thirdPartyLink() {


        helper.getURL(ENV);


        helper.waitForSeconds(4);

        //MORE
        List<String>  actualValues = helper.getElementsAttribute(By.xpath(".//*[@id='footer-sitemap-navigation']/div[5]/ul/li/a"), "href");

        helper.log(actualValues);

        List<String> expectedValuesMore= Arrays.asList("http://regression.cd.nahb.velir.com/en/members/hba-resources.aspx", "http://buildersshow.com/Home/", "http://www.nationalhousingendowment.org/", "http://www.hbi.org/","http://www.homeinnovation.com/",ENV+"/en/industry-professionals/international-housing-association.aspx","http://www.bestinamericanliving.com/","http://www.nahbclassic.org/showpage_details.aspx?showpageID=311?userid={0}");

        Assert.assertEquals(actualValues, expectedValuesMore); }






    }
