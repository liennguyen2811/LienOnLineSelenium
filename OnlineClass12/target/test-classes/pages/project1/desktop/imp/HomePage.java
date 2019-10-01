package pages.project1.desktop.imp;

import pages.project1.IHomePage;
import utils.common.Navigation;

public class HomePage extends GeneralPage implements IHomePage {

	@Override
	public void openInvoicesPage() {
		waitLoadingFinish();
		navigateTo(Navigation.Invoices);
	}
}
