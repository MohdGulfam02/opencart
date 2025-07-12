package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties property;
	
	@Parameters({"browser", "os"})
	@BeforeClass(groups = {"Sanity","Regression","Master","Datadriven"})
	public void setup(String browser, String os) throws IOException {
		
		property = new Properties();
		FileReader file = new FileReader("./src//test/resources//config.properties");
		
		property.load(file);
		System.out.println("execution_env from config file is: " + property.getProperty("execution_env"));
		logger = LogManager.getLogger(this.getClass());
		
		if(property.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("Please enter valid os");
				return;
			}
			
			if(browser.equalsIgnoreCase("chrome")) {
				capabilities.setBrowserName("chrome");
			}else if(browser.equalsIgnoreCase("edge")) {
				capabilities.setBrowserName("MicrosoftEdge");
			}else if(browser.equalsIgnoreCase("firefox")) {
				capabilities.setBrowserName("firefox");
			}
			else {
				System.out.println("Please enter valid browser name");
				return;
			}
			
			String huburl = "http://localhost:4444/wd/hub";
			
			driver = new RemoteWebDriver(new URL(huburl), capabilities);
		}
		
		
		else if(property.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (browser.toLowerCase()) {
			case "chrome":
				System.out.println("inside switch case chrome browser");
				driver = new ChromeDriver();
				break;
			case "edge":
				System.out.println("inside switch case edge browser");
				driver = new EdgeDriver();
				break;	
			default:
				logger.info("Please enter valid browser");
				System.out.println("Enter valid browser name!");
				return;
			}
		}
		
		
//		System.out.println("os passed from testing.xml file is "+os);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(property.getProperty("appurlonline"));
		driver.manage().window().maximize();
	}
	
	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}
	
	public String randomNumbers() {
		return RandomStringUtils.randomNumeric(10);
	}
	
	public String randomAlphaNumeric() {
		return RandomStringUtils.randomAlphanumeric(8);
	}
	
	@AfterClass(groups = {"Sanity","Regression","Master","Datadriven"})
	public void tearDown() {
		driver.close();
	}
	

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	
}
