package com.broadway.ecom;

import com.broadway.ecom.pages.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import static org.testng.Assert.*;

public class UserTest extends BaseSetup {
    String name = "Tony zaa";
    String email = "tonyza@gmail.com";
    String password = "tonyza54321*#";
    boolean isNewUser = true;

    @Test
    public void signUp() {
        HomePage home = new HomePage(driver);
        SignupLoginPage loginPage = new SignupLoginPage(driver);
  
        home.clickSignupLogin();
        loginPage.enterSignupDetails(name, email);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        if (loginPage.isErrorMessageVisible()) {
        	isNewUser = false;
            loginPage.login(email, password);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
            assertEquals(driver.findElement(By.linkText("Logout")).getText(), "Logout");
        }
    }

    @Test(dependsOnMethods = "signUp")
    public void fillUserInfo() throws TimeoutException {
        SignupLoginPage loginPage = new SignupLoginPage(driver);

        if (!isNewUser) {
            System.out.println("Skipping fillUserInfo — user already exists.");
            return;
        }

        new WebDriverWait(driver, Duration.ofSeconds(5))
		    .until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("days")).sendKeys("10");
		driver.findElement(By.id("months")).sendKeys("July");
		driver.findElement(By.id("years")).sendKeys("2000");
		driver.findElement(By.id("first_name")).sendKeys("Ramesh");
		driver.findElement(By.id("last_name")).sendKeys("Tudu");
		driver.findElement(By.id("company")).sendKeys("Broadway Infosys");
		driver.findElement(By.id("address1")).sendKeys("Gauriganj-Jhapa, Nepal");

		Select select = new Select(driver.findElement(By.id("country")));
		select.selectByVisibleText("United States");

		driver.findElement(By.id("state")).sendKeys("Los Angeles");
		driver.findElement(By.id("city")).sendKeys("New York");
		driver.findElement(By.id("zipcode")).sendKeys("5678");
		driver.findElement(By.id("mobile_number")).sendKeys("+1 9898989898");
		driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement continueBtn = wait.until(ExpectedConditions
		    .elementToBeClickable(By.xpath("//a[@data-qa='continue-button']")));
		continueBtn.click();

		loginPage.login(email, password);
    }


    @Test(dependsOnMethods = "fillUserInfo")
    public void buyProducts() {
        ProductPage productPage = new ProductPage(driver);
        productPage.buyProducts();
    }
    
    
    @Test(dependsOnMethods = "buyProducts")
    public void makePayment() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.proceedToCheckout();
        checkout.placeOrder();
    }

    @Test(dependsOnMethods = "makePayment")
    public void fillCardInfo() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.fillCardInfo(name);
        assertTrue(checkout.isOrderConfirmed(), "Order not confirmed!");
    }

    @Test(dependsOnMethods = "makePayment")
    public void logout() throws InterruptedException {
        LogoutPage logout = new LogoutPage(driver);
        logout.logoutAndReturnHome();
    }
}
