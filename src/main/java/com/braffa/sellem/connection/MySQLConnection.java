package com.braffa.sellem.connection;

import java.sql.DriverManager;

import org.apache.log4j.Logger;

public class MySQLConnection extends GenericConnection {

	private static final Logger logger = Logger.getLogger(MySQLConnection.class);

	@Override
	public void setDBDriver() {
		if (logger.isDebugEnabled()) {
			logger.debug("Setting MySQL DB drivers...");
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setCredentials() {
		if (logger.isDebugEnabled()) {
			logger.debug("Setting credentials for MySQL DB...");
		}
		database = "sellMyThings";
		username = "sellem";
		password = "lindsay23";
	}

	@Override
	public void connect() {
		if (logger.isDebugEnabled()) {
			logger.debug("database - " + database);
			logger.debug("username - " + username);
			logger.debug("password - " + password);
		}
		String conString = "jdbc:mysql://localhost/" + database + "?" + "user=" + username + "&password=" + password;
		if (logger.isDebugEnabled()) {
			logger.debug("connection string  - " + conString);
		}

		try {
			connection = DriverManager.getConnection(conString);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
