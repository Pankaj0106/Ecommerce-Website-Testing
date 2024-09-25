package selenium;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUsingLoops {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String gmail = "test1100@gmail.com";
		String password = "Test@12345";
		String[] names = {"ZARA COAT 3","ADIDAS ORIGINAL"};

		
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(7));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		
		
		//LOGIN
		driver.findElement(By.id("userEmail")).sendKeys(gmail);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		//ADD TO CART
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//ADD-TO-CART USING LOOPS
		
		//System.out.println("OUTSIDE FOR");
		for(WebElement pro:products) {
			System.out.println("INSIDE FOR");
			String prodName = pro.findElement(By.cssSelector("b")).getText();
			System.out.println(prodName);
			
			List<String> listNames= Arrays.asList(names);
			
			
			int j=0;
			if(listNames.contains(prodName)) {
				j++;
				pro.findElement(By.cssSelector("button.btn.w-10.rounded")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
				//ng-animating
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
				if(j==names.length) {
					break;
				}
				
			}
		
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[routerlink*='cart']")));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cartSection")));
		List<WebElement> cartProducts = driver.findElements(By.className("cartSection"));
		System.out.println(cartProducts);
		
		for(WebElement cp:cartProducts) {
			System.out.println("Inside cartProducts section");
			String cartNames =cp.findElement(By.cssSelector(".cartSection h3")).getText();
			System.out.println(cartNames);
		}

	}

}
