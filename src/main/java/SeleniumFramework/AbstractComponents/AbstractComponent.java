package SeleniumFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFramework.PageObjects.CartPage;
import SeleniumFramework.PageObjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement CartHeader;

	@FindBy(css = ".btnn.action__submit")
	WebElement butnsubmit;
	
	@FindBy(css = "[routerlink*='myorder']")
	WebElement orderHeader;

	// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	public void waitforElementtoAppear(By findby) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}

	public void waitforwebElementtoAppear(WebElement findby) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}

	public void waitforElementtoDisappear(WebElement Ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(Ele));
	}

	public void waitforclickable(By clickable) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(clickable));

	}

	public CartPage GoToCartPage() {
		CartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	public OrderPage GoToOrdersPage() {
		orderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
}
