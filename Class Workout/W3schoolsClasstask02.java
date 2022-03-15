package week4.day1;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class W3schoolsClasstask02 {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String windowHandle = driver.getWindowHandle();
		System.out.println(windowHandle);

		driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("//button[text()='Try it']")).click();
		Alert alert = driver.switchTo().alert();

		alert.sendKeys("Arief Khan");
		Thread.sleep(2000);
		alert.accept();

		String text = driver.findElement(By.xpath("//p[@id='demo']")).getText();
		System.out.println(text);

		if (text.contains("Arief")) {
			System.out.println("This case is PASSED");

		} else {
			System.out.println("This case is FAILED");
		}

	}

}
