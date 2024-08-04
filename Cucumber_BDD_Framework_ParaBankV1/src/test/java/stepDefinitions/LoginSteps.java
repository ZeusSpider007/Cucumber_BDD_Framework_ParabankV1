package stepDefinitions;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class LoginSteps {
     WebDriver driver;
     HomePage hp;
     LoginPage lp;
     MyAccountPage macc;
  
     
   
    @Given("the user clicks on the registration link on homepage")
    public void user_navigate_to_login_page() {
    	
    	BaseClass.getLogger().info("User is clicking on the Registration Link");
    	hp=new HomePage(BaseClass.getDriver());
    	
    	hp.userRegistration();
    }

 
    @When("user enters email as {string} and password as {string}")
    public void user_enters_email_as_and_password_as(String email, String pwd) {
    	BaseClass.getLogger().info("Entering email and password.. ");
    	
    	lp=new LoginPage(BaseClass.getDriver());
       	lp.setEmail(email);
        lp.setPassword(pwd);
        }

    @When("the user clicks on the Login button")
    public void click_on_login_button() {
        lp.clickLogin();
        BaseClass.getLogger().info("clicked on login button...");
    	
        
    }


    @Then("the user should be redirected to the MyAccount Page")
    public void user_navigates_to_my_account_page() {
    	macc=new MyAccountPage(BaseClass.getDriver());
		boolean targetpage=macc.isMyAccountPageExists();
				
		Assert.assertEquals(targetpage, true);
        
    }


 
}
