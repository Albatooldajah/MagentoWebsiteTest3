package magentoWebsiteProjectTest;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {
	//test Gear Section
	WebDriver driver = new ChromeDriver();
	String myWebsite = "https://magento.softwaretestingboard.com/"; // variable
	Random Rand = new Random();
	String password = "MyPass_5443";
	String logoutLink = "https://magento.softwaretestingboard.com/customer/account/logout/";
	String emailforSignUp = "";

	@BeforeTest
	public void mySetUp() {
		driver.manage().window().maximize();
		// put the website in variable
		driver.get(myWebsite);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	@Test(priority = 1, enabled = false)
	public void createAnAccount() {
		WebElement CreateAccount = driver.findElement(By.linkText("Create an Account"));
		CreateAccount.click();
		String[] firstNames = { "Alice", "Bob", "Charlie", "David", "Eva" };
		String[] lastNames = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis" };
		int randonIndexForFirstName = Rand.nextInt(firstNames.length); // inside nextint called bound
		int randonIndexForLastName = Rand.nextInt(lastNames.length); // uses .length becuse it is static array

		WebElement firstNameInput = driver.findElement(By.id("firstname"));
		WebElement lastNameInput = driver.findElement(By.id("lastname"));
		WebElement emailAddressInput = driver.findElement(By.id("email_address"));
		WebElement passInput = driver.findElement(By.id("password"));
		WebElement passComfirmInput = driver.findElement(By.id("password-confirmation"));
		WebElement signUpButton = driver.findElement(By.cssSelector(".action.submit.primary"));

		String firstName = firstNames[randonIndexForFirstName];
		String lastName = lastNames[randonIndexForFirstName];
		String domian = "@gmail.com";
		int randomNamber = Rand.nextInt(10000);

		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		emailAddressInput.sendKeys(firstName + lastName + randomNamber + domian);
		passInput.sendKeys(password);
		passComfirmInput.sendKeys(password);
		signUpButton.click();
		emailforSignUp = firstName + lastName + randomNamber + domian;
		WebElement MassegeAsWebElement = driver.findElement(By.className("messages"));
		String TheActualMassage = MassegeAsWebElement.getText();
		String TheExptedMassage = "Thank you for registering with Main Website Store.";
		Assert.assertEquals(TheActualMassage, TheExptedMassage); // to check the test is pass or not , we can't use if
																	// statement becuase the if statment Used to
																	// implement the code, not to test the element
	}

	@Test(priority = 2, enabled = false)
	public void logout() {
		driver.get(logoutLink);
		WebElement LogoutMassege = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));
		String ActualMsg = LogoutMassege.getText();
		String ExcpetedMsg = "You are signed out";
		Assert.assertEquals(ActualMsg, ExcpetedMsg);
	}

	@Test(priority = 3, enabled = false)
	public void signIn() {
		WebElement signIn = driver.findElement(By.linkText("Sign In"));
		signIn.click();
		WebElement emailSignInInput = driver.findElement(By.id("email"));
		WebElement signInButton = driver.findElement(By.cssSelector(".action.login.primary"));

		emailSignInInput.sendKeys(emailforSignUp);
		WebElement passSignInInput = driver.findElement(By.id("pass"));
		passSignInInput.sendKeys(password);
		signInButton.click();
		String WelcomeMsg = driver.findElement(By.className("logged-in")).getText();
		Boolean AcualMsg = WelcomeMsg.contains("Welcome");
		Boolean ExcptedMsg = true;

		Assert.assertEquals(AcualMsg, ExcptedMsg);

	}

	@Test(priority = 4)
	public void addMenItem() {
		WebElement SelectGear = driver.findElement(By.id("ui-id-6"));
		SelectGear.click();
		

	}
}
