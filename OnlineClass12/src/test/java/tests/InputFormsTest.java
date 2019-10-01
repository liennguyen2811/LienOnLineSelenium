package tests;


import org.testng.annotations.Test;
import pages.seleniumeasy.HomePage;


public class InputFormsTest extends TestBase{
	

	@Test(groups = { "smoke" })
	public void TC001_UserCanGoInputForms() throws InterruptedException {
		
		Thread.sleep(3000);
		homePage.clickInputForms();
		Thread.sleep(30000);
		//assertTrue("The returned search result displays correct", .isAt("Selenium"));
	}
	
	private HomePage homePage = new HomePage();
}


