package pages.project1;

import data.project1.go.ClientData;
import data.project1.go.ItemData;

public interface IInvoiceCreatePage {

	String getInvoiceNumber();

	void addNewClient(ClientData data);

	void addNewItem(ItemData data);

	void addAttachment(String attachmentPath, String attachmentName);

	void saveInvoice();

	void sendInvoice(String toEmail, String subject);

	boolean isClientInfoCorrect(String clientName, String clientEmail);

	boolean isItemInfoCorrect(String itemDescription, long total);

	void deleteClient(String clientName);

	void deleteItem(String itemDescription);

	void editItem(String oldDescription, ItemData newData);

	void editClient(String oldClientName, ClientData newData);

	boolean isAttachmentAdded(String attachmentName);

	boolean isAddClientLinkDisplayed();

	boolean isClientDisplayed(String clientName);

	boolean isItemDisplayed(String itemDesciption);
}
