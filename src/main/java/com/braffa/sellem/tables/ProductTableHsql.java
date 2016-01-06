package com.braffa.sellem.tables;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.braffa.sellem.connection.GenericConnection;
import com.braffa.sellem.sqlqueries.ProductSQL;

public class ProductTableHsql extends ProductTable {

	private static final Logger logger = Logger.getLogger(ProductTableHsql.class);
	
	protected ProductSQL productSQL = new ProductSQL();

	public ProductTableHsql(GenericConnection connection) {
		super(connection);
	}

	@Override
	public void createTable() throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.createTable(productSQL.createTableHsql());
	}

}
