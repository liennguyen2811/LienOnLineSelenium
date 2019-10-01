package pages.project1.desktop.imp;

import com.logigear.control.common.ILink;
import com.logigear.control.common.imp.Link;

public class BookingHomePage {

	private ILink registerLink = new Link(
			"//div[@class='sign_in_wrapper']/span[contains(normalize-space(),'Register')]");

	public void goToRegisterPage() {
		registerLink.waitForVisibility();
		registerLink.click();
	}

}
