package SeleniumFramework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class ConformationPage extends AbstractComponent {
	WebDriver driver;

	public ConformationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement  conformationmessage;
public String getconformationmess() {
	
	return conformationmessage.getText();
}
	
	
	
	
	
	
}

