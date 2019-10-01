package utils.common;

import java.awt.image.BufferedImage;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import com.logigear.control.common.imp.Element;
import com.logigear.driver.DriverUtils;

import data.project1.go.ItemData;

public class Common {

	public static ThreadLocal<String> uploadFolderPath = new ThreadLocal<String>();

	public static String convertImageToURI(String imagePath) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		BufferedImage img;
		File image = new File(imagePath);
		try {
			img = ImageIO.read(image);
			ByteArrayOutputStream convert = new ByteArrayOutputStream();
			ImageIO.write(img, "png", convert);
			String data = DatatypeConverter.printBase64Binary(convert.toByteArray());
			return data;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public static String randomString() {
		String letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String number = "0123456789";
		String string = "";
		Random rd = new Random();
		int indx = rd.nextInt(20);

		for (int i = 0; i < indx; i++) {
			int kt = rd.nextInt(2);
			if (kt == 0)// string will have letter(s)
			{
				int lt = rd.nextInt(52);
				string += Character.toString(letter.charAt(lt));
			} else // password will have number(s)
			{
				int lt = rd.nextInt(9);
				string += Character.toString(number.charAt(lt));
			}
		}
		return string;
	}

	public static String screenshotURI(String imagePath) {
		String randomPopUpId = "id" + UUID.randomUUID().toString();
		String randomButtonId = randomPopUpId + "button";
		String imageString = "data:image/png;base64," + convertImageToURI(imagePath);
		String htmlScript = "<script>$(document).ready(function(){$( \"#" + randomPopUpId
				+ "\" ).dialog({ autoOpen: false });$( \"#" + randomPopUpId
				+ "\" ).dialog({width:1000},{height:700});$( \"#" + randomButtonId + "\" ).click(function() {$( \"#"
				+ randomPopUpId + "\" ).dialog( \"open\" );});});</script></br><img id=\"" + randomButtonId
				+ "\" src=\"" + imageString
				+ "\" style=\"border: 4px solid #f6f7fa;width: 150px;cursor: zoom-in;display: block;margin-top: 15px;\"/></br><div style=\"width: 50%; margin: 0 auto;\" id=\""
				+ randomPopUpId + "\" > <a href=\"#" + randomPopUpId
				+ "\"  class=\"ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right\"></a><img style=\"width:800px;height:600;\"  src=\""
				+ imageString + "\"/></div>";
		return htmlScript;
	}

	public static String getNowTime(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getRandomString(String prefix) {
		return prefix.concat(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Date()));
	}

	public static void switchTab(String url, boolean isURLFull) {
		ArrayList<String> tabs = new ArrayList<String>(DriverUtils.getWebDriver().getWindowHandles());

		for (int i = 1; i < tabs.size(); i++) {
			DriverUtils.switchTo(tabs.get(i));
			String fullURL = DriverUtils.getWebDriver().getCurrentUrl();
			if (!isURLFull & fullURL.toLowerCase().contains(url.toLowerCase())) {
				break;
			} else if (isURLFull & url.equals(fullURL)) {
				break;
			}
		}
	}

	public static void switchToMain(boolean doesCurrentBrowserClose) {
		ArrayList<String> tabs = new ArrayList<String>(DriverUtils.getWebDriver().getWindowHandles());
		DriverUtils.getWebDriver().close();
		DriverUtils.switchTo(tabs.get(0));
	}

	public static void writeTextToFile(String fileName, String content) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void waitPageRefresh(Element eleLoading, int timeout) {
		eleLoading.waitForPresent(timeout);
		eleLoading.waitForElementNotPresent(timeout);
	}

	public static long countIemTotal(ItemData data) {
		return Integer.parseInt(data.getQuantity()) * Long.parseLong(data.getRate());
	}

	public static long countInvoiceTotal(ItemData data) {
		long tempTotalItem = Integer.parseInt(data.getQuantity()) * Integer.parseInt(data.getRate());
		long tempTax = tempTotalItem * Integer.parseInt(data.getTax().substring(0, data.getTax().length() - 1)) / 100;
		return tempTotalItem + tempTax;
	}

	public static long convertMoneyStringToNumber(String moneyString) {
		try {
			NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
			long myNumber = nf.parse(moneyString.replace("$", "")).longValue();
			return myNumber;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void setUploadFolderPath(String browser) {
		if (browser.contains("safari")) {
			uploadFolderPath.set("/Users/vietnamtest/Desktop/grid/");
		} else {
			File f = new File("src/test/resources/uploadData/");
			uploadFolderPath.set(f.getAbsolutePath() + "\\");
		}
	}
}
