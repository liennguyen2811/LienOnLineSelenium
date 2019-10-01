package data.project1.go;

import utils.common.Common;

public class ItemData {

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public boolean getSaveToItemList() {
		return saveToItemList;
	}

	public void setSaveToItemList(boolean saveToItemList) {
		this.saveToItemList = saveToItemList;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public long getItemTotal() {
		return Common.countIemTotal(this);
	}

	private String description;
	private String quantity;
	private String rate;
	private String unitType;
	private boolean saveToItemList;
	private String discount;
	private String tax;
}
