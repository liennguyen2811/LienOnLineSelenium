package utils.common;

public enum Navigation {

	Home("Home"), Invoices("Invoices"), Settings("Settings");
	private String value;

	Navigation(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
