package skyprogramation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SkyProgramation {
	
	static WebDriver driver;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		//Driver configuration and navigator opening
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.sky.com.br");	
		
		System.out.println("Website from Sky has been opened.");
		
	}

	@Test
	public void testExecution() {
		
		//Timer to wait the pop-up to be closed
		WebDriverWait wait = new WebDriverWait(driver, (30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("close"))).click();
		
		//Navigating into the website
		driver.findElement(By.xpath("//*[text() = 'Programação']")).click();
		((JavascriptExecutor) driver).executeScript("scrollBy(0,1000)", "");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Passando Agora']"))).click();
		
		WebElement program = driver.findElement(By.className("program-schedule-title"));
		WebElement time = driver.findElement(By.className("program-duration"));
		String textProgramation = program.getText();
		String timeProgramation = time.getText();
		program.click();
		
		System.out.println(textProgramation);
		System.out.println(timeProgramation);
		
		String textModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sky-modal-program-title"))).getText();
		String timeModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sky-modal-program-date-time"))).getText();
		
		System.out.println(textModal);
		System.out.println(timeModal);
		
		//Name of programation comparation
		if(textProgramation.equals(textModal)) {
			System.out.println("O nome da programação está sendo exibida corretamente nos dois layouts.");
		}
		else {
			System.out.println("Os nomes não estão sendo exibidos corretamente nos dois layouts.");	
		}
		
		//Time of programation comparation
		if(timeProgramation.equals(timeModal)) {
			System.out.println("O nome da programação está sendo exibida corretamente nos dois layouts.");
		}
		else {
			System.out.println("Os nomes não estãosendo exibidos corretamente nos dois layouts.");
			
		}
		
	}

	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}
}
