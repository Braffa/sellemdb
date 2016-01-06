/**
 * 
 */
package com.braffa.sellem.tables;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.braffa.sellem.connection.GenericConnection;

/**
 * @author david.brayfield
 *
 */
public class UserToProductTableHsql extends UserToProductTable {

	private static final Logger logger = Logger.getLogger(UserToProductTableHsql.class);

	public UserToProductTableHsql(GenericConnection connection) {
		super(connection);
	}

	@Override
	public void createTable() throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.createTable(userToProductSQL.createTableHsql());
	}
	
}
