package com.braffa.sellem.tables;

public enum TableEnum {
	LOGIN(1), LOGIN_HSQL(2), REGISTERED_USER(3), REGISTERED_USER_HSQL(4), PRODUCT(5), PRODUCT_HSQL(6), USER_TO_PRODUCT(
			7), USER_TO_PRODUCT_HSQL(8);

	private int table;

	private TableEnum(int table) {
		this.table = table;
	}

	public int getTable() {
		return this.table;
	}

}
