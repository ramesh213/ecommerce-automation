package com.broadway.ecom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class AccountCreationPage {
    private WebDriver driver;

    public AccountCreationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillUserInfo(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("days")).sendKeys("10");
        driver.findElement(By.id("months")).sendKeys("July");
        driver.findElement(By.id("years")).sendKeys("2000");

        driver.findElement(By.id("first_name")).sendKeys("Ramesh");
        driver.findElement(By.id("last_name")).sendKeys("Tudu");
        driver.findElement(By.id("company")).sendKeys("Broadway Infosys");
        driver.findElement(By.id("address1")).sendKeys("Gauriganj-Jhapa, Nepal");

        new Select(driver.findElement(By.id("country"))).selectByVisibleText("United States");

        driver.findElement(By.id("state")).sendKeys("Los Angeles");
        driver.findElement(By.id("city")).sendKeys("New York");
        driver.findElement(By.id("zipcode")).sendKeys("5678");
        driver.findElement(By.id("mobile_number")).sendKeys("+1 9898989898");

        driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();
    }

    public void clickContinue() {
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
    }
}
