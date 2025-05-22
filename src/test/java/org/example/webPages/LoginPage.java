package org.example.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement continueBtn = driver.findElement(By.xpath("/html/body/app-root/layout/empty-layout/div/div/auth-sign-in/div/div/div[2]/button[2]"));
        continueBtn.click();
        // Example OTP entry
        driver.findElement(By.xpath("//span[1]//input[1]")).sendKeys("1");
        driver.findElement(By.xpath("//span[2]//input[1]")).sendKeys("1");
        driver.findElement(By.xpath("//span[3]//input[1]")).sendKeys("1");
        driver.findElement(By.xpath("//span[4]//input[1]")).sendKeys("1");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

    }

    public void clickCheckbox() {
        WebElement checkbox = driver.findElement(By.id("mat-mdc-checkbox-1-input"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }

    }

    public boolean isLoggedInFailed() {
        return driver.getPageSource().contains("Invalid") || driver.getCurrentUrl().contains("sign-in");
    }
}
