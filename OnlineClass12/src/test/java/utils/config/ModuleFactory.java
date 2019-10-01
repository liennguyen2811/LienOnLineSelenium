package utils.config;

import org.testng.IModuleFactory;
import org.testng.ITestContext;
import org.testng.xml.XmlTest;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

import pages.project1.IHomePage;
import pages.project1.IInvoiceCreatePage;
import pages.project1.IInvoiceListPage;
import pages.project1.ILoginPage;
import pages.project1.desktop.imp.HomePage;
import pages.project1.desktop.imp.InvoiceCreatePage;
import pages.project1.desktop.imp.InvoiceListPage;
import pages.project1.desktop.imp.LoginPage;
//import pages.project1.desktop.imp.HomePage;
//import pages.project1.desktop.imp.InvoiceCreatePage;
//import pages.project1.desktop.imp.InvoiceListPage;
//import pages.project1.desktop.imp.LoginPage;
//import pages.project1.mobile.imp.MobileHomePage;
//import pages.project1.mobile.imp.MobileInvoiceCreatePage;
//import pages.project1.mobile.imp.MobileInvoiceListPage;
//import pages.project1.mobile.imp.MobileLoginPage;
import utils.common.IConfiguration;
import utils.common.imp.DesktopConfiguration;
import utils.common.imp.MobileConfiguration;

public class ModuleFactory implements IModuleFactory {

	@Override
	public Module createModule(ITestContext context, Class<?> testClass) {
		XmlTest xml = context.getCurrentXmlTest();
		String mode = xml.getParameter("mode");
		return new InjectionModule(mode);
	}
}

@SuppressWarnings("rawtypes")
class InjectionModule extends AbstractModule {
	enum Mode {
		DESKTOP, MOBILE
	}

	Mode mode;

	Class[] listInterface = { IConfiguration.class, ILoginPage.class, IHomePage.class, IInvoiceListPage.class,
			IInvoiceCreatePage.class };
	Class[] listDesktopImp = { DesktopConfiguration.class,  LoginPage.class, HomePage.class, InvoiceListPage.class,
			InvoiceCreatePage.class};
	Class[] listMobileImp = { MobileConfiguration.class };

	InjectionModule(String mode) {
		if (mode == null || mode.trim().length() == 0)
			mode = "DESKTOP";		

		this.mode = Mode.valueOf(mode.toUpperCase());
	}

	@Override
	protected void configure() {
		switch (mode) {
		case MOBILE:
			BindClasses(listInterface, listMobileImp);
			break;
		default:
			BindClasses(listInterface, listDesktopImp);
			break;
		}

	}

	@SuppressWarnings("unchecked")
	private void BindClasses(Class[] lstInterface, Class[] lstImplement) {
		for (int i = 0; i < lstInterface.length; i++)
			bind(lstInterface[i]).to(lstImplement[i]);
	}
}
