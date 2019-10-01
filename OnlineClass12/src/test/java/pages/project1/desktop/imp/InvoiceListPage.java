package pages.project1.desktop.imp;

import com.logigear.control.common.ILink;
import com.logigear.control.common.imp.Link;

import pages.project1.IInvoiceListPage;
import utils.common.Common;

public class InvoiceListPage extends GeneralPage implements IInvoiceListPage {

	@Override
	public void addInvoice() {
		addInvoiceLink.waitForDisplay();
		waitLoadingFinish();
		addInvoiceLink.click();
	}

	@Override
	public boolean isInvoiceDisplayedInTab(String tabName, String invoiceNumber) {
		addInvoiceLink.waitForDisplay();
		waitLoadingFinish();
		dynamicInvoicesTab.setDynamicValue(tabName);
		dynamicInvoiceItem.setDynamicValue(invoiceNumber);
		return dynamicInvoicesTab.getAttribute("class").contains("active") && dynamicInvoiceItem.isDisplayed();
	}

	@Override
	public boolean isInvoiceStatusCorrect(String invoiceNumber, String status) {
		waitLoadingFinish();
		dynamicInvoiceStatus.setDynamicValue(invoiceNumber);
		return dynamicInvoiceStatus.getText().contains(status);
	}

	@Override
	public void gotoEditInvoice(String invoiceNumber) {
		dynamicInvoiceItem.setDynamicValue(invoiceNumber);
		dynamicInvoiceItem.waitForDisplay();
		dynamicInvoiceItem.click();
	}

	@Override
	public boolean isInvoiceTotalCorrect(String invoiceNumber, long totalNumber) {
		waitLoadingFinish();
		dynamicInvoiceTotal.setDynamicValue(invoiceNumber);
		return Common.convertMoneyStringToNumber(dynamicInvoiceTotal.getText().trim()) == totalNumber;
	}

	@Override
	public void logOut() {
	}

	private ILink addInvoiceLink = new Link("//a[normalize-space()='Add invoice']");
	private ILink dynamicInvoicesTab = new Link("//a[contains(@data-e2e-id,'invoices-tab')][contains(.,'%s')]");
	private ILink dynamicInvoiceItem = new Link(
			"//div[@data-e2e-id='feed-item']//div[@data-e2e-id='invoice-list-item-document-number'][contains(.,'%s')]/ancestor::a[contains(@data-e2e-id,'invoice-list-item')]");
	private ILink dynamicInvoiceStatus = new Link(
			"//div[@data-e2e-id='feed-item']//div[div[@data-e2e-id='invoice-list-item-document-number'][contains(.,'%s')]]/div[@data-e2e-id='invoice-list-item-document-status']");
	private ILink dynamicInvoiceTotal = new Link(
			"//div[@data-e2e-id='feed-item']//div[div[@data-e2e-id='invoice-list-item-document-number'][contains(.,'%s')]]/div[@data-e2e-id='invoice-list-item-document-total']");
}
