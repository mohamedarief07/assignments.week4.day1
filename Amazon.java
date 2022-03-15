package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("oneplus 9 pro", Keys.ENTER);
		Thread.sleep(2000);

		// 3.Getfirst product

		String priceFor1stProduct = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();

		System.out.println("price of the first product  is: " + priceFor1stProduct.replaceAll("\\D", ""));

		// 4. Print the number of customer ratings for the first displayed product
		String NocustomerRatings = driver.findElement(By.xpath("(//span[@class='a-size-base s-underline-text'])[1]"))
				.getText();
		System.out.println("Number of customer ratings for the first displayed product: " + NocustomerRatings);

		// 5. click on the stars
		driver.findElement(By.xpath("(//i[@class='a-icon a-icon-star-small a-star-small-4-5 aok-align-bottom'])[1]"))
				.click();

		// 6. Get the percentage of ratings for all the star.
		for (int i = 5; i >= 1; i--) {
			String attribute = driver.findElement(By.xpath("//a[contains(text(),'" + i + " star')]"))
					.getAttribute("title");
			System.out.println(attribute);
		}

		// 7. Click the first text link of the first image

		driver.findElement(By.xpath("(//input[@class='nav-input nav-progressive-attribute'])[2]")).click();
		Thread.sleep(6000);

		driver.findElement(By.xpath("(//img[@class='s-image'])[1]")).click();

		// 8. Click 'Add to Cart' button
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allhandles = new ArrayList<String>(allWindows);
		driver.switchTo().window(allhandles.get(1));

		driver.findElement(By.xpath("//input[@value='Add to Cart']")).click();

		// 9. Get the cart subtotal and verify if it is correct.
		Thread.sleep(6000);

		String text = driver.findElement(By.xpath("//span[@class='a-size-base-plus a-color-price a-text-bold']/span"))
				.getText();

		System.out.println(text.replaceAll("\\D", ""));

		if (text.contains(priceFor1stProduct)) {
			System.out.println("This test case is passed");
		} else {
			System.out.println("This test case is failed");
		}
	}

}
