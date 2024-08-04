package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import factory.BaseClass;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	// Elements
		@FindBy(xpath = "//input[@id='customer.firstName']")
		WebElement txtFirstname;

		@FindBy(xpath = "//input[@id='customer.lastName']")
		WebElement txtLasttname;

		@FindBy(xpath = "//input[@id='customer.address.street']")
		WebElement userAddress;

		@FindBy(xpath = "//input[@id='customer.address.city']")
		WebElement userCity;

		@FindBy(xpath = "//input[@id='customer.address.state']")
		WebElement userState;

		@FindBy(xpath = "//input[@id='customer.address.zipCode']")
		WebElement zipCode;

		@FindBy(xpath = "//input[@id='customer.phoneNumber']")
		WebElement userPhone;

		@FindBy(xpath = "//input[@id='customer.ssn']")
		WebElement userSSN;

		@FindBy(xpath = "//input[@id='customer.username']")
		WebElement userName;
		
		@FindBy(xpath = "//input[@id='customer.password']")
		WebElement userPassword;
		
		@FindBy(xpath = "//input[@id='repeatedPassword']")
		WebElement confirmPassword;
			
		@FindBy(xpath = "//input[@value='Register']")
		WebElement clickRegister;
		
		@FindBy(xpath = "//p[contains(text(),'Your account was created successfully. You are now')]")
		WebElement accounntCreationSucesstxt;
		
		@FindBy(css = ".title")
		WebElement welcomeTxt;
		
		
		//action methods
		

		public void setFirstName(String fname) {
			txtFirstname.sendKeys(fname);

		}

		public void setLastName(String lname) {
			txtLasttname.sendKeys(lname);

		}

		public void setAddress(String address) {
			userAddress.sendKeys(address);

		}

		public void setCity(String city) {
			userCity.sendKeys(city);

		}

		public void setState(String state) {
			userState.sendKeys(state);

		}

		public void setZipCode(String zipcode) {
			zipCode.sendKeys(zipcode);

		}

		public void setUserPhone(String phonenumber) {
		    if (phonenumber.matches("\\d+")) { // Check if the string contains only digits
		        userPhone.sendKeys(phonenumber);
		    } else {
		        throw new IllegalArgumentException("Phone number must be numeric");
		    }
		}

		public void setSSN(String SSN) {
			userSSN.sendKeys(SSN);

		}
		
		public void setUsername(String username) {
			userName.sendKeys(username);

		}

		
		public void setPassword(String password) {
			userPassword.sendKeys(password);

		}
		
		public void confirmPassword(String repeatpassword) {
			confirmPassword.sendKeys(repeatpassword);

		}
		
		
		public void clickRegisterButton() {
			clickRegister.click();

		}
		
		public void verifySucessfulyRegistration() {
			
			   try {
		            // Check if the element is displayed
		            if (accounntCreationSucesstxt.isDisplayed()) {
		                // Element is present and visible, test passes
		            	BaseClass.getLogger().info("Account creation success message is displayed.");
		            } else {
		                // Element is not visible, test fails
		            	BaseClass.getLogger().info("Account creation success message is not visible.");
		                Assert.fail("Account creation success message is not visible.");
		            }
		        } catch (NoSuchElementException e) {
		            // Element is not present, test fails
		            Assert.fail("Account creation success message is not present.");
		        }

		}
		
		
		 public String getTitleText() {
		        WebElement titleElement =welcomeTxt;
		        return titleElement.getText();
		    }

		
}