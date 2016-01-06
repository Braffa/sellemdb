package com.braffa.sellem.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.braffa.sellem.connection.GenericConnection;
import com.braffa.sellem.model.sql.UserToProduct;
import com.braffa.sellem.sqlqueries.UserToProductSQL;

public class UserToProductTable extends GenericTable {

	private static final Logger logger = Logger.getLogger(UserToProductTable.class);

	protected UserToProductSQL userToProductSQL = new UserToProductSQL();

	public UserToProductTable(GenericConnection connection) {
		this.connection = connection;
	}

	public void createTable() throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.createTable(userToProductSQL.createTableSQL());
	}

	public void create(Object u) throws SQLException {
		UserToProduct userToProduct = (UserToProduct) u;
		connection.create(userToProductSQL.createSQL(userToProduct));
		connection.shutdown();
	}

	public void delete(Object O) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		UserToProduct userToProduct = (UserToProduct) O;
		connection.create(userToProductSQL.deleteSQL(userToProduct));
		connection.shutdown();
	}

	public void dropTable() throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		super.dropTable(userToProductSQL.dropSQL());
	}

	public List<?> read(Object u) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		UserToProduct userToProduct = (UserToProduct) u;
		connection.configure();
		ResultSet rs = connection.executeQuery(userToProductSQL.readSQL(userToProduct));
		List<UserToProduct> lOfuserToProducts = processResultSet(rs);
		connection.shutdown();
		return lOfuserToProducts;
	}

	public List<?> readAll() throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.configure();
		ResultSet rs = connection.executeQuery(userToProductSQL.readAllSQL());
		List<UserToProduct> lOfUserToProducts = processResultSet(rs);
		connection.shutdown();
		return lOfUserToProducts;
	}

	public void update(Object u) throws SQLException {
		UserToProduct userToProduct = (UserToProduct) u;
		connection.update(userToProductSQL.updateSQL(userToProduct));
		connection.shutdown();

	}
	
	private List<UserToProduct> processResultSet(ResultSet rs) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		List<UserToProduct> lOfUserToProducts = new ArrayList<UserToProduct>();
		while (rs.next()) {
			UserToProduct userToProduct = new UserToProduct.UserToProductBuilder().id(rs.getInt("id")).userId(rs.getString("userId")).productId(rs.getString("productId"))
					.productIndex(rs.getString("productIndex")).crDate(rs.getDate("CRDATE")).updDate(rs.getDate("UPDDATE")).build();
			lOfUserToProducts.add(userToProduct);
		}
		return lOfUserToProducts;
	}

	public List<?> readByListOfKeys(String keys) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
