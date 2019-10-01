package tests;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Parameters;

import com.google.inject.Inject;
import com.logigear.driver.DriverProperty;
import com.logigear.driver.DriverUtils;
import com.logigear.helper.BrowserSettingHelper;

import utils.common.Constants;
import utils.common.IConfiguration;
import utils.config.ModuleFactory;

@Guice(moduleFactory = ModuleFactory.class)
public class TestBase {

	@Parameters({ "browser", "autoLogBug" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(String browser, boolean autoLogBug, Method method, ITestContext context) throws Throwable {
		context.setAttribute("autoLogBug", autoLogBug);
		DriverProperty property = BrowserSettingHelper.getDriverProperty(Constants.BROWSER_SETTING_FILE, browser,
				method.getName());
		DriverUtils.getDriver(property);
		DriverUtils.setTimeOut(Constants.LONG_TIME);
		configuration.initial(browser);
	}

	@AfterMethod(alwaysRun = true)
	public void cleanUp(ITestResult result) {
		DriverUtils.quitBrowser(result.isSuccess());
	} 

	@Inject
	IConfiguration configuration;
}

