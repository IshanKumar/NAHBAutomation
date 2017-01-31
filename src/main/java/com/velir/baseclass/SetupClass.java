package com.velir.baseclass;

import com.velir.utilities.Helper;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;


public class SetupClass {
    protected WebDriver driver;
    public Helper helper;
    public String ENV;
    public String BROWSER;
    public String ENV_BE;
    public PropertiesConfiguration configuration ;
    protected PropertiesConfiguration expectedData;

    @BeforeMethod
    @Parameters({"browser","environment"})
    public void launchBrowser(@Optional("FF")String browser,@Optional("regression.frontend") String environment){  //to launch browser, open url and click on our consultants

            switch (browser){
                case "Chrome":
                    System.setProperty("webdriver.chrome.driver",
                            "C:\\BrowserDrivers\\chromedriver.exe");
                    driver = new ChromeDriver();
                    driver.manage().window().setSize(new Dimension(1250, 1050));
                    break;

                case "FF":
                    System.setProperty("webdriver.gecko.driver","C:\\BrowserDrivers\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;

                case "IE":
                    System.setProperty("webdriver.ie.driver",
                            "C:\\BrowserDrivers\\IEDriverServer.exe");
                    driver = new InternetExplorerDriver();

                    break;


                case "Mobile":
                    System.setProperty("webdriver.chrome.driver",
                            "C:\\BrowserDrivers\\chromedriver.exe");
                    Map<String, String> mobileEmulation = new HashMap<String, String>();
                   //mobileEmulation.put("deviceName", "Apple iPhone 6");
                    mobileEmulation.put("deviceName", "Google Nexus 6");

                    Map<String, Object> chromeOptions = new HashMap<String, Object>();
                    chromeOptions.put("mobileEmulation", mobileEmulation);
                    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                    capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                    driver = new ChromeDriver(capabilities);
                    break;


                default:
                    System.setProperty("webdriver.gecko.driver","C:\\BrowserDrivers\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
            }
        helper =new Helper(driver);
        try {
            configuration = new PropertiesConfiguration("testdata/config.properties");
            expectedData = new PropertiesConfiguration("testdata/expecteddata.properties");
            ENV = configuration.getString(environment);
            BROWSER = browser;
        }catch (Exception E){

        }
        }



//    @AfterMethod
//    public void closeBrowser(){ //to close and quit browser
//        driver.close();
//        driver.quit();
//    }
}
