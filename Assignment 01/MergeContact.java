package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		driver.get("http://leaftaps.com/opentaps/control/login");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();

		driver.findElement(By.xpath("//a[contains(text(),'CRM/SFA')]")).click();
		driver.findElement(By.xpath("//a[(text()='Contacts')]")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();

		Set<String> allWindows = driver.getWindowHandles();
		List<String> allhandles = new ArrayList<String>(allWindows);
		driver.switchTo().window(allhandles.get(1));
		System.out.println("LookupContacts");
		System.out.println(driver.getCurrentUrl());

		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a")).click();
		driver.switchTo().window(allhandles.get(0));
		System.out.println("mergeContactsForm");
		System.out.println(driver.getCurrentUrl());

		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		/*
		 * for (int i = 0; i < allhandles.size(); i++) { String string =
		 * allhandles.get(i); System.out.println(string); }
		 */
		Set<String> allWindowset = driver.getWindowHandles();
		List<String> allhandles1 = new ArrayList<String>(allWindowset);
		driver.switchTo().window(allhandles1.get(1));
		System.out.println("LookupContacts");
		System.out.println(driver.getCurrentUrl());

		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[2]/a")).click();
		driver.switchTo().window(allhandles1.get(0));
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		String title = driver.getTitle();
		System.out.println("Title of the screen is " +title);

		if (title.contains("View Contact")) {
			System.out.println("This case is PASSED");

		} else {
			System.out.println("This case is FAILED");
		}
		Thread.sleep(6000);
		driver.close();
	}

}
