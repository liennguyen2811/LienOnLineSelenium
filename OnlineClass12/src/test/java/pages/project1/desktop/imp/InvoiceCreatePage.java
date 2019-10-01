package pages.project1.desktop.imp;

import org.openqa.selenium.Keys;

import com.logigear.control.common.IButton;
import com.logigear.control.common.ICheckBox;
import com.logigear.control.common.ILabel;
import com.logigear.control.common.ITextBox;
import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.CheckBox;
import com.logigear.control.common.imp.Label;
import com.logigear.control.common.imp.TextBox;
import com.logigear.driver.DriverUtils;

import data.project1.go.ClientData;
import data.project1.go.ItemData;
import pages.project1.IInvoiceCreatePage;
import utils.common.Common;
import utils.common.Constants;

public class InvoiceCreatePage extends GeneralPage implements IInvoiceCreatePage {

	@Override
	public void addAttachment(String attachmentPath, String attachmentName) {
		attachPhotoButton.waitForDisplay();
		DriverUtils.execJavaScript("arguments[0].removeAttribute('hidden');", attachPhotoTextBox.getElement());
		try {
			attachPhotoTextBox.enter(attachmentPath + attachmentName);
		} catch (Exception e) {
		}
		dynamicAttachmentItemsLabel.setDynamicValue(attachmentName);
		dynamicAttachmentItemsLabel.waitForDisplay();
	}

	@Override
	public void saveInvoice() {
		saveAndCloseButton.waitForDisplay();
		saveAndCloseButton.scrollToView();
		saveAndCloseButton.click();
		if (noResendInvoiceButton.isDisplayed(Constants.LOADING_TIME)) {
			noResendInvoiceButton.click();
		}
		waitLoadingFinish();
	}

	@Override
	public void sendInvoice(String toEmail, String subject) {
		sendTab.scrollToView();
		sendTab.click();
		emailToTextBox.waitForDisplay();
		if (!(toEmail.equals("") || toEmail == null)) {
			emailToTextBox.enter(toEmail);
		}
		if (!(subject.equals("") || subject == null)) {
			emailSubjectTextBox.clear();
			emailSubjectTextBox.enter(subject);
		}
		sendInvoiceButton.click();
		if (waitingInvoiceSentPopup.isDisplayed(Constants.LOADING_TIME)) {
			waitingInvoiceSentPopup.waitForElementNotPresent(Constants.LONG_TIME);
		}
	}

	@Override
	public boolean isClientInfoCorrect(String clientName, String clientEmail) {
		dynamicClientItemsLabel.setDynamicValue(clientName);
		return dynamicClientItemsLabel.getText().contains(clientEmail);
	}

	@Override
	public boolean isItemInfoCorrect(String itemDescription, long total) {
		dynamicItemTotalLabel.setDynamicValue(itemDescription);
		return Common.convertMoneyStringToNumber(dynamicItemTotalLabel.getText().trim()) == total;
	}

	@Override
	public void deleteClient(String clientName) {
		dynamicClientItemsLabel.setDynamicValue(clientName);
		dynamicClientItemsLabel.moveTo();
		dynamicDeleteClientButton.setDynamicValue(clientName);
		dynamicDeleteClientButton.waitForDisplay();
		dynamicDeleteClientButton.click();
		dynamicClientItemsLabel.waitForElementNotPresent(Constants.SHORT_TIME);
	}

	@Override
	public void deleteItem(String itemDescription) {
		dynamicItemsLabel.setDynamicValue(itemDescription);
		dynamicItemsLabel.moveTo();
		dynamicDeleteItemButton.setDynamicValue(itemDescription);
		dynamicDeleteItemButton.waitForDisplay();
		dynamicDeleteItemButton.click();
		dynamicItemsLabel.waitForElementNotPresent(Constants.SHORT_TIME);
	}

	@Override
	public boolean isAttachmentAdded(String attachmentName) {
		dynamicAttachmentItemsLabel.setDynamicValue(attachmentName);
		return dynamicAttachmentItemsLabel.isDisplayed();
	}

	@Override
	public boolean isAddClientLinkDisplayed() {
		return addClientButton.isDisplayed();
	}

	@Override
	public String getInvoiceNumber() {
		invoiceNumberLabel.waitForDisplay();
		return invoiceNumberLabel.getValue();
	}

	@Override
	public void addNewClient(ClientData data) {
		addClientButton.waitForDisplay();
		addClientButton.click();
		listOfExistingClientLabel.waitForDisplay();
		clientNameTextBox.enter(Keys.ESCAPE);
		waitLoadingFinish();
		fillClientInfo(data);
		saveClientButton.waitForElementEnabled();
		saveClientButton.click();
		dynamicClientItemsLabel.setDynamicValue(data.getClientName());
		dynamicClientItemsLabel.waitForDisplay();
	}

	private void fillClientInfo(ClientData data) {
		clientNameTextBox.waitForDisplay();
		clientNameTextBox.clear();
		clientNameTextBox.enter(data.getClientName());
		if (listOfExistingClientLabel.isDisplayed(Constants.LOADING_TIME)) {
			clientNameTextBox.enter(Keys.ESCAPE);
			waitLoadingFinish();
		}
		clientEmailTextBox.clear();
		clientEmailTextBox.enter(data.getEmail());
		clientBillToTextBox.clear();
		clientBillToTextBox.enter(data.getBillTo());
	}

	@Override
	public void addNewItem(ItemData data) {
		addItemButton.waitForDisplay();
		addItemButton.click();
		listOfExistingItemLabel.waitForDisplay();
		itemDescriptionTextBox.enter(Keys.ESCAPE);
		waitLoadingFinish();
		fillItemInfo(data);
		saveItemButton.click();
		dynamicItemsLabel.setDynamicValue(data.getDescription());
		dynamicItemsLabel.waitForDisplay();
	}

	private void fillItemInfo(ItemData data) {
		itemDescriptionTextBox.waitForDisplay();
		itemDescriptionTextBox.clear();
		itemDescriptionTextBox.enter(data.getDescription());
		if (listOfExistingItemLabel.isDisplayed(Constants.LOADING_TIME)) {
			itemDescriptionTextBox.enter(Keys.ESCAPE);
			waitLoadingFinish();
		}
		itemQuantityTextBox.clear();
		itemQuantityTextBox.enter(data.getQuantity());
		itemRateTextBox.clear();
		itemRateTextBox.enter(data.getRate());
		itemUnitTypelabel.click();
		dynamicUnitTypeOption.setDynamicValue(data.getUnitType());
		dynamicUnitTypeOption.waitForDisplay();
		dynamicUnitTypeOption.click();
		if (data.getSaveToItemList()) {
			if (!saveToItemListCheckBox.getAttribute("checked").equals("true")) {
				saveToItemListLabel.click();
			}
		} else {
			if (saveToItemListCheckBox.getAttribute("checked").equals("true")) {
				saveToItemListLabel.click();
			}
		}
		seeTaxAndDiscount.click();
		discountLabel.waitForDisplay();
		if (!(data.getDiscount().equals("") || data.getDiscount() == null)) {
			if (!discountCheckBox.getAttribute("checked").equals("true")) {
				discountLabel.click();
				discountCheckBox.waitForValuePresentInAttribute("checked", "true", Constants.LOADING_TIME);
			}
			String discountUnit = data.getDiscount().substring(data.getDiscount().length() - 1);
			dynamicDiscountUnitButton.setDynamicValue(discountUnit);
			dynamicDiscountUnitButton.click();
			discountTextBox.clear();
			discountTextBox.enter(data.getDiscount().substring(0, data.getDiscount().length() - 1));
		}
		if (!(data.getTax().equals("") || data.getTax() == null)) {
			if (!taxCheckBox.getAttribute("checked").equals("true")) {
				taxLabel.click();
				taxCheckBox.waitForValuePresentInAttribute("checked", "true", Constants.LOADING_TIME);
			}
			itemTaxlabel.click();
			dynamicTaxOption.setDynamicValue(data.getTax());
			dynamicTaxOption.waitForDisplay();
			dynamicTaxOption.click();
		}
	}

	@Override
	public void editItem(String oldDescription, ItemData newData) {
		editTab.click();
		dynamicItemsLabel.setDynamicValue(oldDescription);
		dynamicItemsLabel.click();
		fillItemInfo(newData);
		saveItemButton.click();
		dynamicItemsLabel.setDynamicValue(newData.getDescription());
		dynamicItemsLabel.waitForDisplay();
	}

	@Override
	public void editClient(String oldClientName, ClientData newData) {
		editTab.waitForDisplay();
		editTab.click();
		dynamicClientItemsLabel.setDynamicValue(oldClientName);
		dynamicClientItemsLabel.waitForDisplay();
		dynamicClientItemsLabel.click();
		fillClientInfo(newData);
		saveClientButton.click();
		dynamicClientItemsLabel.setDynamicValue(newData.getClientName());
		dynamicClientItemsLabel.waitForDisplay();
	}

	@Override
	public boolean isClientDisplayed(String clientName) {
		dynamicClientItemsLabel.setDynamicValue(clientName);
		return dynamicClientItemsLabel.isDisplayed();
	}

	@Override
	public boolean isItemDisplayed(String itemDesciption) {
		dynamicItemsLabel.setDynamicValue(itemDesciption);
		return dynamicItemsLabel.isDisplayed();
	}

	private ILabel invoiceNumberLabel = new Label("id=document_number_field");
	private IButton addClientButton = new Button("class=add-client-actions-contents");
	private IButton addItemButton = new Button("class=add-items-action");
	private IButton attachPhotoButton = new Button("class=add-attachment");
	private ITextBox attachPhotoTextBox = new TextBox("//div[label[@class='add-attachment']]/input[@type='file']");
	private ITextBox clientNameTextBox = new TextBox("id=client_name_field");
	private ITextBox clientEmailTextBox = new TextBox("id=email_field");
	private ITextBox clientBillToTextBox = new TextBox("id=billing_address_field");
	private IButton saveClientButton = new Button("//button[@data-e2e-id='add-client-save-action']");
	private ITextBox itemDescriptionTextBox = new TextBox("id=description_field");
	private ITextBox itemQuantityTextBox = new TextBox("id=quantity_field");
	private ITextBox itemRateTextBox = new TextBox("id=rate_field");
	private ILabel itemUnitTypelabel = new Label("id=unit_type_field");
	private ILabel dynamicUnitTypeOption = new Label("//div[@id='unit_type_field']/ul/li[contains(.,'%s')]");
	private ICheckBox saveToItemListCheckBox = new CheckBox("id=save_item_list_field");
	private ILabel saveToItemListLabel = new Label("//div[input[@id='save_item_list_field']]");
	private ILabel seeTaxAndDiscount = new Label("class=tax-discount-popup");
	private ICheckBox discountCheckBox = new CheckBox("id=discount_field");
	private ILabel discountLabel = new Label("//div[input[@id='discount_field']]");
	private ITextBox discountTextBox = new TextBox("id=discount_percentage_field");
	private IButton dynamicDiscountUnitButton = new Button(
			"//div[@id='discount_value_switch_field']/button[contains(.,'%s')]");
	private ICheckBox taxCheckBox = new CheckBox("//input[contains(@id,'tax_')]");
	private ILabel taxLabel = new Label("//div[//input[contains(@id,'tax_')]]");
	private ILabel itemTaxlabel = new Label("//div[@class='tax-rates']//app-dropdown/div");
	private ILabel dynamicTaxOption = new Label("//div[@class='tax-rates']//app-dropdown/div/ul/li[contains(.,'%s')]");
	private IButton saveItemButton = new Button("//button[@data-e2e-id='document-items-save-action']");
	private ILabel sendTab = new Label("//a[@data-e2e-id='document-detail-send']");
	private ILabel editTab = new Label("//a[@data-e2e-id='document-detail-create']");
	private IButton dynamicDeleteClientButton = new Button("//div[div[@class='client-info'][contains(.,'%s')]]/button");
	private ILabel dynamicClientItemsLabel = new Label("//div[div[@class='client-info'][contains(.,\"%s\")]]");
	private ILabel listOfExistingItemLabel = new Label("//div[div[button[contains(@class,'choose-multiple-action')]]]");
	private ILabel dynamicItemsLabel = new Label("//div[@data-e2e-id='document-items-list-item'][contains(.,'%s')]");
	private IButton dynamicDeleteItemButton = new Button(
			"//div[@data-e2e-id='document-items-list-item'][contains(.,'%s')]//button");
	private ILabel dynamicItemTotalLabel = new Label(
			"//div[@data-e2e-id='document-items-list-item'][contains(.,'%s')]//span[@class='total']");
	private ILabel dynamicAttachmentItemsLabel = new Label("//div[@class='attachment-item'][contains(.,'%s')]");
	private IButton saveAndCloseButton = new Button("//button[@data-e2e-id='document-save-action']");
	private ITextBox emailToTextBox = new TextBox("id=email_to_field");
	private ITextBox emailSubjectTextBox = new TextBox("id=email_subject_field");
	private IButton sendInvoiceButton = new Button("//button[@data-e2e-id='document-send-action']");
	private ILabel listOfExistingClientLabel = new Label("//div[ul[li[div[@class='client-contents']]]]");
	private ILabel waitingInvoiceSentPopup = new Label("class=sending-img");
	private IButton noResendInvoiceButton = new Button(
			"//div[@class='dialog-content']//button[@data-e2e-id='secondary-dialog-action']");
}
