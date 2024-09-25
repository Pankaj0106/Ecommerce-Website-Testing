package selenium.tests;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.TestComponents.BaseTest;
import selenium.pageobject.CartPage;
import selenium.pageobject.CheckoutPage;
import selenium.pageobject.ConfirmationPage;
import selenium.pageobject.OrderPage;
import selenium.pageobject.ProductCatalogue;

public class StandAloneTestStream extends BaseTest{
	public String productName = "ZARA COAT 3";
	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String,String> mp) throws IOException{
		// TODO Auto-generated method stub
		
		
		//LOGIN
//		driver.findElement(By.id("userEmail")).sendKeys(gmail);
//		driver.findElement(By.id("userPassword")).sendKeys(password);
//		driver.findElement(By.id("login")).click();
		
		ProductCatalogue productCatalogue =landPage.loginApp(mp.get("email"),mp.get("password"));
		
		
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
//		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		
		
				
//		WebElement prod =	products.stream().filter(product->
//		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
//		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		productCatalogue.addToCart(productName);
		
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		CartPage cartPage = productCatalogue.goToCartPage();
		
		
		
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cartSection h3")));
//		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));	
//		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		CheckoutPage checkoutPage = cartPage.goToCheckout();
//		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		
//		Actions a = new Actions(driver);
//		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
//		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//		
//		
//		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ta-results")));
//		
		//Thread.sleep(10);
		//System.out.println(driver.findElement(By.cssSelector(".action__submit")).getText());
		//wait.until(ExpectedConditions.elementToBeSelected(By.cssSelector(".action__submit")));
		
		
		//driver.findElement(By.cssSelector(".action__submit")).click();
		//driver.findElement(By.linkText("PLACE ORDER")).click();
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".hero-primary")));
		
		
//		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		driver.quit();
		String confirmMessage = confirmationPage.getComfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test
	public void OrderHistoryTest() {
		
		String gmail = "test1100@gmail.com";
		String password = "Test@12345";
		
		ProductCatalogue productCatalogue =landPage.loginApp(gmail,password);
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}

	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\selenium\\data\\PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
		
	}
	
//	@DataProvider
//	public Object[][] getData(){
//		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "test1100@gmail.com");
//		map.put("password", "Test@12345");
//		
//		return new Object[][] {{map}};
//		//return new Object[][] {{"test1100@gmail.com","Test@12345"},{"random12345@gmail.com","Pass@12345"}};
//	}

}
