package org.example.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OwnAccountPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public OwnAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to initiate fund transfer
    public void FundIntiate() {
        // Click on the main menu
        WebElement mainMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/app-root/layout/classy-layout/fuse-vertical-navigation/div/div[2]/fuse-vertical-navigation-collapsable-item[1]/div[1]/div/div/div/span")));
        mainMenu.click();

        // Click on the submenu item
        WebElement subMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/app-root/layout/classy-layout/fuse-vertical-navigation/div/div[2]/fuse-vertical-navigation-collapsable-item[1]/div[2]/fuse-vertical-navigation-basic-item[1]/div/a/div/div/span")));
        subMenu.click();

        // Click on the "Initiate" button
        WebElement initiateBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[3]//div[1]//button[1]")));
        initiateBtn.click();

        // Wait and enter amount
        WebElement amountField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='mat-input-4']")));
        amountField.sendKeys("500");

        // Wait and enter remarks
        WebElement remarksField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='mat-input-5']")));
        remarksField.sendKeys("mm");

        // Wait and click the "Next" or Submit button
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/app-root/layout/classy-layout/div/div[2]/app-bank-account-own-details/div/div/form/div/div[9]/div/button[2]")));
        nextButton.click();

        // Click the checkbox
        WebElement termsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='mdc-checkbox']")));
        termsCheckbox.click();

        // Click Next (if it becomes clickable)
        WebElement nextButton1 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Next']")));
        nextButton1.click();

        // Example OTP entry
        driver.findElement(By.xpath("//span[1]//input[1]")).sendKeys("1");
        driver.findElement(By.xpath("//span[2]//input[1]")).sendKeys("1");
        driver.findElement(By.xpath("//span[3]//input[1]")).sendKeys("1");
        driver.findElement(By.xpath("//span[4]//input[1]")).sendKeys("1");

        // Click Next (if it becomes clickable)
        WebElement nextButton2 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='flex space-x-2 justify-center']//button[@class='btn-primary'][normalize-space()='Next']")));
        nextButton2.click();

//        //download receipt
//        WebElement download = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//.btn-primary.ng-star-inserted")));
//        download.click();


    }
}




