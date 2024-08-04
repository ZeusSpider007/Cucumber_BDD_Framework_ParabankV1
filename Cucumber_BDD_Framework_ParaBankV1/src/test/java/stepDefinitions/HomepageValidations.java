package stepDefinitions;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;


public class HomepageValidations {

	   WebDriver driver;
	   HomePage hp;
	   
	   @Given("the user is on homepage")
	   public void the_user_is_on_homepage_page() {
		   hp=new HomePage(BaseClass.getDriver());
		   
		   hp.verifyHomepageUrl();

	   }

	   @Then("the homepage should display correct header links")
	   public void the_homepage_should_display_correct_header_links() {
		   hp.verifyHeaderLinks();
		   
	   }
	     
	     
	   @And("the homepage should display the parabank logo")
	   public void the_homepage_should_display_the_parabank_logo() {
		   boolean isLogoDisplayed = hp.verifyParabankLogo();
	        BaseClass.getLogger().info("Parabank Logo is displayed: " + isLogoDisplayed);
	        assertTrue("Parabank Logo is not present in the DOM.", isLogoDisplayed);
	   }  
	     
	   
	   
	   @And("the quicklinks should display and linked correctly")
	   public void the_quicklink_should_display_and_linked_correctly() {
		   
		   hp.verifyQuicklinks();
	   } 
	     
	   
	   @And("the footer panel should be displayed")
	   public void the_footer_panel_should_be_displayed() {

			boolean isFooterPanelDisplayed = hp.checkFooterPanel();
	        if (isFooterPanelDisplayed) {
	            BaseClass.getLogger().info("Footer panel is displayed as expected.");
	        } else {
	            BaseClass.getLogger().error("Footer panel is not displayed.");
	            // Fail the test
	            Assert.fail("Footer panel is not displayed as expected.");
	        }
		   
	   } 
	     
	   @And("all the footer links should be present")
	   public void the_footer_panel_links_should_be_displayed() {
		   
		   hp.verifyFooterLinks();
	   } 
	   
	
}
