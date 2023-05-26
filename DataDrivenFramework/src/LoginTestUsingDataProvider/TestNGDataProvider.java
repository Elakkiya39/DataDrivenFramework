package LoginTestUsingDataProvider;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGDataProvider {
	WebDriver driver;
	
	String[][] data = { { "Admin", "admin123" }, { "Admin1", "admin1234" }, { "Admin1", "admin123" },
			{ "Admin", "admin1234" } };

	@DataProvider(name = "loginData")
	public String[][] TestData() {
		return data;
	}

	@Test(dataProvider = "loginData")
	public void login(String uName, String pWord) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\praga\\Downloads\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebElement name = driver.findElement(By.name("username"));
		name.sendKeys(uName);

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pWord);

		WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		loginBtn.click();
		
		driver.quit();
	}

}
