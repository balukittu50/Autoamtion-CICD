package SeleniumFramework.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFramework.PageObjects.CartPage;
import SeleniumFramework.PageObjects.CheckoutPage;
import SeleniumFramework.PageObjects.ConformationPage;
import SeleniumFramework.PageObjects.OrderPage;
import SeleniumFramework.PageObjects.ProductCatalog;
import SeleniumFramework.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productname = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitorder(HashMap<String, String> input) throws IOException {
//updated for cicd
		ProductCatalog productcat = landingpage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productcat.getProductlist();
		productcat.addProductToCart(input.get("productname"));
		CartPage cartpage = productcat.GoToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(input.get("productname"));
		Assert.assertTrue(match);
		CheckoutPage Checkoutpage = cartpage.goToCheckout();
		Checkoutpage.selectcountry("india");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		ConformationPage ConformationPage = Checkoutpage.Submitorder();
		String conformMesg = ConformationPage.getconformationmess();
		Assert.assertTrue(conformMesg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitorder" })
	public void OrderHistoryTest() {

		ProductCatalog productcat = landingpage.LoginApplication("balukittu50@gmail.com", "Haswik@123");
		OrderPage orderspage = productcat.GoToOrdersPage();
		Assert.assertTrue(orderspage.VerifyOrderDisplay(productname));
	}

	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\SeleniumFramework\\Data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	HashMap<String,String> map=new HashMap<String,String>();
//	map.put("email", "balukittu50@gmail.com");
//	map.put("password", "Haswik@123");
//	map.put("productname", "ZARA COAT 3");
//	
//	HashMap<String,String> map1=new HashMap<String,String>();
//	map1.put("email", "balukittu55@gmail.com");
//	map1.put("password", "Haswik@1234");
//	map1.put("productname", "ADIDAS ORIGINAL");

//	  @DataProvider 
//		public Object[][] getData() {
//			return new Object[][] { { "balukittu50@gmail.com", "Haswik@123", "ZARA COAT 3" },
//					{ "balukittu55@gmail.com", "Haswik@1234", "ADIDAS ORIGINAL" } };
//		}

}
