package com.broadway.ecom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed To Checkout"))).click();
    }

    public void placeOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Place Order"))).click();
    }

    public void fillCardInfo(String name) {
        driver.findElement(By.xpath("//input[@data-qa='name-on-card']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@data-qa='card-number']")).sendKeys("4525785469635896");
        driver.findElement(By.xpath("//input[@data-qa='cvc']")).sendKeys("698");
        driver.findElement(By.xpath("//input[@data-qa='expiry-month']")).sendKeys("8");
        driver.findElement(By.xpath("//input[@data-qa='expiry-year']")).sendKeys("2029");

        driver.findElement(By.xpath("//button[@data-qa='pay-button']")).click();
    }

    public boolean isOrderConfirmed() {
        String text = driver.findElement(By.xpath("//div/p[text()='Congratulations! Your order has been confirmed!']")).getText();
        return text.contains("Congratulations!");
    }
}
