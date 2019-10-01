package pages.project1;

public interface IInvoiceListPage {

	void addInvoice();

	boolean isInvoiceDisplayedInTab(String tabName, String invoiceNumber);

	boolean isInvoiceStatusCorrect(String invoiceNumber, String status);

	boolean isInvoiceTotalCorrect(String invoiceNumber, long totalNumber);

	void gotoEditInvoice(String invoiceNumber);

	void logOut();
}
