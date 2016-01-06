package com.braffa.sellem.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public abstract class GenericConnection {

	private static final Logger logger = Logger.getLogger(GenericConnection.class);

	protected String database;
	protected String username;
	protected String password;
	protected Connection connection;
	protected PreparedStatement ps;
	protected ResultSet resultSet;

	public ResultSet getResultSet() {
		return resultSet;
	}

	public final void configure() {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		setDBDriver();
		setCredentials();
		connect();
	}

	public final void shutdown() {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		close();
		destroy();
	}

	public abstract void setDBDriver();

	public abstract void setCredentials();

	public abstract void connect();

	public ResultSet executeQuery(String sql) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug(sql);
		}
		ps = connection.prepareStatement(sql);
		resultSet = ps.executeQuery();
		return resultSet;
	}

	public void excuteUpdate(String sql) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug(sql);
		}
		ps = connection.prepareStatement(sql);
		ps.executeUpdate();
	}


	public void close() {
		if (logger.isDebugEnabled()) {
			logger.debug("Closing connections...");
		}
		try {
			ps.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void destroy() {
		if (logger.isDebugEnabled()) {
			logger.debug("Destroying connection objects...");
		}
	}
	
	public void createTable(String sql) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug(sql);
		}
		configure();
		excuteUpdate(sql);
		shutdown();
	}
	
	public void create(String sql) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		configure();
		excuteUpdate(sql);
		shutdown();
	}
	
	public void delete(String sql) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		configure();
		excuteUpdate(sql);
		shutdown();
	}
	
	public void dropTable(String sql) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		configure();
		excuteUpdate(sql);
		shutdown();
	}
	
	public void update(String sql) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		configure();
		excuteUpdate(sql);
		shutdown();
	}

}
