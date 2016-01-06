package com.braffa.sellem.tables;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.braffa.sellem.connection.GenericConnection;

public abstract class GenericTable implements IDBActions {

	private static final Logger logger = Logger.getLogger(GenericTable.class);

	protected GenericConnection connection;

	public final void dropTable(String sql) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.dropTable(sql);
		connection.shutdown();
	}
	
}
