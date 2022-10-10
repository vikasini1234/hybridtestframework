package com.sample.testcases;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sample.pageobjectmadel.LoginPage;
import org.apache.log4j.Logger;

import java.io.IOException;

//import  com.sample.testcases.BaseClass;
//import static com.sample.testcases.BaseClass.driver;

public class TC_LoginTest_001 extends BaseClass {


    @Test
    public void loginTest() throws InterruptedException, IOException {
        driver.get(baseURL);
        logger.info("User provided"); //logger msg
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(username);
        lp.setPassword(password);
        logger.info("Provided credentials"); //logger msg
        lp.clickLogin();
        logger.info("Click on login button"); //logger msg
        logger.info("Verifying Title"); //logger msg
        if (driver.getTitle().equals("Dashboard / nopCommerce administration")) {
            Thread.sleep(5000);
            lp.clickLogout();
            Assert.assertTrue(true);
            logger.info("login Passed"); //logger msg
        } else {
            captureScreen(driver,"loginTest");
            Assert.assertTrue(false);
            logger.info("login failed"); //logger msg
        }

    }

}
