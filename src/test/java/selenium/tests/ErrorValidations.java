package selenium.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import selenium.TestComponents.Authenticator;
import selenium.TestComponents.BaseTest;
import selenium.pageobject.CartPage;
import selenium.pageobject.ProductCatalogue;

public class ErrorValidations extends BaseTest{
	
	@Test(retryAnalyzer= Authenticator.Retry.class)
	public void submitOrder() {
		String gmail = "test1100@gmail.com";
		String password = "Tet@12345";
		
		landPage.loginApp(gmail,password);
		System.out.println(landPage.getErrorMessage());
		Assert.assertEquals("Incorrect email or password.", landPage.getErrorMessage());
		tearDown();
	}
	
	@Test
	public void ProductErrorValidation() {
		String gmail = "test1100@gmail.com";
		String password = "Test@12345";
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue =landPage.loginApp(gmail,password);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCart(productName);
		
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		tearDown();
	}

}
