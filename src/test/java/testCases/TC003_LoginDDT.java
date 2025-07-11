package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{

	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = {"Datadriven"})
	public void verify_loginDDT(String email, String pwd, String exp) {
		logger.info("******** Starting TC003_LoginDDT ********");
	
		try {
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			logger.info("Clicked on My Account link.. ");
			homePage.clickLogin();
			logger.info("Clicked on Login link.. ");
			
			LoginPage lp = new LoginPage(driver);
			
			logger.info("Providing customer details....");
			
			lp.setEmail(email);
			lp.setPassword(pwd);	
			lp.clickLoginBtn();
			logger.info("Clicked on Login button.. ");
			
			MyAccountPage accountPage = new MyAccountPage(driver);
			boolean targetPage = accountPage.isMyAccountPageAvailable();
			logger.info("Validating my account page..");
			
			if(exp.equalsIgnoreCase("Valid")) {
				
				if(targetPage==true) {
					System.out.println("Inside if if");
					accountPage.clickLogoutBtn();
					Assert.assertTrue(true);
				}
				else {
					System.out.println("Inside if else");
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid")) {
				
				if(targetPage==true) {
					System.out.println("Inside second if if");
					accountPage.clickLogoutBtn();
					Assert.assertTrue(false);
				}
				else {
					System.out.println("Inside second if else");
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e) {
			 logger.error("Test failed due to exception: " + e.getMessage());
			Assert.assertTrue(false);
		}
		
		logger.info("******** Finished TC003_LoginDDT ********");
		
	}
}
