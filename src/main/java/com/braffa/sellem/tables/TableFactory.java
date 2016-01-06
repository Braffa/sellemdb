package com.braffa.sellem.tables;

import com.braffa.sellem.connection.ConnEnum;
import com.braffa.sellem.connection.ConnectionFactory;

public class TableFactory {

	public static GenericTable getTable(TableEnum table) {

		switch (table.getTable()) {
		case 3:
			return new RegisteredUserTable(ConnectionFactory.getConnection(ConnEnum.MYSQL));
		case 4:
			return new RegisteredUserTableHsql(ConnectionFactory.getConnection(ConnEnum.HSQL));
		case 5:
			return new ProductTable(ConnectionFactory.getConnection(ConnEnum.MYSQL));
		case 6:
			return new ProductTableHsql(ConnectionFactory.getConnection(ConnEnum.HSQL));
		case 7:
			return new UserToProductTable(ConnectionFactory.getConnection(ConnEnum.MYSQL));
		case 8:
			return new UserToProductTableHsql(ConnectionFactory.getConnection(ConnEnum.HSQL));
		default:
			return null;
		}

	}

}
