package pages.project1.desktop.imp;

import com.logigear.control.common.IButton;
import com.logigear.control.common.ILabel;
import com.logigear.control.common.ILink;
import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.Label;
import com.logigear.control.common.imp.Link;

import utils.common.Constants;
import utils.common.Navigation;

public class GeneralPage {

	public void navigateTo(Navigation navigation) {
		navigationItem.setDynamicValue(navigation.value());
		navigationItem.waitForDisplay();
		navigationItem.click();
	}

	public void waitLoadingFinish() {
		loadingDialog.waitForElementNotPresent(Constants.LONG_TIME);
		loadingIcon.waitForElementNotPresent(Constants.LONG_TIME);
	}

	public void logout() {
		if (!signOutButton.isDisplayed()) {
			userDopdownLabel.click();
		}
		signOutButton.waitForDisplay();
		signOutButton.click();
	}

	private ILink navigationItem = new Link("//nav[@class='navigation']//div/span[normalize-space()='%s']");
	private ILabel loadingIcon = new Label("//span[contains(@class,'loading-spinner-icon')]");
	private ILabel loadingDialog = new Label("id=loading-dialog");
	private ILabel userDopdownLabel = new Label("//div[div[@class='account-info']]/app-icon/span");
	private IButton signOutButton = new Button("//button[@class='logout-action']");
}
