package com.braffa.sellem.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.braffa.sellem.connection.GenericConnection;
import com.braffa.sellem.model.sql.Product;
import com.braffa.sellem.sqlqueries.ProductSQL;

public class ProductTable extends GenericTable {

	private static final Logger logger = Logger.getLogger(ProductTable.class);

	protected ProductSQL productSQL = new ProductSQL();

	public ProductTable(GenericConnection connection) {
		this.connection = connection;
	}

	public void createTable() throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.createTable(productSQL.createTableSQl());
	}

	public void create(Object p) throws SQLException {
		Product product = (Product) p;
		connection.create(productSQL.createSQL(product));
		connection.shutdown();
	}

	public void delete(Object productId) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.create(productSQL.deleteSQL(productId.toString()));
		connection.shutdown();
	}

	public void dropTable() throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		super.dropTable(productSQL.dropSQL());
	}

	public void update(Object p) throws SQLException {
		Product product = (Product) p;
		connection.update(productSQL.updateSQL(product));
		connection.shutdown();

	}

	public List<?> read(Object productid) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.configure();
		ResultSet rs = connection.executeQuery(productSQL.readSQL(productid.toString()));
		List<Product> lOfProducts = processResultSet(rs);
		connection.shutdown();
		return lOfProducts;
	}

	public List<?> readByListOfKeys(String productids) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.configure();
		ResultSet rs = connection.executeQuery(productSQL.readByListOfKeysSQL(productids));
		List<Product> lOfProducts = processResultSet(rs);
		connection.shutdown();
		return lOfProducts;
	}

	public List<?> readAll() throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		connection.configure();
		ResultSet rs = connection.executeQuery(productSQL.readAllSQL());
		List<Product> lOfProducts = processResultSet(rs);
		connection.shutdown();
		return lOfProducts;
	}

	private List<Product> processResultSet(ResultSet rs) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}
		List<Product> lOfProducts = new ArrayList<Product>();
		while (rs.next()) {
			Product product = new Product.ProductBuilder().author(rs.getString("author"))
					.imageURL(rs.getString("imageURL")).imageLargeURL(rs.getString("imageLargeURL"))
					.manufacturer(rs.getString("manufacturer")).productIndex(rs.getString("productIndex"))
					.productgroup(rs.getString("productgroup")).productId(rs.getString("productId"))
					.productidtype(rs.getString("productidtype")).source(rs.getString("source"))
					.sourceid(rs.getString("sourceid")).title(rs.getString("title")).build();
			product.setCrDate(rs.getDate("crDate"));
			product.setUpdDate(rs.getDate("updDate"));
			lOfProducts.add(product);
		}
		return lOfProducts;
	}

}
