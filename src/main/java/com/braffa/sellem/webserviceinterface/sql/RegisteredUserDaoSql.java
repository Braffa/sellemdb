package com.braffa.sellem.webserviceinterface.sql;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.braffa.sellem.hbn.Dao;
import com.braffa.sellem.model.sql.Product;
import com.braffa.sellem.model.sql.RegisteredUser;
import com.braffa.sellem.model.xml.ProductXml;
import com.braffa.sellem.model.xml.RegisteredUserMsgXml;
import com.braffa.sellem.model.xml.RegisteredUserXml;
import com.braffa.sellem.tables.IDBActions;
import com.braffa.sellem.tables.TableEnum;
import com.braffa.sellem.tables.TableFactory;

public class RegisteredUserDaoSql extends Dao {

	private static final Logger logger = Logger.getLogger(RegisteredUserDaoSql.class);

	private RegisteredUserMsgXml registeredUserMsg;

	private static IDBActions mysqlRegisteredUser = TableFactory.getTable(TableEnum.REGISTERED_USER);

	public RegisteredUserDaoSql(RegisteredUserMsgXml registeredUserMsg) {
		this.registeredUserMsg = registeredUserMsg;
	}

	public Object create() {
		if (logger.isDebugEnabled()) {
			logger.debug("create");
		}
		RegisteredUserXml registeredUserXml = registeredUserMsg.getRegisteredUser();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder()
				.authorityLevel(registeredUserXml.getAuthorityLevel()).userId(registeredUserXml.getUserId())
				.email(registeredUserXml.getEmail()).firstname(registeredUserXml.getFirstname())
				.lastname(registeredUserXml.getLastname()).password(registeredUserXml.getPassword())
				.telephone(registeredUserXml.getTelephone()).build();
		try {
			mysqlRegisteredUser.create(registeredUser);
			registeredUserMsg.setSuccess("true");
		} catch (Exception e) {
			registeredUserMsg.setSuccess("false");
		}
		return registeredUserMsg;
	}

	public Object delete() {
		if (logger.isDebugEnabled()) {
			logger.debug("delete");
		}
		RegisteredUserXml registeredUserXml = registeredUserMsg.getRegisteredUser();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder()
				.authorityLevel(registeredUserXml.getAuthorityLevel()).userId(registeredUserXml.getUserId())
				.email(registeredUserXml.getEmail()).firstname(registeredUserXml.getFirstname())
				.lastname(registeredUserXml.getLastname()).password(registeredUserXml.getPassword())
				.telephone(registeredUserXml.getTelephone()).build();
		try {
			mysqlRegisteredUser.delete(registeredUser.getUserId());
			registeredUserMsg.setSuccess("true");
		} catch (Exception e) {
			registeredUserMsg.setSuccess("false");
		}
		return registeredUserMsg;
	}

	public int getCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getCount");
		}
		try {
			List<RegisteredUser> lOfRegisteredUsers = (List<RegisteredUser>) mysqlRegisteredUser.readAll();
			return lOfRegisteredUsers.size();
		} catch (Exception e) {
			registeredUserMsg.setSuccess("false");
		}
		return 0;
	}

	private RegisteredUserMsgXml processList(List<RegisteredUser> lOfRegisteredUsers) {
		ArrayList<RegisteredUserXml> lOfRegisteredUserXml = new ArrayList<RegisteredUserXml>();
		for (RegisteredUser registeredUser : lOfRegisteredUsers) {
			RegisteredUserXml registeredUserXml = new RegisteredUserXml.RegisteredUserXmlBuilder()
					.authorityLevel(registeredUser.getAuthorityLevel()).userId(registeredUser.getUserId())
					.email(registeredUser.getEmail()).firstname(registeredUser.getFirstname())
					.lastname(registeredUser.getLastname()).password(registeredUser.getPassword())
					.telephone(registeredUser.getTelephone()).crDate(registeredUser.getCrDate())
					.updDate(registeredUser.getUpdDate()).build();
			lOfRegisteredUserXml.add(registeredUserXml);
		}
		registeredUserMsg.setLOfRegisteredUsers(lOfRegisteredUserXml);
		return registeredUserMsg;
	}

	public Object read() {
		if (logger.isDebugEnabled()) {
			logger.debug("read");
		}
		RegisteredUserXml registeredUserXml = registeredUserMsg.getRegisteredUser();
		try {
			List<RegisteredUser> lOfRegisteredUsers = (List<RegisteredUser>) mysqlRegisteredUser
					.read(registeredUserXml.getUserId());
			registeredUserMsg.setSuccess("true");
			return processList(lOfRegisteredUsers);
		} catch (Exception e) {
			registeredUserMsg.setSuccess("false");
			return registeredUserMsg;
		}
	}

	public Object readAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("read");
		}
		try {
			List<RegisteredUser> lOfRegisteredUsers = (List<RegisteredUser>) mysqlRegisteredUser.readAll();
			registeredUserMsg.setSuccess("true");
			return processList(lOfRegisteredUsers);
		} catch (Exception e) {
			e.printStackTrace();
			registeredUserMsg.setSuccess("false");
			return registeredUserMsg;
		}
	}

	public Object readListOfKeys() {
		if (logger.isDebugEnabled()) {
			logger.debug("readAll");
		}
		StringBuffer sb = new StringBuffer();
		int index = registeredUserMsg.getLOfRegisteredUsers().size();
		int count = 0;
		for (RegisteredUserXml registeredUserXml : registeredUserMsg.getLOfRegisteredUsers()) {
			sb.append("(userId = '" + registeredUserXml.getUserId() + "')");
			count++;
			if (index > 1 && count < index) {
				sb.append(" or ");
			}
		}
		try {
			List<RegisteredUser> lOfRegisteredUser = (List<RegisteredUser>) mysqlRegisteredUser
					.readByListOfKeys(sb.toString());
			registeredUserMsg.setSuccess("true");
			return processList(lOfRegisteredUser);
		} catch (Exception e) {
			e.printStackTrace();
			registeredUserMsg.setSuccess("false");
			return registeredUserMsg;
		}
	}

	public Object update() {
		if (logger.isDebugEnabled()) {
			logger.debug("update");
		}
		try {
			RegisteredUserXml registeredUserXml = registeredUserMsg.getRegisteredUser();
			RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder()
					.authorityLevel(registeredUserXml.getAuthorityLevel()).userId(registeredUserXml.getUserId())
					.email(registeredUserXml.getEmail()).firstname(registeredUserXml.getFirstname())
					.lastname(registeredUserXml.getLastname()).password(registeredUserXml.getPassword())
					.telephone(registeredUserXml.getTelephone())
					.build();
			mysqlRegisteredUser.update(registeredUser);
			registeredUserMsg.setSuccess("true");
			return registeredUserMsg;
		} catch (Exception e) {
			e.printStackTrace();
			registeredUserMsg.setSuccess("false");
			return registeredUserMsg;
		}
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
