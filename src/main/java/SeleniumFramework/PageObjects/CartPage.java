package SeleniumFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkoutele;
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartprods;

	public Boolean VerifyProductDisplay(String productname) {
		Boolean match = cartprods.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		return match;
	}

	public CheckoutPage goToCheckout() {
		checkoutele.click();
		return new CheckoutPage(driver);
	}

}
