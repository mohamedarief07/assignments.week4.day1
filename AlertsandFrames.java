package week4.day1.assignments;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertsandFrames {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("//button[text()='Try it']")).click();

		// Click OK/Cancel in the alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		WebElement getTextFun = driver.findElement(By.xpath("//p[@id='demo']"));
		String text = getTextFun.getText();
		System.out.println(text);

		if (text.contains("You pressed OK")) {
			System.out.println("This case is PASSED");

		} else {
			System.out.println("This case is Failed");
		}

	}

}
