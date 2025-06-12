package com.broadway.ecom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSetup {

    protected static WebDriver driver; // static to be shared across the suite
    protected String url = "https://automationexercise.com/";

    @BeforeSuite
    public void setUpSuite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(url);
        System.out.println("Browser launched and navigated to: " + url);

    }

//    @AfterSuite
//    public void tearDownSuite() {
//        if (driver != null) {
//            driver.quit();
//            System.out.println("Browser closed after suite.");
//        }
//    }
//
//    public WebDriver getDriver() {
//        return driver;
//    }
}
