package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	
	public void setFirstName(String firstname) {
		txtFirstName.sendKeys(firstname);
	}
	
	public void setLastName(String lastname) {
		txtLastName.sendKeys(lastname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelePhone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String cpwd) {
		txtConfirmPassword.sendKeys(cpwd);
	}
	
	public void clickTandCCheckbox() {
		chkdPolicy.click();
	}
	
	public void clickContinueBtn() {
		btnContinue.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return msgConfirmation.getText();
		}catch(Exception e){
			return e.getMessage();
		}
	}
}
