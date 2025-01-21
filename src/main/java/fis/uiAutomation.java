package fis;

import java.util.ArrayList;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class uiAutomation {

	//	public static String resourceDir = System.getProperty("user.dir")+"/src/main/resources/";

	public static void main(String[] args)
	{		
		//		System.setProperty("webdriver.chrome.driver", resourceDir+"chromedriver.exe");
		//		WebDriver driver = new ChromeDriver();

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.ebay.com/");
		driver.manage().window().maximize();
		//input[@placeholder="Search for anything"]
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search for anything']")));
		driver.findElement(By.xpath("//input[@placeholder='Search for anything']")).sendKeys("book");
		//span[contains(text(),'Search')]
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Search')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Search')]")).click();
		//li[@id='item425c8ce1a2']/div/div[1]/div/a/div/img
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='srp-results srp-list clearfix']/li[@data-view='mi:1686|iid:1']/div/div[1]/div/a/div/img")));
		driver.findElement(By.xpath("//ul[@class='srp-results srp-list clearfix']/li[@data-view='mi:1686|iid:1']/div/div[1]/div/a/div/img")).click();
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(0));
		driver.close();
		driver.switchTo().window(tab.get(1));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Add to cart')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click();
		//span[@aria-label='Your shopping cart contains 1 items']/span
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label='Your shopping cart contains 1 items']/span")));
		String items = driver.findElement(By.xpath("//span[@aria-label='Your shopping cart contains 1 items']/span")).getText();
		System.out.println("The cart has "+ items+" item(s) loaded");
		driver.close();
	}

}
