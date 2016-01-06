package com.braffa.sellem.connection;

import java.sql.DriverManager;

import org.apache.log4j.Logger;

public class HSQLConnection extends GenericConnection {

	private static final Logger logger = Logger.getLogger(HSQLConnection.class);

	@Override
	public void setDBDriver() {
		if (logger.isDebugEnabled()) {
			logger.debug("Setting HSQL DB drivers...");
		}
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setCredentials() {
		if (logger.isDebugEnabled()) {
			logger.debug("Setting credentials for MySQL DB...");
		}
		database = "HSQLtestdb";
		username = "HSQLbraffa";
		password = "HSQLbraffapw";
	}

	@Override
	public void connect() {
		if (logger.isDebugEnabled()) {
			logger.debug("database - " + database);
			logger.debug("username - " + username);
			logger.debug("password - " + password);
		}
		String conString = "jdbc:hsqldb:" + database;
		if (logger.isDebugEnabled()) {
			logger.debug("connection string  - " + conString);
		}
		try {
			connection = DriverManager.getConnection(conString, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
