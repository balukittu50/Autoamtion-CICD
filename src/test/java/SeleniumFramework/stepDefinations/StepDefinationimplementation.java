package SeleniumFramework.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFramework.PageObjects.CartPage;
import SeleniumFramework.PageObjects.CheckoutPage;
import SeleniumFramework.PageObjects.ConformationPage;
import SeleniumFramework.PageObjects.LandingPage;
import SeleniumFramework.PageObjects.ProductCatalog;
import SeleniumFramework.testcomponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationimplementation extends BaseTest {
	public LandingPage landinpage;
	public ProductCatalog productcat;
	public ConformationPage ConformationPage;

	@Given("I Landed on Ecommerce Page")
	public void I_Landed_on_Ecommerce_Page() throws IOException {

		landinpage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password(.+)$")
	public void Loggedin_with_username_password(String username, String password) {
		productcat = landingpage.LoginApplication(username, password);

	}

	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productname) {
		List<WebElement> products = productcat.getProductlist();
		productcat.addProductToCart(productname);
	}

	@When("^Checkout (.+) and Submit the order$")
	public void Checkout_product_and_Submi_the_order(String productname) {
		CartPage cartpage = productcat.GoToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(productname);
		Assert.assertTrue(match);
		CheckoutPage Checkoutpage = cartpage.goToCheckout();
		Checkoutpage.selectcountry("india");

	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,500)");
		ConformationPage = Checkoutpage.Submitorder();
	}
	
	@Then("{string} message is Displed on ConformationPage")
	public void message_is_Displed_on_ConformationPage(String string) {
		String conformMesg = ConformationPage.getconformationmess();
		Assert.assertTrue(conformMesg.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is Displayed")
	public void message_is_Displayed(String string){
		Assert.assertEquals(string, landingpage.Geterrormesg());
		
		driver.close();
	}
}
