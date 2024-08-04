package pageObjects;

import java.util.Arrays;
import static org.junit.Assert.assertTrue;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import factory.BaseClass;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	// Elements
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement registerAccount;
	
	@FindBy(xpath = "//h2[normalize-space()='Customer Login']")
	WebElement loginPanel;
	
	@FindBy(xpath = "//ul[@class='leftmenu']//li")
	List<WebElement> headerLinks;
	
	@FindBy(xpath = "//img[@title='ParaBank']")
	WebElement parabankLogo;
	
	@FindBy(xpath = "//ul[@class='button']/li")
	List<WebElement> quickLinksButtons;


		
	// Action Methods
	public void userRegistration() {	
		BaseClass.getLogger().info("Clicking on the Register Button on the Homepage");
		registerAccount.click();
	}
	
	public boolean checkLoginPanel() {
	    BaseClass.getLogger().info("Verifying the presence of login panel on homepage");
	    try {
	        boolean isDisplayed = loginPanel.isDisplayed();
	        BaseClass.getLogger().info("Login panel is displayed: " + isDisplayed);
	        return isDisplayed;
	        
	    } catch (NoSuchElementException e) {
	        BaseClass.getLogger().info("Login panel is not present in the DOM.");
	        return false;
	    }
	}
	
	
	public void verifyHeaderLinks() {
		
		List<WebElement> elements = headerLinks;
		List<String> presetValues = Arrays.asList("Solutions", "About Us", "Services","Products", "Locations", "Admin Page");
		
		for(WebElement element : elements) {
			
			String innerText = element.getText();
			
			if(presetValues.contains(innerText)) {
				
				BaseClass.getLogger().info(innerText +"is present in the header link");
				System.out.println(innerText + " is present in the preset values.");
            }
			
			else {
				BaseClass.getLogger().info(innerText +"is NOT present in the header link");
                System.out.println(innerText + " is not present in the preset values.");
                Assert.fail();
            }					
		}
		
	}
		
	public void verifyHomepageUrl() {
		  String expectedUrlPart = "https://parabank.parasoft.com/parabank/index.htm";
		   String currentUrl = driver.getCurrentUrl();
	       assertTrue("The URL does not contain the expected string", currentUrl.contains(expectedUrlPart));
	}

	
    public boolean verifyParabankLogo() {
    	 try {
 	        boolean isDisplayed = parabankLogo.isDisplayed();
 	        BaseClass.getLogger().info("Parabank Logo is displayed: " + isDisplayed);
 	        return isDisplayed;
 	        
 	    } catch (NoSuchElementException e) {
 	        BaseClass.getLogger().info("Parabank Logo is not present in the DOM.");
 	        return false;
 	    }
    	
    }
    
    
    public void verifyQuicklinks() {
    	
    	List<WebElement> listItems = quickLinksButtons;
    			
    	List<String> presetLinks = Arrays.asList(
                "index.htm",
                "about.htm",
                "contact.htm"
            );

        if (listItems.size() != presetLinks.size()) {
        	 BaseClass.getLogger().info("The number of <li> elements does not match the number of expected links.");
        	System.out.println("The number of <li> elements does not match the number of expected links.");
        	Assert.fail();
     
        } else {
         
            for (int i = 0; i < listItems.size(); i++) {
                WebElement item = listItems.get(i);
                WebElement link = item.findElement(By.tagName("a"));
                String href = link.getAttribute("href");
                
                if (href.equals(presetLinks.get(i))) {
                	BaseClass.getLogger().info("Hyperlink " + href + " matches expected value " + presetLinks.get(i) + ".");
                    System.out.println("Hyperlink " + href + " matches expected value " + presetLinks.get(i) + ".");
                } else {
                	BaseClass.getLogger().info("Hyperlink " + href + " does not match expected value " + presetLinks.get(i) + ".");
                    System.out.println("Hyperlink " + href + " does not match expected value " + presetLinks.get(i) + ".");
                }
            }
    }
    
	
}
}
