package stepDef;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class WebDriverUni {
	WebDriver driver;
	String parentWindow;
	
	public WebDriverUni() {		
		System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@Given("Launch Browser and navigate to webdriveruniversity contact page")
	public void launch_browser_and_navigate_to_webdriveruniversity_contact_page() {
		driver.get("https://webdriveruniversity.com/");
		System.out.print("Browser Lanuched Successfully with webdriveruniversity..");
	}
	

	@Given("^Enter Contact Us Form with the (.*) and (.*) then submit$")
	public void enter_contact_us_form_with_the_FirstName_and_LastName_then_submit(String firstName,String lastName) throws InterruptedException {
		System.out.println("First Name:"+firstName);
		System.out.println("Last Name:"+lastName);
		driver.findElement(By.id("contact-us")).click();
		parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String currentWindow : allWindows) {
			driver.switchTo().window(currentWindow);
		}
		driver.findElement(By.name("first_name")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("lokeshkondepudi@gmail.com");
		driver.findElement(By.tagName("textarea")).sendKeys("It's my first Selenium Script....");

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input[value='SUBMIT']")));
		Thread.sleep(3000);
		Boolean status = driver.findElement(By.xpath("//h1[contains(text(),'Thank You for your Message!')]"))
				.isDisplayed();
		assertTrue(status);
		Thread.sleep(2000);
		driver.switchTo().window(parentWindow);
		Thread.sleep(2000);
	}

	
	@Given("Enter Contact Us Form and submit")
	public void enter_contact_us_form_and_submit() throws InterruptedException {
		driver.findElement(By.id("contact-us")).click();
		parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for(String currentWindow:allWindows) {
			driver.switchTo().window(currentWindow);
		}
		driver.findElement(By.name("first_name")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys("Demo");
		driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("lokeshkondepudi@gmail.com");
		driver.findElement(By.tagName("textarea")).sendKeys("It's my first Selenium Script....");
	   
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input[value='SUBMIT']")));
		Thread.sleep(2000);
		Boolean status = driver.findElement(By.xpath("//h1[contains(text(),'Thank You for your Message!')]")).isDisplayed();
		assertTrue(status);
		Thread.sleep(2000);
		driver.switchTo().window(parentWindow);
		Thread.sleep(2000);
	} 

	@Then("Navigate to buttons window and perform actions")
	public void navigate_to_buttons_window_and_perform_actions() throws InterruptedException {
		driver.findElement(By.id("button-clicks")).click();
		Set<String> allWindows = driver.getWindowHandles();
		for(String currentWindow:allWindows) {
			driver.switchTo().window(currentWindow);
		}
		driver.findElement(By.id("button1")).click();
		Thread.sleep(2000);	
		driver.switchTo().window(parentWindow);
	}
	
	@Then("Navigate to Dropdown CheckBox Radio buttons page and perform actions")
	public void navigate_to_Dropdown_CheckBox_Radio_buttons_page_and_perform_actions() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(@id,'dropdown-checkboxes-radiobuttons')]")).click();
		Set<String> allWindows = driver.getWindowHandles();
		for(String currentWindow:allWindows) {
			driver.switchTo().window(currentWindow);
		}
		Select options1 = new Select(driver.findElement(By.cssSelector(".section-title #dropdowm-menu-1")));
		options1.selectByValue("sql");
		Thread.sleep(2000);		
		Select options2 = new Select(driver.findElement(By.cssSelector(".section-title #dropdowm-menu-2")));
		options2.selectByIndex(3);
		Thread.sleep(2000);		
		Select options3 = new Select(driver.findElement(By.cssSelector(".section-title #dropdowm-menu-3")));
		options3.selectByVisibleText("JavaScript");
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector("#checkboxes >label >input[value='option-1']")).click();
		driver.close();
	}
	
	@Then("perform operation on iframe window")
	public void perform_operation_on_iframe_window() throws InterruptedException {
		driver.switchTo().window(parentWindow);
		driver.findElement(By.id("iframe")).click();
		Set<String> allWindows = driver.getWindowHandles();
		for(String currentWindow:allWindows) {
			driver.switchTo().window(currentWindow);
		}
		Thread.sleep(2000);
		int size = driver.findElements(By.tagName("iframe")).size();
		for(int i=0;i<size;i++) {
			System.out.println("Iframe Count:"+i);
			driver.switchTo().frame(i);
			driver.findElement(By.xpath("//a[contains(text(),'Our Products')]")).click();
		}
		Thread.sleep(2000);
	}
	
	@Then("Close Browser")
	public void close_browser() {	   
	   driver.quit();
	}

}
