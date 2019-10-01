package pages.seleniumeasy;


import com.logigear.control.common.IButton;
import com.logigear.control.common.imp.Button;


public class HomePage {
		
private IButton inputFormsButton =new Button("//li[@class='dropdown']//a[contains(text(),'Input Forms')]");
//private final By inputFormsButton = By.xpath("//li[@class='dropdown']//a[contains(text(),'Input Forms')]");


	
	public void clickInputForms() {
		
	//	driver.findElement(inputFormsButton).click();
		inputFormsButton.waitForVisibility();
		inputFormsButton.click();
	}

}