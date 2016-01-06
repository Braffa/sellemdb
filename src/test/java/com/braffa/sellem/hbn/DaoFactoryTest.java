package com.braffa.sellem.hbn;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.braffa.sellem.hbn.DaoFactory.daoType;
import com.braffa.sellem.model.sql.RegisteredUser;
import com.braffa.sellem.model.xml.RegisteredUserMsgXml;
import com.braffa.sellem.model.xml.RegisteredUserXml;
import com.braffa.sellem.tables.IDBActions;
import com.braffa.sellem.tables.TableEnum;
import com.braffa.sellem.tables.TableFactory;

public class DaoFactoryTest {

	private static IDBActions mySqlRegisteredUser;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mySqlRegisteredUser = TableFactory.getTable(TableEnum.REGISTERED_USER);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		mySqlRegisteredUser.dropTable();
	}

	@Before
	public void setUp() throws Exception {
		try {
			mySqlRegisteredUser.dropTable();
		} catch (Exception e) {
			// ignore if no table found
		}
		mySqlRegisteredUser.createTable();
	}

	@Test
	public void daoFactoryTest1() {
		RegisteredUserXml RegisteredUserXml = new RegisteredUserXml.RegisteredUserXmlBuilder().authorityLevel("22").userId("donaldduck").email("donald@gmail.com")
				.firstname("donny").lastname("Duck").password("mickeymouse").telephone("8888 999999").build();
		
		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(RegisteredUserXml);
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);
		registeredUserMsg = (RegisteredUserMsgXml)registeredUserDao.create();
		System.out.println(registeredUserMsg.getSuccess());
	}
	
	@Test
	public void daoFactoryTest2() {
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("33").userId("bettyboops").email("betty@gmail.com")
				.firstname("betty").lastname("boops").password("betty567").telephone("333 666666").build();
		
		RegisteredUserXml RegisteredUserXml = new RegisteredUserXml(registeredUser);
		
		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(RegisteredUserXml);
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);
		registeredUserMsg = (RegisteredUserMsgXml)registeredUserDao.create();
		System.out.println(registeredUserMsg.getSuccess());
	}

}
