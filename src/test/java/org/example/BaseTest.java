package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.ResourceBundle;


public class BaseTest {
    public static ResourceBundle bundle = ResourceBundle.getBundle("config");
    public WebDriver driver;
    public static String browserName = System.getProperty("BROWSER") != null ? System.getProperty("BROWSER").toLowerCase() : bundle.getString("BROWSER");
    public static String baseUrl = bundle.getString("BASE_URL");

    @BeforeMethod
    public void setUp() {
        System.out.println("Running on OS: " + System.getProperty("os.name"));
        if (browserName.equalsIgnoreCase("chrome")) {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(bundle.getString("IMPLICIT_WAIT"))));
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
