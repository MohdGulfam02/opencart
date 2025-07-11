package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Regression","Master"})
	public void verify_account_registraion() {

		logger.info("******** Starting TC001_AccountRegistrationTest ********");
		try {

			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			logger.info("Clicked on My Account link.. ");
			homePage.clickRegister();
			logger.info("Clicked on Register link.. ");
			AccountRegistrationPage accountPage = new AccountRegistrationPage(driver);

			logger.info("Providing customer details....");
			accountPage.setFirstName(randomString());
			accountPage.setLastName(randomString());
			accountPage.setEmail(randomString() + "@gmail.com");
			accountPage.setTelePhone(randomNumbers());

			String password = randomAlphaNumeric();

			accountPage.setPassword(password);
			accountPage.setConfirmPassword(password);
			accountPage.clickTandCCheckbox();
			accountPage.clickContinueBtn();

			logger.info("Validating expected messages..");
			if(accountPage.getConfirmationMsg().equals("Your Account Has Been Created!")) {
				
				Assert.assertTrue(true);
			}
			else {
				logger.error("Test failed..");
				logger.debug("Debug logs..");
				Assert.assertTrue(false);
			}
		
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("******** Finished TC001_AccountRegistrationTest ********");
	}

}
