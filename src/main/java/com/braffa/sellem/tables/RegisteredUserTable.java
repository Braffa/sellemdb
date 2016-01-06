package com.braffa.sellem.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.braffa.sellem.connection.GenericConnection;
import com.braffa.sellem.model.sql.Product;
import com.braffa.sellem.model.sql.RegisteredUser;
import com.braffa.sellem.sqlqueries.RegisteredUserSQL;

public class RegisteredUserTable extends GenericTable {

	private static final Logger logger = Logger.getLogger(RegisteredUserTable.class);

	RegisteredUserSQL registeredUserSQL = new RegisteredUserSQL();

	public RegisteredUserTable(GenericConnection connection) {
		this.connection = connection;
	}

	public void createTable() throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.createTable(registeredUserSQL.createTableSQL());
	}

	public void create(Object o) throws SQLException {
		RegisteredUser registeredUser = (RegisteredUser) o;
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.create(registeredUserSQL.createRegisteredUser(registeredUser));
	}

	public void delete(Object userId) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.create(registeredUserSQL.deleteSQL(userId.toString()));
	}

	public void dropTable() throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		super.dropTable(registeredUserSQL.dropSQL());
	}

	public List<RegisteredUser> read(String userId) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.configure();
		ResultSet rs = connection.executeQuery(registeredUserSQL.readSQL(userId));
		List<RegisteredUser> lOfRegisteredUsers = processResultSet(rs);
		connection.shutdown();
		return lOfRegisteredUsers;
	}

	public List<RegisteredUser> readAll() throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.configure();
		ResultSet rs = connection.executeQuery(registeredUserSQL.readAllSQL());
		List<RegisteredUser> lOfRegisteredUsers = processResultSet(rs);
		connection.shutdown();
		return lOfRegisteredUsers;
	}

	private List<RegisteredUser> processResultSet(ResultSet rs) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		List<RegisteredUser> lOfRegisteredUsers = new ArrayList<RegisteredUser>();
		while (rs.next()) {
			RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder()
					.authorityLevel(rs.getString("authorityLevel"))
					.email(rs.getString("email"))
					.firstname(rs.getString("firstname"))
					.lastname(rs.getString("lastname"))
					.password(rs.getString("password"))
					.telephone(rs.getString("telephone"))
					.userId(rs.getString("userId"))
					.crDate(rs.getTimestamp("crDate"))
					.updDate(rs.getTimestamp("updDate")).build();
			lOfRegisteredUsers.add(registeredUser);
		}
		return lOfRegisteredUsers;
	}

	public void shutdown() {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.shutdown();
	}

	public void update(Object r) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.update(registeredUserSQL.updateSQL((RegisteredUser) r));
	}

	public List<?> read(Object key) throws SQLException {
		connection.configure();
		ResultSet rs = connection.executeQuery(registeredUserSQL.readSQL(key.toString()));
		List<RegisteredUser> lOfRegisteredUsers = processResultSet(rs);
		connection.shutdown();
		return lOfRegisteredUsers;
	}

	public List<?> readByListOfKeys(String keys) throws SQLException {
		connection.configure();
		ResultSet rs = connection.executeQuery(registeredUserSQL.readByListOfKeysSQL(keys));
		List<RegisteredUser> lOfRegisteredUsers = processResultSet(rs);
		connection.shutdown();
		return lOfRegisteredUsers;
	}

}
