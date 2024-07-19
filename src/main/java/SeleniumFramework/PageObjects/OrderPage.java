package SeleniumFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkoutele;
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productsNames;

	public Boolean VerifyOrderDisplay(String productname) {
		Boolean match = productsNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productname));
		return match;
	}

	public CheckoutPage goToCheckout() {
		checkoutele.click();
		return new CheckoutPage(driver);
	}

}
