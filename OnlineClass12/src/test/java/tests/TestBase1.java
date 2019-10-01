package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.logigear.control.common.IButton;
import com.logigear.control.common.imp.Button;


public class TestBase1 {
	 
	 public WebDriver driver;
	 
	  @Test
	  public void openMyBlog() throws InterruptedException {
	  driver.get("https://www.seleniumeasy.com/test/");
	  Thread.sleep(2000);
	  //driver.findElement(inputFormsButton).click();
	  inputFormsButton.click();
	  Thread.sleep(2000);
	  }
	  
	  @BeforeClass
	  public void beforeClass() {   
	   System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
	   driver = new ChromeDriver();
//	   driver.get("https://www.seleniumeasy.com/test/");
//	   driver.findElement(inputFormsButton).click();
	   
	  }
	 
	  @AfterClass
	  public void afterClass() {
	   driver.quit();
	  }
	  private final IButton inputFormsButton =new Button("//li[@class='dropdown']//a[contains(text(),'Input Forms')]");
	  //private final By inputFormsButton = By.xpath("//li[@class='dropdown']//a[contains(text(),'Input Forms')]");
	}