package stepDef;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginOrangeCrm {

	WebDriver driver;

	@Given("User already in singup for CRM page")
	public void user_already_in_singup_for_crm_page() {
		System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		System.out.println("user_already_in_singup_for_crm_page");
	}
	@When("User navigates to CRM Page")
	public void user_navigates_to_crm_page() {
		System.out.println("user_navigates_to_crm_page");
	}
	@When("Enter correct username and password")
	public void enter_correct_username_and_password() throws InterruptedException {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(4000);
	}
	@Then("verify home page should be displayed")
	public void verify_home_page_should_be_displayed() throws InterruptedException {		
		String lblWelcome = driver.findElement(By.xpath("//a[@id='welcome']")).getText();		
		if(lblWelcome.trim().contains("Welcome Tuan")) {
			System.out.println(lblWelcome);	
		}else {
			System.out.println("Fail:"+lblWelcome);	
		}
		
	}
	@Then("user closes the browser")
	public void user_closes_the_browser() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='welcome']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		driver.quit();
	}




}