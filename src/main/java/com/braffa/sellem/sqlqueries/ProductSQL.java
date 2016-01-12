package com.braffa.sellem.sqlqueries;

import com.braffa.sellem.model.sql.Product;

public class ProductSQL {


	public String createTableSQl() {
		StringBuffer sql = new StringBuffer("CREATE TABLE PRODUCT ");
		sql.append("(author VARCHAR(50),         ");
		sql.append(" imageURL VARCHAR(255),      ");
		sql.append(" imageLargeURL VARCHAR(255), ");
		sql.append(" manufacturer VARCHAR(50),   ");
		sql.append(" productindex VARCHAR(20),   ");
		sql.append(" productgroup VARCHAR(20),   ");
		sql.append(" productId VARCHAR(20),      ");
		sql.append(" productidtype VARCHAR(20),  ");
		sql.append(" source VARCHAR(20),         ");
		sql.append(" sourceid VARCHAR(20),       ");
		sql.append(" title VARCHAR(100),         ");
		sql.append(" CRDATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,");
		sql.append(" UPDDATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP,");
		sql.append(" PRIMARY KEY ( productId ))  ");
		return sql.toString();
	}

	public String createTableHsql() {
		StringBuffer sql = new StringBuffer("CREATE TABLE PRODUCT ");
		sql.append(" (id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null, ");
		sql.append(" author VARCHAR(50),         ");
		sql.append(" imageURL VARCHAR(255),      ");
		sql.append(" imageLargeURL VARCHAR(255), ");
		sql.append(" manufacturer VARCHAR(50),   ");
		sql.append(" productindex VARCHAR(20),   ");
		sql.append(" productgroup VARCHAR(20),   ");
		sql.append(" productid VARCHAR(20),      ");
		sql.append(" productidtype VARCHAR(20),  ");
		sql.append(" source VARCHAR(20),         ");
		sql.append(" sourceid VARCHAR(20),       ");
		sql.append(" title VARCHAR(100),         ");
		sql.append(" CRDATE TIMESTAMP,           ");
		sql.append(" UPDDATE TIMESTAMP,          ");
		sql.append(" PRIMARY KEY ( productid ))  ");
		return sql.toString();
	}

	public String createSQL(Product product) {
		StringBuffer sql = new StringBuffer(
				"INSERT INTO PRODUCT (author, imageURL, imageLargeURL, manufacturer, productindex, productgroup, "
						+ "productId, productidtype, source, sourceid, title, CRDATE, UPDDATE) VALUES (");
		sql.append("'" + product.getAuthor() + "', ");
		sql.append("'" + product.getImageURL() + "', ");
		sql.append("'" + product.getImageLargeURL() + "', ");
		sql.append("'" + product.getManufacturer() + "', ");
		sql.append("'" + product.getProductIndex() + "', ");
		sql.append("'" + product.getProductgroup() + "', ");
		sql.append("'" + product.getProductId() + "', ");
		sql.append("'" + product.getProductidtype() + "', ");
		sql.append("'" + product.getSource() + "', ");
		sql.append("'" + product.getSourceid() + "', ");
		sql.append("'" + product.getTitle() + "', ");
		sql.append("CURRENT_TIMESTAMP, ");
		sql.append("CURRENT_TIMESTAMP");
		sql.append(")");
		return sql.toString();
	}

	public String dropSQL() {
		return "DROP TABLE PRODUCT ";
	}

	public String readSQL(String productId) {
		StringBuffer sb = new StringBuffer("SELECT author, imageURL, imageLargeURL, manufacturer, productindex, productgroup, "
				+ "productId, productidtype, source, sourceid, title, CRDATE, UPDDATE FROM PRODUCT WHERE productId = '");
		sb.append(productId + "'");
		return sb.toString();
	}
	
	public String readByListOfKeysSQL(String productIds) {
		StringBuffer sb = new StringBuffer("SELECT author, imageURL, imageLargeURL, manufacturer, productindex, productgroup, "
				+ "productId, productidtype, source, sourceid, title, CRDATE, UPDDATE FROM PRODUCT WHERE ");
		sb.append(productIds);
		return sb.toString();
	}

	public String readAllSQL() {
		StringBuffer sb = new StringBuffer("SELECT author, imageURL, imageLargeURL, manufacturer, productindex, productgroup, "
				+ "productId, productidtype, source, sourceid, title, CRDATE, UPDDATE FROM PRODUCT ");
		return sb.toString();
	}

	public String updateSQL(Product product) {
		StringBuffer sb = new StringBuffer("UPDATE PRODUCT SET ");
		boolean comma = false;
		if (product.getAuthor().length() > 0) {
			sb.append("author = '" + product.getAuthor() + "' ");
			comma = true;
		}
		if (product.getImageURL().length() > 0) {
			if (comma) {
				sb.append(", imageURL = '" + product.getImageURL() + "' ");
				comma = false;
			} else {
				sb.append("imageURL = '" + product.getImageURL() + "' ");
				comma = true;
			}
		}
		if (product.getImageLargeURL().length() > 0) {
			if (comma) {
				sb.append(", imageLargeURL = '" + product.getImageLargeURL() + "' ");
				comma = false;
			} else {
				sb.append("imageLargeURL = '" + product.getImageLargeURL() + "' ");
				comma = true;
			}
		}
		if (product.getManufacturer().length() > 0) {
			if (comma) {
				sb.append(", manufacturer = '" + product.getManufacturer() + "' ");
				comma = false;
			} else {
				sb.append("manufacturer = '" + product.getManufacturer() + "' ");
				comma = true;
			}
		}
		if (product.getProductIndex().length() > 0) {
			if (comma) {
				sb.append(", productindex = '" + product.getProductIndex() + "' ");
				comma = false;
			} else {
				sb.append("productindex = '" + product.getProductIndex() + "' ");
				comma = true;
			}
		}
		if (product.getProductgroup().length() > 0) {
			if (comma) {
				sb.append(", productgroup = '" + product.getProductgroup() + "' ");
				comma = false;
			} else {
				sb.append("productgroup = '" + product.getProductgroup() + "' ");
				comma = true;
			}
		}
		if (product.getProductidtype().length() > 0) {
			if (comma) {
				sb.append(", productidtype = '" + product.getProductidtype() + "' ");
				comma = false;
			} else {
				sb.append("productidtype = '" + product.getProductidtype() + "' ");
				comma = true;
			}
		}
		if (product.getSource().length() > 0) {
			if (comma) {
				sb.append(", source = '" + product.getSource() + "' ");
				comma = false;
			} else {
				sb.append("source = '" + product.getSource() + "' ");
				comma = true;
			}
		}
		if (product.getSourceid().length() > 0) {
			if (comma) {
				sb.append(", sourceid = '" + product.getSourceid() + "' ");
				comma = false;
			} else {
				sb.append("sourceid = '" + product.getSourceid() + "' ");
				comma = true;
			}
		}
		if (product.getTitle().length() > 0) {
			if (comma) {
				sb.append(", title = '" + product.getTitle() + "' ");
				comma = false;
			} else {
				sb.append("title = '" + product.getTitle() + "' ");
				comma = true;
			}
		}
		//sb.append("UPDDATE = CURRENT_TIMESTAMP ");
		sb.append("WHERE productId = '" + product.getProductId() + "'");
		return sb.toString();
	}

	public String deleteSQL(String productId) {
		StringBuffer sb = new StringBuffer("DELETE FROM PRODUCT ");
		sb.append(" WHERE productid = '" + productId + "'");
		return sb.toString();
	}
}
