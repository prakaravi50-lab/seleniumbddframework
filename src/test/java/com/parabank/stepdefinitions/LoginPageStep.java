package com.parabank.stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageStep {
	
	WebDriver driver;
	ChromeOptions option;
	@Given("to launch the parabank application url in chrome browser")
	public void to_launch_the_parabank_application_url_in_chrome_browser() {
		option = new ChromeOptions();
		option.addArguments("--headless=new");
		System.setProperty("webdriver.chrome.driver","./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://parabank.parasoft.com/parabank/index.htm");
	}

	@And("validate the login page is displaying")
	public void validate_the_login_page_is_displaying() {
	   String Expected_Login_Page_Title = "ParaBank | Welcome | Online Banking";
	   String Actual_Login_Page_Title = driver.getTitle();
	   if(Expected_Login_Page_Title.equals(Actual_Login_Page_Title))
	   {
		   System.out.println("Your in Parabank Login Page");
	   }else
	   {
		   System.out.println("Your not in Parabank Login Page");
	   }
	}

	@When("after displayed the login page enter the {string} and {string}")
	public void after_displayed_the_login_page_enter_the_username_and_password(String username, String password) {
	    driver.findElement(By.name("username")).sendKeys(username);
	    driver.findElement(By.name("password")).sendKeys(password);
	}

	@And("click the login button")
	public void click_the_login_button() {
	    driver.findElement(By.xpath("//input[@value='Log In']")).click();
	    String Expected_Home_Title = "ParaBank | Accounts Overview";
		  String Actual_Home_Title = driver.getTitle();
		  if(Expected_Home_Title.equals(Actual_Home_Title))
		  {
			  System.out.println("Login Successfull..!!");
		  }else
		  {
			  String error_Message = driver.findElement(By.xpath("//*[@id='rightPanel']/child::p")).getText();
			  System.out.println("Unable to Login Error as >>>"+error_Message);
		  }
	}

	/*
	 * @Then("validate the it is redirect to home page") public void
	 * validate_the_it_is_redirect_to_home_page() { String Expected_Home_Title =
	 * "ParaBank | Accounts Overview"; String Actual_Home_Title = driver.getTitle();
	 * if(Expected_Home_Title.equals(Actual_Home_Title)) {
	 * System.out.println("Your in Home page"); }else {
	 * System.out.println("Your in Login page"); }
	 * 
	 * }
	 */

	/*
	 * @And("ensure the page title") public void ensure_the_page_title() {
	 * 
	 * String Actual_Home_Title = driver.getTitle();
	 * System.out.println("Home Page Title is:>> "+Actual_Home_Title); }
	 */

	/*
	 * @And("logout from the home page") public void logout_from_the_home_page() {
	 * driver.findElement(By.linkText("Log Out")).click();
	 * System.out.println("Logout successfull..!!"); }
	 */
	@And("close the chrome browser")
	public void close_the_chrome_browser() {
		if(driver!=null)
		{
			 driver.quit();
		}else
		{
			System.out.println("Driver instance still in open sate"+driver);
		}
	   
	    System.out.println("Browser Closed");
	}


}
