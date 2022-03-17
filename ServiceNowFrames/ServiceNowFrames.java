package week4.day1.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowFrames {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// Step1: Load ServiceNow application URL
		driver.get("https://dev112418.service-now.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.switchTo().frame(0);

		// Step2: Enter username (Check for frame before entering the username)
		driver.findElement(By.id("user_name")).sendKeys("admin");

		// Step3: Enter password
		driver.findElement(By.id("user_password")).sendKeys("VR5f8EKhfLwi");

		// Step4: Click Login
		driver.findElement(By.id("sysverb_login")).click();

		// Step5: Search “incident “ Filter Navigator
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident");

		// Step6: Click “All”
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();

		// Step7: Click New button
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();

		// Step8: Select a value for Caller and Enter value for short_description
		driver.findElement(By.xpath("//input[@id='incident.short_description']"))
				.sendKeys("testing the Service now application");
		driver.findElement(By.xpath("//span[@class='icon icon-search']")).click();
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allhandles = new ArrayList<String>(allWindows);
		driver.switchTo().window(allhandles.get(1));
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();

		// Step9: Read the incident number and save it a variable
		driver.switchTo().window(allhandles.get(0));

		driver.switchTo().frame(0);
		Thread.sleep(4000);
		String InNumber = driver
				.findElement(By.xpath(
						"//div[@class='col-xs-10 col-sm-9 col-md-6 col-lg-5 form-field input_controls']//div//input"))
				.getAttribute("value");
		System.out.println("InNumber :" + InNumber);

		// Step10: Click on Submit button

		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[@id='sysverb_insert_bottom']")).click();

		// Step 11: Search the same incident number in the next search screen as below
		// driver.switchTo().frame(0);
		driver.findElement(By.xpath("(//input[@placeholder='Search'])[1]")).sendKeys(InNumber, Keys.ENTER);

		// Step12: Verify the incident is created successful and take snapshot of the
		// created incident.
		String serachIncident = driver.findElement(By.xpath("(//td[@class='vt'])[1]//a")).getText();
		System.out.println("serachIncident: " + serachIncident);

		if (InNumber.contains(serachIncident)) {
			System.out.println("This test case is passed");
		} else {
			System.out.println("This test case is failed");
		}

		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		File image = new File("./snaps/" + InNumber + ".jpg");
		FileUtils.copyFile(screenshot, image);

	}

}
