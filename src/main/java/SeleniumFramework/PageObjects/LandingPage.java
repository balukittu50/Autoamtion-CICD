package SeleniumFramework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;

	//PageFactory
	
	public LandingPage(WebDriver driver) {
	
		super(driver);
		
		//initializing
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}

	//WebElement userid = driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userid;
	
	
	@FindBy(id="userPassword")
	WebElement Passwordelm;
	
	@FindBy(id="login")
	WebElement Submit;
	@FindBy(css="[class*='flyInOut']")
	WebElement ErrorMessage;
	
	public String Geterrormesg() {
		waitforwebElementtoAppear(ErrorMessage);
		return ErrorMessage.getText();
		
	}
	public ProductCatalog LoginApplication(String email,String password)
	{
		userid.sendKeys(email);
		Passwordelm.sendKeys(password);
		Submit.click();
		ProductCatalog productcat = new ProductCatalog(driver);
		return productcat;
		
	}
	
	public void goTO() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}

















