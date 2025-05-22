package org.example.testCases;

import io.qameta.allure.testng.AllureTestNg;
import org.example.BaseTest;
import org.example.utils.ExcelUtils;
import org.example.webPages.LoginPage;
import org.example.webPages.OwnAccountPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners({AllureTestNg.class})
public class AbLoginTest extends BaseTest {
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        return ExcelUtils.readExcelData("LoginData.xlsx", "Sheet1");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        // After login, initiate Own Account
        OwnAccountPage ownAccountPage = new OwnAccountPage(driver);
        ownAccountPage.FundIntiate();


//        if (username.equals("shovon01") && password.equals("123456@Aa")) {
//            loginPage.clickCheckbox();
//        }
//        Assert.assertTrue(loginPage.isLoggedInFailed(), "Login should have failed but didn't.");
//    }
    }
}
