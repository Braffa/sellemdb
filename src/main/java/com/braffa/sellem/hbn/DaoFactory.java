package com.braffa.sellem.hbn;

import org.apache.log4j.Logger;

import com.braffa.sellem.model.xml.ProductMsgXml;
import com.braffa.sellem.model.xml.RegisteredUserMsgXml;
import com.braffa.sellem.model.xml.UserToProductMsgXml;
import com.braffa.sellem.webserviceinterface.hbn.ProductDaoHbn;
import com.braffa.sellem.webserviceinterface.hbn.RegisteredUserDaoHbn;
import com.braffa.sellem.webserviceinterface.hbn.UserToProductDaoHbn;

public class DaoFactory {
	
	private static final Logger logger = Logger.getLogger(DaoFactory.class);

	public enum daoType {LOGIN_DAO, REGISTERED_USER_DAO, PRODUCT_DAO, USER_TO_PRODUCT_DAO}

	public static Dao getDAO (daoType aDaoType, Object xml) {
		if (logger.isDebugEnabled()) {
			logger.debug("getDAO aDaoType " + aDaoType + " xml "  + xml);
		}
		
		switch (aDaoType) {
		case REGISTERED_USER_DAO:
			RegisteredUserMsgXml RegisteredUsers = (RegisteredUserMsgXml) xml;
			return new RegisteredUserDaoHbn(RegisteredUsers);
		case PRODUCT_DAO:
			ProductMsgXml productMsgXml = (ProductMsgXml)xml;
			return new ProductDaoHbn(productMsgXml);
		case USER_TO_PRODUCT_DAO:
			UserToProductMsgXml userToProductMsgXml = (UserToProductMsgXml)xml;
			return new UserToProductDaoHbn(userToProductMsgXml);
		default:
			break;
		}
		
	
		return null;
	}
}
