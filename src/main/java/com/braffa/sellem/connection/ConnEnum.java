package com.braffa.sellem.connection;

public enum ConnEnum {
	MYSQL(1), HSQL(2);
	
	private int connection;
	
	private ConnEnum(int connection) {
		this.connection = connection;
	}

	public int getConnection() {
		return this.connection;
	}
	
	
	
}
