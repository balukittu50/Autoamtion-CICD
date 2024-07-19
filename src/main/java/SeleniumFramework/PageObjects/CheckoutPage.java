package SeleniumFramework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[placeholder=\"Select Country\"]")
	WebElement Country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement SelectCountry;
	
	@FindBy(css=".action__submit")
	WebElement Submit;
	
	By results=By.cssSelector(".ta-results");
	public void selectcountry(String Countryname) {
		Actions a = new Actions(driver);
		a.sendKeys(Country, Countryname).build().perform();

		waitforElementtoAppear(By.cssSelector(".ta-results"));
		SelectCountry.click();
	}
	public ConformationPage Submitorder(){
		waitforclickable(By.cssSelector(".btnn.action__submit"));
		Submit.click();
		return new ConformationPage(driver);
	}
	
	
}
