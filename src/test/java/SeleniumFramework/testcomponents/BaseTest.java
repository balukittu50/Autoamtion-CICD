package SeleniumFramework.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFramework.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public  LandingPage landingpage;

	public WebDriver initializeDriver() throws IOException {
		// properties class

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")//gets local system path
				+ "//src//main//java//SeleniumFramework//Resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName =	System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
	//	String browserName = prop.getProperty("browser");
		if (browserName.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//full screenmode

		} else if (browserName.equalsIgnoreCase("firefox")) {
			// firefox
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver", "E:\\Selenium java\\UdemySelinum\\geckodriver-v0.32.2-win64.exe");
			driver=new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			// edge
			WebDriverManager.edgedriver().setup();
			//System.setProperty("webdriver.edge.driver", "E:\\Selenium java\\UdemySelinum\\edgedriver_win64\\msedgedriver.exe");
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string

		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);

		// string to HasMap Jackson DataBind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {});
				
		return data;
//{map}{map1}
		
	}
	
	
	public String getScreenShot(String testcasename,WebDriver driver) throws IOException {
		TakesScreenshot	ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public  LandingPage launchApplication() throws IOException {

		driver = initializeDriver();
		 landingpage = new LandingPage(driver);
		landingpage.goTO();
		return landingpage;

	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
		//driver.quit();
	}

}
