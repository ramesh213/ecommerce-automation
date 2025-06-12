package com.broadway.ecom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void buyProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(By.className("card_travel"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-parent='#accordian']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-product-id='3']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View Cart"))).click();
    }
}
