/**
 * 
 */
package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AccountRegistrationPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC006_EndToEndTest extends BaseClass {
	@Test(groups= {"Master"})
	public void endToendTest() throws InterruptedException
	{
	//Soft assertions
		SoftAssert myassert=new SoftAssert();
		
	//Account Registration
	System.out.println("Account Registration................");
	HomePage hp = new HomePage(driver);
	hp.clickMyAccount();
	hp.clickRegister();
	
	AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
	regpage.setFirstName(randomString().toUpperCase());
	regpage.setLastName(randomString().toUpperCase());
	
	String email=randomString() + "@gmail.com";
	regpage.setEmail(email);// randomly generated the email
			
	regpage.setTelePhone("1234567");
	regpage.setPassword("test123");
	regpage.setConfirmPassword("test123");
	regpage.clickTandCCheckbox();;
	regpage.clickContinueBtn();
	Thread.sleep(3000);

	String confmsg = regpage.getConfirmationMsg();
	System.out.println(confmsg);
	
	myassert.assertEquals(confmsg, "Your Account Has Been Created!"); //validation
	
	MyAccountPage mc=new MyAccountPage(driver);
	mc.clickLogoutBtn();;
	Thread.sleep(3000);
	
	
	//Login
	System.out.println("Login to application...............");
	hp.clickMyAccount();
	hp.clickLogin();
	
	LoginPage lp=new LoginPage(driver);
	lp.setEmail(email);
	lp.setPassword("test123");
	lp.clickLoginBtn();;
		
	
	System.out.println("Going to My Account Page? "+ mc.isMyAccountPageAvailable());
	myassert.assertEquals(mc.isMyAccountPageAvailable(), true); //validation

	
	//search & add product to cart
	System.out.println("search & add product to cart...............");
	hp.enterProductName(property.getProperty("searchProductName"));
	hp.clickSearch();
	
	SearchPage sp=new SearchPage(driver);
	System.out.println("product is "+property.getProperty("searchProductName"));
	if(sp.isProductExist(property.getProperty("searchProductName")))
	{
		sp.selectProduct(property.getProperty("searchProductName"));
		sp.setQuantity("2");
		sp.addToCart();
		
	}else {
		System.out.println("not able to find product");
	}
	Thread.sleep(3000);
	System.out.println("Added product to cart ? "+ sp.checkConfMsg());
	myassert.assertEquals(sp.checkConfMsg(),true);
		
		
	//Shopping cart
	System.out.println("Shopping cart...............");
	ShoppingCartPage sc=new ShoppingCartPage(driver);
	sc.clickItemsToNavigateToCart();
	sc.clickViewCart();
	Thread.sleep(3000);
	String totprice=sc.getTotalPrice();
	System.out.println("total price is shopping cart: "+totprice);
	myassert.assertEquals(totprice, "$246.40");   //validation
	sc.clickOnCheckout(); //navigate to checkout page
	Thread.sleep(3000);
	
	
	//Checkout Product
	System.out.println("Checkout Product...............");
	CheckoutPage ch=new CheckoutPage(driver);
	
	ch.setfirstName(randomString().toUpperCase());
	Thread.sleep(1000);
	ch.setlastName(randomString().toUpperCase());
	Thread.sleep(1000);
	ch.setaddress1("address1");
	Thread.sleep(1000);
	ch.setaddress2("address2");
	Thread.sleep(1000);
	ch.setcity("Delhi");
	Thread.sleep(1000);
	ch.setpin("500070");
	Thread.sleep(1000);
	ch.setCountry("India");
	Thread.sleep(1000);
	ch.setState("Delhi");
	Thread.sleep(1000);
	ch.clickOnContinueAfterBillingAddress();
	Thread.sleep(1000);
	ch.clickOnContinueAfterDeliveryAddress();
	Thread.sleep(1000);
	ch.setDeliveryMethodComment("testing...");
	Thread.sleep(1000);
	ch.clickOnContinueAfterDeliveryMethod();
	Thread.sleep(1000);
	ch.selectTermsAndConditions();
	Thread.sleep(1000);
	ch.clickOnContinueAfterPaymentMethod();
	Thread.sleep(2000);
	
	String total_price_inOrderPage=ch.getTotalPriceBeforeConfOrder();
	System.out.println("total price in confirmed order page:"+total_price_inOrderPage);
	myassert.assertEquals(total_price_inOrderPage, "$207.00"); //validation
	
	//Below code works only if you configure SMTP for emails 
	/*ch.clickOnConfirmOrder();
	Thread.sleep(3000);
		
	boolean orderconf=ch.isOrderPlaced();
	System.out.println("Is Order Placed? "+orderconf);
	myassert.assertEquals(ch.isOrderPlaced(),true);*/
		
	myassert.assertAll(); //conclusion
}

}


