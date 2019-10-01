package utils.common;

import utils.helper.PropertiesHelper;

public class Constants {

	public static final String BROWSER_SETTING_FILE = "src/test/resources/browsers.setting.properties";
	public static final String USERNAME = PropertiesHelper.getPropValue("invoice2go.user.name");
	public static final String PASSWORD = PropertiesHelper.getPropValue("invoice2go.user.password");
	public static final String INVOICE2GO_DATA_PATH = "src/test/resources/data/";

	// Timeout variables
	public static final int LONG_TIME = Integer.parseInt(PropertiesHelper.getPropValue("driver.longTimeout"));
	public static final int SHORT_TIME = Integer.parseInt(PropertiesHelper.getPropValue("driver.shortTimeout"));
	public static final int SLEEP_TIME = Integer.parseInt(PropertiesHelper.getPropValue("driver.sleepTime"));
	public static final int LOADING_TIME = Integer.parseInt(PropertiesHelper.getPropValue("driver.loadTime"));
}
