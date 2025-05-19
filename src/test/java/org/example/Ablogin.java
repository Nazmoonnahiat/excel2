package org.example;

import org.example.utils.ExcelUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.Listeners;

@Listeners({AllureTestNg.class})
public class Ablogin {

    WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("firefox") String browser) {
        System.out.println("Running on OS: " + System.getProperty("os.name"));
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        } else {
            WebDriverManager.firefoxdriver().setup();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("geo.enabled", false);
            profile.setPreference("geo.prompt.testing", true);
            profile.setPreference("geo.prompt.testing.allow", false);
            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(profile);
            driver = new FirefoxDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://qa-abbl-customer-web.global.fintech23.xyz/sign-in");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        login(username, password);
        if (username.equals("shamsaldin") && password.equals("123456@Ff")) {
            clickCheckbox();
        } else {
            assertLoginFailed();
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        return ExcelUtils.readExcelData("LoginData.xlsx", "Sheet1");
    }

    private void login(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    private void clickCheckbox() {
        WebElement checkbox = driver.findElement(By.id("mat-mdc-checkbox-1-input"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    private void assertLoginFailed() {
        boolean errorDisplayed = driver.getPageSource().contains("Invalid")
                || driver.getCurrentUrl().contains("sign-in");
        Assert.assertTrue(errorDisplayed, "Login should have failed but didn't.");
    }
}
