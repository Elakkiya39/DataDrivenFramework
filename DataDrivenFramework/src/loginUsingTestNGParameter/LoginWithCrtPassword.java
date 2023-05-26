package loginUsingTestNGParameter;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginWithCrtPassword {

	@Test
	@Parameters({"usename" , "password"})
	public void bothCorrect(String uName, String pWord) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\praga\\Downloads\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		

		WebElement name = driver.findElement(By.name("username"));
		name.sendKeys(uName);
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pWord);
		
		WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		loginBtn.click();
		
		//quit browser
		driver.quit();
	}
}
