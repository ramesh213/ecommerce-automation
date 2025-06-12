package com.broadway.ecom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupLoginPage {
    private WebDriver driver;

    private By signupName = By.xpath("//input[@data-qa='signup-name']");
    private By signupEmail = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");
    private By loginEmail = By.xpath("//input[@data-qa='login-email']");
    private By loginPassword = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    private By errorMsg = By.xpath("//p[contains(text(),'Email Address already exist!')]");

    public SignupLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSignupDetails(String name, String email) {
        driver.findElement(signupName).sendKeys(name);
        driver.findElement(signupEmail).sendKeys(email);
        driver.findElement(signupButton).click();
    }

    public void login(String email, String password) {
        driver.findElement(loginEmail).sendKeys(email);
        driver.findElement(loginPassword).sendKeys(password);
        driver.findElement(loginButton).click();
    }
    
    public String getErrorMessage() {
    	return driver.findElement(errorMsg).getText();
    }

    public boolean isErrorMessageVisible() {
        return driver.findElements(errorMsg).size() > 0;
    }
}
