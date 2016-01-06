package com.braffa.sellem.connection;

public class ConnectionFactory {

	public static GenericConnection getConnection(ConnEnum connection) {
		switch (connection.getConnection()) {
		case 1:
			return new MySQLConnection();
		case 2:
			return new HSQLConnection();
		default:
			return null;
		}
	}
}
