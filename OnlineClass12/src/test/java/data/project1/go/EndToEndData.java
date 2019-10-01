package data.project1.go;

import com.github.javafaker.Faker;

import data.base.DataBase;
import utils.common.Common;
import utils.common.Constants;

public class EndToEndData extends DataBase {

	public EndToEndData() {
		super(Constants.INVOICE2GO_DATA_PATH + "endToEndData.json");
	}

	public void initData() {
		Faker fakerData = new Faker();
		getClientData().setClientName(fakerData.name().fullName());
		getClientData().setBillTo(fakerData.address().fullAddress());
		getClientData().setEmail(fakerData.internet().emailAddress());
		getEditClientData().setClientName(fakerData.name().fullName());
		getEditClientData().setBillTo(fakerData.address().fullAddress());
		getEditClientData().setEmail(fakerData.internet().emailAddress());
		getNewClientData().setClientName(fakerData.name().fullName());
		getNewClientData().setBillTo(fakerData.address().fullAddress());
		getNewClientData().setEmail(fakerData.internet().emailAddress());
		getItemData().setDescription(fakerData.lorem().sentence(3));
		getEditItemData().setDescription(fakerData.lorem().sentence(3));
		getNewItemData().setDescription(fakerData.lorem().sentence(3));
	}

	public String getAttachmentName() {
		return attactmentName;
	}

	public void setAttactmentName(String attactmentName) {
		this.attactmentName = attactmentName;
	}

	public ClientData getClientData() {
		return clientData;
	}

	public void setClientData(ClientData clientData) {
		this.clientData = clientData;
	}

	public ClientData getEditClientData() {
		return editClientData;
	}

	public void setEditClientData(ClientData editClientData) {
		this.editClientData = editClientData;
	}

	public ClientData getNewClientData() {
		return newClientData;
	}

	public void setNewClientData(ClientData newClientData) {
		this.newClientData = newClientData;
	}

	public ItemData getItemData() {
		return itemData;
	}

	public void setItemData(ItemData itemData) {
		this.itemData = itemData;
	}

	public ItemData getEditItemData() {
		return editItemData;
	}

	public void setEditItemData(ItemData editItemData) {
		this.editItemData = editItemData;
	}

	public ItemData getNewItemData() {
		return newItemData;
	}

	public void setNewItemData(ItemData newItemData) {
		this.newItemData = newItemData;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getUnpaidTex() {
		return unpaidTex;
	}

	public void setUnpaidTex(String unpaidTex) {
		this.unpaidTex = unpaidTex;
	}

	public String getSentText() {
		return sentText;
	}

	public void setSentText(String sentText) {
		this.sentText = sentText;
	}

	public long getInvoiceTotal() {
		return Common.countInvoiceTotal(itemData);
	}

	public long getNewInvoiceTotal() {
		return Common.countInvoiceTotal(newItemData);
	}

	private String attactmentName;
	private ClientData clientData;
	private ClientData editClientData;
	private ClientData newClientData;
	private ItemData itemData;
	private ItemData editItemData;
	private ItemData newItemData;
	private String emailAddress;
	private String emailSubject;
	private String unpaidTex;
	private String sentText;
}
