package SeleniumFramework.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFramework.PageObjects.CartPage;
import SeleniumFramework.PageObjects.ProductCatalog;
import SeleniumFramework.testcomponents.BaseTest;
import SeleniumFramework.testcomponents.Retry;

public class ErrorValidationsTest extends BaseTest  {
	
	@Test(groups= {"ErrorHandling"} ,retryAnalyzer=   Retry.class)
	public void Loginerrorvalidation() throws IOException {
	
		String productname = "ZARA COAT 3";
		landingpage.LoginApplication("bbb@gmail.com", "bbb");
		Assert.assertEquals("Incorrect email or password.", landingpage.Geterrormesg());
		
			}
	@Test
	public void Producterrorvalidation() throws IOException {
	
		String productname = "ZARA COAT 3";
		ProductCatalog productcat = landingpage.LoginApplication("balukittu55@gmail.com", "Haswik@1234");
		List<WebElement> products = productcat.getProductlist();
		productcat.addProductToCart(productname);
		CartPage cartpage = productcat.GoToCartPage();
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}
}

