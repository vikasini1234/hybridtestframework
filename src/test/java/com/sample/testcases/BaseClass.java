package com.sample.testcases;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.sample.utlities.ReadConfig;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Parameters;

public class BaseClass {
    ReadConfig readconfig = new ReadConfig();
    public String baseURL = readconfig.getApplicationURL();
    ;
    public String username = readconfig.getUseremail();
    public String password = readconfig.getPassword();
    public static WebDriver driver;
    public static Logger logger; //Added logger
    @Parameters("browser")
    @BeforeClass
    public void setup(String br) {
        //  System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//drivers/chromedriver.exe" );
        //   driver=new ChromeDriver();
        logger = Logger.getLogger("SampleTestProjV"); //Added logger
        PropertyConfigurator.configure("Log4j.properties");//Added logger
        if (br.equals("firefox")) {
            // Firefox Browser
            System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
            driver = new FirefoxDriver();
        } else if (br.equals("chrome")) {
            // opens the browser
            System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
            driver = new ChromeDriver();
        } else if (br.equals("edge")) {
            // opens the browser
            System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
            driver = new EdgeDriver();
        }

        // Global implicit Wait
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }



   @AfterClass
    public void tearDown()
   {
     driver.quit();
    }
    public void captureScreen(WebDriver driver, String tname) throws IOException, IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }
}
