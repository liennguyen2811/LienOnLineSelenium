package utils.common.imp;

import com.logigear.driver.DriverUtils;

import utils.common.Common;
import utils.common.Constants;
import utils.common.IConfiguration;
import utils.helper.PropertiesHelper;

public class DesktopConfiguration implements IConfiguration {

	@Override
	public void initial(String browser) {
		DriverUtils.setWaitForAjax(false);
		DriverUtils.setPageLoadTimeout(Constants.LONG_TIME);
		DriverUtils.maximizeBrowser();
		System.out.println("if go here");
		DriverUtils.navigate(PropertiesHelper.getPropValue("seleniumeasy.url"));
		System.out.println("if go here1");
		Common.setUploadFolderPath(browser);
		System.out.println("if go here2");
	}
}
