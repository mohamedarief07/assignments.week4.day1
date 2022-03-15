package week4.day1;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnAlert {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.get("http://www.leafground.com/pages/Alert.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.findElement(By.xpath("//button[text()='Alert Box']")).click();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		System.out.println(text);
		Thread.sleep(2000);
		alert.accept();

		driver.findElement(By.xpath("//button[text()='Confirm Box']")).click();
		String text1 = alert.getText();
		System.out.println(text1);
		Thread.sleep(2000);
		alert.dismiss();

		driver.findElement(By.xpath("//button[text()='Prompt Box']")).click();
		alert.sendKeys("Arief");
		Thread.sleep(2000);
		alert.accept();
		
		driver.findElement(By.xpath("//button[text()='Sweet Alert']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		Thread.sleep(4000);
		driver.close();
	

	}

}
