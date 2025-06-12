package com.broadway.ecom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.broadway.ecom.pages.HomePage;
import com.broadway.ecom.pages.SignupLoginPage;

public class NegativeLoginTest extends BaseSetup {

	@Test
	public void testLoginWithInvalidPassword() {
		HomePage home = new HomePage(driver);
		SignupLoginPage loginPage = new SignupLoginPage(driver);

		// Navigate to login page
		home.clickSignupLogin();

		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[required]")));

		((JavascriptExecutor) driver)
				.executeScript("document.querySelectorAll('[required]').forEach(e => e.removeAttribute('required'));");

		// Try to login with invalid password 
		String invalidPass = "ni";
		loginPage.login(email, invalidPass);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement errorEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form//p[contains(text(),'Your email or password is incorrect!')]")));
		

		String actualErrorMsg = errorEle.getText();
		System.out.println("Login error message: " + actualErrorMsg);

		Assert.assertTrue(
				actualErrorMsg.contains("Your email or password is incorrect!")
						|| actualErrorMsg.contains("Invalid email") || actualErrorMsg.length() > 5,
				"Expected an error message for invalid password login, but got: " + actualErrorMsg);
	}
}
