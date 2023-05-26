package loginUsingDataProviderAndJXLJar;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class UsingDataProviderAndJXLJar {

	WebDriver driver;

	String[][] data;

	@DataProvider(name = "loginData")
	public String[][] TestData() throws BiffException, IOException {
		data = getDataFromExcel();
		return data;
	}

	public String[][] getDataFromExcel() throws BiffException, IOException {
		FileInputStream excel = new FileInputStream("C:\\Users\\praga\\Desktop\\LoginTestData.xls");
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(0);
		int rowCount = sheet.getRows();
		System.out.println(rowCount);
		int columnCount = sheet.getColumns();
		System.out.println(columnCount);
		String[][] testData = new String[rowCount - 1][columnCount];

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				testData[i - 1][j] = sheet.getCell(j, i).getContents();
			}

		}
		return testData;
	}

	@BeforeSuite
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\praga\\Downloads\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@AfterSuite
	public void name() {
		driver.quit();
	}

	@Test(dataProvider = "loginData")
	public void login(String uName, String pWord) {

		WebElement name = driver.findElement(By.name("username"));
		name.sendKeys(uName);

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pWord);

		WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		loginBtn.click();
	}

}
