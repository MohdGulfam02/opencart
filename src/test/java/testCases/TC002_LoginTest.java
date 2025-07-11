package testCases;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups = {"Sanity","Master"})
	public void verify_login() {
		
		logger.info("******** Starting TC002_LoginTest ********");
		try {
			
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			logger.info("Clicked on My Account link.. ");
			homePage.clickLogin();
			logger.info("Clicked on Login link.. ");
			LoginPage lp = new LoginPage(driver);
			FileReader file = new FileReader("./src//test/resources//config.properties");
			logger.info("Providing customer details....");
			lp.setEmail(property.getProperty("email"));
			lp.setPassword(property.getProperty("password"));	
			lp.clickLoginBtn();
			logger.info("Clicked on Login button.. ");
			
			MyAccountPage accountPage = new MyAccountPage(driver);
			
			logger.info("Validating expected messages..");
			if(accountPage.isMyAccountPageAvailable()) {
				Assert.assertTrue(true);
			}else {
				logger.error("Test failed..");
				logger.debug("Debug logs..");
				Assert.assertTrue(false);
			}
				
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("******** Finished TC002_LoginTest ********");
	}
}
