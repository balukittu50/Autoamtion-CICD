package SeleniumFramework.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeleniumFramework.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class E2E {
	public static void main(String[] args) {

		//new comments updated for git jenkin
		String productname = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		
		LandingPage landingpage=new LandingPage(driver);
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.id("userEmail")).sendKeys("balukittu50@gmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("Haswik@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartprods = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartprods.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));

		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder=\"Select Country\"]")), "india").build().perform();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btnn.action__submit")));

		driver.findElement(By.cssSelector(".btnn.action__submit")).click();
		//driver.findElement(By.cssSelector(".action__submit")).click();
		String conformMesg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(conformMesg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
