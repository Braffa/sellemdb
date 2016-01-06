package com.braffa.sellem.webserviceinterface.sql;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.braffa.sellem.hbn.Dao;
import com.braffa.sellem.model.sql.Product;
import com.braffa.sellem.model.sql.UserToProduct;
import com.braffa.sellem.model.xml.ProductMsgXml;
import com.braffa.sellem.model.xml.ProductXml;
import com.braffa.sellem.model.xml.UserToProductMsgXml;
import com.braffa.sellem.model.xml.UserToProductXml;
import com.braffa.sellem.tables.IDBActions;
import com.braffa.sellem.tables.TableEnum;
import com.braffa.sellem.tables.TableFactory;

public class UserToProductDaoSql extends Dao {

	private static final Logger logger = Logger.getLogger(UserToProductDaoSql.class);

	private UserToProductMsgXml userToProductMsg;

	private static IDBActions mysqlUserToProduct = TableFactory.getTable(TableEnum.USER_TO_PRODUCT);

	public UserToProductDaoSql(UserToProductMsgXml userToProductMsg) {
		this.userToProductMsg = userToProductMsg;
	}

	public Object create() {
		if (logger.isDebugEnabled()) {
			logger.debug("create");
		}
		UserToProductXml userToProductXml = userToProductMsg.getUserToProduct();
		UserToProduct userToProduct = new UserToProduct.UserToProductBuilder()
				.productId(userToProductXml.getProductId()).productIndex(userToProductXml.getProductIndex())
				.userId(userToProductXml.getUserId()).build();
		try {
			mysqlUserToProduct.create(userToProduct);
			userToProductMsg.setSuccess("true");
		} catch (Exception e) {
			userToProductMsg.setSuccess("false");
		}
		return userToProductMsg;
	}

	public Object delete() {
		if (logger.isDebugEnabled()) {
			logger.debug("delete");
		}
		UserToProductXml userToProductXml = userToProductMsg.getUserToProduct();
		UserToProduct userToProduct = new UserToProduct.UserToProductBuilder()
				.productId(userToProductXml.getProductId()).productIndex(userToProductXml.getProductIndex())
				.userId(userToProductXml.getUserId()).build();
		try {
			mysqlUserToProduct.delete(userToProduct);
			userToProductMsg.setSuccess("true");
		} catch (Exception e) {
			userToProductMsg.setSuccess("false");
		}
		return userToProductMsg;
	}

	public int getCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getCount");
		}
		try {
			List<UserToProduct> lOfUserToProducts = (List<UserToProduct>) mysqlUserToProduct.readAll();
			userToProductMsg.setSuccess("true");
			return lOfUserToProducts.size();
		} catch (Exception e) {
			e.printStackTrace();
			userToProductMsg.setSuccess("false");
			return 0;
		}
	}

	private UserToProductMsgXml processList(List<UserToProduct> lOfUserToProducts) {
		ArrayList<UserToProductXml> lOfUserToProductXmls = new ArrayList<UserToProductXml>();
		for (UserToProduct UserToProduct : lOfUserToProducts) {
			UserToProductXml UserToProductXml = new UserToProductXml.UserToProductBuilder().id(UserToProduct.getId())
					.productId(UserToProduct.getProductId()).productIndex(UserToProduct.getProductIndex())
					.userId(UserToProduct.getUserId()).crDate(UserToProduct.getCrDate())
					.updDate(UserToProduct.getUpdDate()).build();
			lOfUserToProductXmls.add(UserToProductXml);
		}
		userToProductMsg.setLOfUserToProduct(lOfUserToProductXmls);
		userToProductMsg.setSuccess("true");
		return userToProductMsg;
	}

	public Object read() {
		if (logger.isDebugEnabled()) {
			logger.debug("read");
		}
		UserToProductXml userToProductXml = userToProductMsg.getUserToProduct();
		UserToProduct userToProduct = new UserToProduct.UserToProductBuilder()
				.id(userToProductXml.getId()).productId(userToProductXml.getProductId()).productIndex(userToProductXml.getProductIndex())
				.userId(userToProductXml.getUserId()).build();
		try {
			List<UserToProduct> lOfUserToProducts = (List<UserToProduct>) mysqlUserToProduct.read(userToProduct);
			return processList(lOfUserToProducts);
		} catch (Exception e) {
			e.printStackTrace();
			userToProductMsg.setSuccess("false");
			return userToProductMsg;
		}
	}

	public Object readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object readListOfKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object update() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object search() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object remove() {
		// TODO Auto-generated method stub
		return null;
	}

}
