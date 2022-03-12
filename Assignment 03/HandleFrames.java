package week4.day1.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleFrames {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		WebDriver frame = driver.switchTo().frame(0);
		driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys("Arief Khan");

		WebDriver Frame3 = driver.switchTo().frame("frame3");
		driver.findElement(By.xpath("//input[@id='a']")).click();

		driver.switchTo().defaultContent();
		WebElement Frame2 = driver.findElement(By.xpath("//iframe[@id='frame2']"));
		driver.switchTo().frame(Frame2);
		WebElement getByIndex = driver.findElement(By.xpath("//select[@class='col-lg-3']"));
		Select index = new Select(getByIndex);
		index.selectByIndex(1);

	}

}
