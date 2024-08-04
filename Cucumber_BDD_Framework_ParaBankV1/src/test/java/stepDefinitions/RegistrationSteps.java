package stepDefinitions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.ExcelUtils;


import static org.junit.Assert.assertEquals;

public class RegistrationSteps {

	 WebDriver driver;
     HomePage hp;
     LoginPage lp;
     AccountRegistrationPage regpage;
     MyAccountPage accpage;
     
     private String firstName;
     private String lastName;
     private String username;
     private List<HashMap<String, String>> testData;


	@Given("the user navigates to Register Account page")
	public void user_navigates_to_register_account_page() throws InterruptedException {
	
		hp=new HomePage(BaseClass.getDriver());		
    	hp.userRegistration();
    	Thread.sleep(5000);
	}

	@When("the user enters the details into below fields")
	public void user_enters_the_details_into_below_fields(DataTable dataTable) {
		
		Map<String, String> dataMap = dataTable.asMap(String.class,String.class);
		
		regpage=new AccountRegistrationPage(BaseClass.getDriver());
		
		this.firstName = dataMap.get("firstName");
        this.lastName = dataMap.get("lastName");
        this.username = dataMap.get("username");
		
		regpage.setFirstName(this.firstName);
	    regpage.setLastName(this.lastName);
		regpage.setAddress(dataMap.get("address"));
		regpage.setCity(dataMap.get("city"));
		regpage.setState(dataMap.get("state"));
		regpage.setZipCode(dataMap.get("zipcode"));
		regpage.setUserPhone(dataMap.get("phonenumber"));
		regpage.setSSN(dataMap.get("SSN"));
		regpage.setUsername(this.username);
		regpage.setPassword(dataMap.get("password"));
		regpage.confirmPassword(dataMap.get("repeatpassword"));
			
	}
	

	  
	@When("the user enters the details from the Excel file row {int}")
    public void enterDetailsFromExcel(int rowIndex) {
        // Load data from the Excel file
        String fileLocation = System.getProperty("user.dir") + "\\testData\\RegistrationDataFile.xlsx";
        String sheetName = "Sheet1";

        // Get the data from the Excel file
        testData = ExcelUtils.readExcelData(fileLocation, sheetName);

        // Convert rowIndex to zero-based index
        int index = rowIndex - 1;

        if (index >= 0 && index < testData.size()) {
            Map<String, String> data = testData.get(index);

            regpage = new AccountRegistrationPage(BaseClass.getDriver());

            this.username = data.get("username");
            regpage.setFirstName(data.get("firstName"));
            regpage.setLastName(data.get("lastName"));
            regpage.setAddress(data.get("address"));
            regpage.setCity(data.get("city"));
            regpage.setState(data.get("state"));
            regpage.setZipCode(data.get("zipcode"));
            regpage.setUserPhone(data.get("phoneNumber"));
            regpage.setSSN(data.get("SSN"));
            regpage.setUsername(this.username);
            regpage.setPassword(data.get("password"));
            regpage.confirmPassword(data.get("repeatPassword"));
        } else {
            throw new IllegalArgumentException("Row index out of range.");
        }
    }


	@And("the user clicks on register")
	public void user_clicks_resgister_button() {
		regpage.clickRegisterButton();
	}



	@Then("the user account should get created successfully")
	public void user_account_should_get_created_successfully() {
		regpage.verifySucessfulyRegistration();
		String titleText = regpage.getTitleText();
	        
	    String expectedText = "Welcome " + this.username;
	    if (titleText.equals(expectedText)) {
	    	BaseClass.getLogger().info("The title matches the username.");
	    } else {
	    	BaseClass.getLogger().info("The title does not match the username.");
	        assertEquals("The title does not match the username.", expectedText, titleText);
	    }
	
	}
	
	@Then("logout the session")
	public void user_account_should_logout_successfully() {
		
		accpage=new MyAccountPage(BaseClass.getDriver());				
		accpage.clickLogout();
		
		boolean isLoginPanelDisplayed = hp.checkLoginPanel();
        if (isLoginPanelDisplayed) {
            BaseClass.getLogger().info("Login panel is displayed as expected.");
        } else {
            BaseClass.getLogger().error("Login panel is not displayed.");
            // Fail the test
            Assert.fail("Login panel is not displayed as expected.");
        }
    }
	}


