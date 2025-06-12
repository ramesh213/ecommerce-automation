package com.broadway.ecom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {
    private WebDriver driver;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void logoutAndReturnHome() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='/logout']")).click();
        Thread.sleep(3000); // ideally use wait
        driver.findElement(By.xpath("//a[@href='/']")).click();
    }
}
