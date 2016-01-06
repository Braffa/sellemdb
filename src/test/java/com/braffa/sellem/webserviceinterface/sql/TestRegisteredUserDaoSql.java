package com.braffa.sellem.webserviceinterface.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.braffa.sellem.datafortesting.RegisteredUserTestData;
import com.braffa.sellem.model.sql.RegisteredUser;
import com.braffa.sellem.model.xml.RegisteredUserMsgXml;
import com.braffa.sellem.model.xml.RegisteredUserXml;
import com.braffa.sellem.tables.IDBActions;
import com.braffa.sellem.tables.TableEnum;
import com.braffa.sellem.tables.TableFactory;
import com.braffa.sellem.webserviceinterface.sql.RegisteredUserDaoSql;

public class TestRegisteredUserDaoSql {

	private static IDBActions mysqlRegisteredUser = TableFactory.getTable(TableEnum.REGISTERED_USER);

	RegisteredUserTestData rutd = new RegisteredUserTestData();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mysqlRegisteredUser = TableFactory.getTable(TableEnum.REGISTERED_USER);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// mysqlRegisteredUser.dropTable();
	}

	@Before
	public void setUp() throws Exception {
		try {
			mysqlRegisteredUser.dropTable();
		} catch (Exception e) {
			// ignore if no table found
		}
		mysqlRegisteredUser.createTable();
	}

	private void setUpRegisteredUsers() {
		try {
			RegisteredUser registeredUser = rutd.setUpRegisteredUser1();
			mysqlRegisteredUser.create(registeredUser);
			registeredUser = rutd.setUpRegisteredUser2();
			mysqlRegisteredUser.create(registeredUser);
			registeredUser = rutd.setUpRegisteredUser3();
			mysqlRegisteredUser.create(registeredUser);
			registeredUser = rutd.setUpRegisteredUser4();
			mysqlRegisteredUser.create(registeredUser);
			registeredUser = rutd.setUpRegisteredUser5();
			mysqlRegisteredUser.create(registeredUser);
			registeredUser = rutd.setUpRegisteredUser6();
			mysqlRegisteredUser.create(registeredUser);
			registeredUser = rutd.setUpRegisteredUser7();
			mysqlRegisteredUser.create(registeredUser);
			registeredUser = rutd.setUpRegisteredUser8();
			mysqlRegisteredUser.create(registeredUser);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreate() {
		try {
			RegisteredUser registeredUser = rutd.setUpRegisteredUser3();

			RegisteredUserXml registeredUserXml = new RegisteredUserXml.RegisteredUserXmlBuilder()
					.authorityLevel(registeredUser.getAuthorityLevel()).userId(registeredUser.getUserId())
					.email(registeredUser.getEmail()).firstname(registeredUser.getFirstname())
					.lastname(registeredUser.getLastname()).password(registeredUser.getPassword())
					.telephone(registeredUser.getTelephone()).build();

			RegisteredUserMsgXml RegisteredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql RegisteredUserDaoSql = new RegisteredUserDaoSql(RegisteredUserMsg);
			RegisteredUserDaoSql.create();
			System.out.println(RegisteredUserMsg.getSuccess());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			setUpRegisteredUsers();
			RegisteredUser registeredUser = rutd.setUpRegisteredUser2();
			RegisteredUserXml registeredUserXml = new RegisteredUserXml.RegisteredUserXmlBuilder()
					.authorityLevel(registeredUser.getAuthorityLevel()).userId(registeredUser.getUserId())
					.email(registeredUser.getEmail()).firstname(registeredUser.getFirstname())
					.lastname(registeredUser.getLastname()).password(registeredUser.getPassword())
					.telephone(registeredUser.getTelephone()).build();
			RegisteredUserMsgXml RegisteredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql RegisteredUserDaoSql = new RegisteredUserDaoSql(RegisteredUserMsg);
			RegisteredUserDaoSql.delete();
			System.out.println(RegisteredUserMsg.getSuccess());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCount() {
		try {
			setUpRegisteredUsers();
			RegisteredUserXml registeredUserXml = new RegisteredUserXml();
			RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql registeredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			registeredUserDaoSql.getCount();
			System.out.println(registeredUserDaoSql.getCount());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRead() {
		try {
			setUpRegisteredUsers();
			RegisteredUserXml registeredUserXml = new RegisteredUserXml();
			registeredUserXml.setUserId("naiomi876");
			RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql registeredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			registeredUserMsg = (RegisteredUserMsgXml) registeredUserDaoSql.read();
			System.out.println(registeredUserMsg.getSuccess());
			ArrayList<RegisteredUserXml> lOfRegisteredUserXml = registeredUserMsg.getLOfRegisteredUsers();
			registeredUserXml = lOfRegisteredUserXml.get(0);

			assertEquals("read registered user failed to get authority level", "99",
					registeredUserXml.getAuthorityLevel());
			assertEquals("read registered user failed to get email", "naiomi@gmail.com", registeredUserXml.getEmail());
			assertEquals("read registered user failed to get first name", "naiomi", registeredUserXml.getFirstname());
			assertEquals("read registered user failed to get last name", "Smith", registeredUserXml.getLastname());
			assertEquals("read registered user failed to get password", "kelly1233", registeredUserXml.getPassword());
			assertEquals("read registered user failed to get telephone", "2222 5555555",
					registeredUserXml.getTelephone());
			assertEquals("read registered user failed to get user id", "naiomi876", registeredUserXml.getUserId());
			assertTrue("read registered failed crDate is null", null != registeredUserXml.getCrDate());
			assertTrue("read registered failed updDate is null", null != registeredUserXml.getUpdDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadAll() {
		try {
			setUpRegisteredUsers();
			RegisteredUserXml registeredUserXml = new RegisteredUserXml();
			RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql registeredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			registeredUserMsg = (RegisteredUserMsgXml) registeredUserDaoSql.readAll();
			System.out.println(registeredUserMsg.getSuccess());
			ArrayList<RegisteredUserXml> lOfRegisteredUserXml = registeredUserMsg.getLOfRegisteredUsers();
			System.out.println(lOfRegisteredUserXml.size());
			assertEquals("readAll failed expected 8 rows", 8, lOfRegisteredUserXml.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadByListOfKeys() {
		try {
			setUpRegisteredUsers();
			RegisteredUserXml registeredUserXml = new RegisteredUserXml();
			RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			ArrayList<RegisteredUserXml> lOfRegisteredUserXml = new ArrayList<RegisteredUserXml>();
			registeredUserXml = new RegisteredUserXml();
			registeredUserXml.setUserId("dave123");
			lOfRegisteredUserXml.add(registeredUserXml);
			registeredUserXml = new RegisteredUserXml();
			registeredUserXml.setUserId("rachel33");
			lOfRegisteredUserXml.add(registeredUserXml);
			registeredUserXml = new RegisteredUserXml();
			registeredUserXml.setUserId("Braffa");
			lOfRegisteredUserXml.add(registeredUserXml);
			registeredUserXml = new RegisteredUserXml();
			registeredUserXml.setUserId("georgie");
			lOfRegisteredUserXml.add(registeredUserXml);
			registeredUserMsg.setLOfRegisteredUsers(lOfRegisteredUserXml);
			RegisteredUserDaoSql registeredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			registeredUserMsg = (RegisteredUserMsgXml) registeredUserDaoSql.readListOfKeys();
			System.out.println(registeredUserMsg.getSuccess());
			ArrayList<RegisteredUserXml> lOfRegisteredUsers = registeredUserMsg.getLOfRegisteredUsers();
			assertEquals("testReadByListOfKeys wrong number of rows returned ", 5, lOfRegisteredUsers.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateAuthorityLevel() {
		try {
			setUpRegisteredUsers();
			RegisteredUser registeredUser = rutd.setUpRegisteredUser5();
			System.out.println(registeredUser.toString());
			RegisteredUserXml registeredUserXml = new RegisteredUserXml.RegisteredUserXmlBuilder().authorityLevel("66")
					.userId("Braffa").email("").firstname("").lastname("").password("").telephone("").build();
			RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql RegisteredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			RegisteredUserDaoSql.update();

			registeredUserXml = new RegisteredUserXml();
			registeredUserXml.setUserId("Braffa");
			registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql registeredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			registeredUserMsg = (RegisteredUserMsgXml) registeredUserDaoSql.read();
			System.out.println(registeredUserMsg.getSuccess());
			ArrayList<RegisteredUserXml> lOfRegisteredUserXml = registeredUserMsg.getLOfRegisteredUsers();
			registeredUserXml = lOfRegisteredUserXml.get(0);

			assertEquals("update registered user failed to get authority level", "66",
					registeredUserXml.getAuthorityLevel());
			assertEquals("update registered user failed to get email", "dave.rogers@yahoo.co.uk",
					registeredUserXml.getEmail());
			assertEquals("update registered user failed to get first name", "Dave", registeredUserXml.getFirstname());
			assertEquals("update registered user failed to get last name", "Rogers", registeredUserXml.getLastname());
			assertEquals("update registered user failed to get password", "kelly1233", registeredUserXml.getPassword());
			assertEquals("update registered user failed to get telephone", "01388 445566",
					registeredUserXml.getTelephone());
			assertEquals("update registered user failed to get user id", "Braffa", registeredUserXml.getUserId());
			assertTrue("update registered failed crDate is null", null != registeredUserXml.getCrDate());
			assertTrue("update registered failed updDate is null", null != registeredUserXml.getUpdDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateEmail() {
		try {
			setUpRegisteredUsers();
			RegisteredUser registeredUser = rutd.setUpRegisteredUser5();
			System.out.println(registeredUser.toString());
			RegisteredUserXml registeredUserXml = new RegisteredUserXml.RegisteredUserXmlBuilder().authorityLevel("")
					.userId("Braffa").email("mickymouse@bt.com").firstname("").lastname("").password("").telephone("").build();
			RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql RegisteredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			RegisteredUserDaoSql.update();

			registeredUserXml = new RegisteredUserXml();
			registeredUserXml.setUserId("Braffa");
			registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql registeredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			registeredUserMsg = (RegisteredUserMsgXml) registeredUserDaoSql.read();
			System.out.println(registeredUserMsg.getSuccess());
			ArrayList<RegisteredUserXml> lOfRegisteredUserXml = registeredUserMsg.getLOfRegisteredUsers();
			registeredUserXml = lOfRegisteredUserXml.get(0);

			assertEquals("update registered user failed to get authority level", "99",
					registeredUserXml.getAuthorityLevel());
			assertEquals("update registered user failed to get email", "mickymouse@bt.com",
					registeredUserXml.getEmail());
			assertEquals("update registered user failed to get first name", "Dave", registeredUserXml.getFirstname());
			assertEquals("update registered user failed to get last name", "Rogers", registeredUserXml.getLastname());
			assertEquals("update registered user failed to get password", "kelly1233", registeredUserXml.getPassword());
			assertEquals("update registered user failed to get telephone", "01388 445566",
					registeredUserXml.getTelephone());
			assertEquals("update registered user failed to get user id", "Braffa", registeredUserXml.getUserId());
			assertTrue("update registered failed crDate is null", null != registeredUserXml.getCrDate());
			assertTrue("update registered failed updDate is null", null != registeredUserXml.getUpdDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	@Test
	public void testUpdateFirstName() {
		try {
			setUpRegisteredUsers();
			RegisteredUser registeredUser = rutd.setUpRegisteredUser5();
			System.out.println(registeredUser.toString());
			RegisteredUserXml registeredUserXml = new RegisteredUserXml.RegisteredUserXmlBuilder().authorityLevel("")
					.userId("Braffa").email("").firstname("Arnold").lastname("").password("").telephone("").build();
			RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql RegisteredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			RegisteredUserDaoSql.update();

			registeredUserXml = new RegisteredUserXml();
			registeredUserXml.setUserId("Braffa");
			registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql registeredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			registeredUserMsg = (RegisteredUserMsgXml) registeredUserDaoSql.read();
			System.out.println(registeredUserMsg.getSuccess());
			ArrayList<RegisteredUserXml> lOfRegisteredUserXml = registeredUserMsg.getLOfRegisteredUsers();
			registeredUserXml = lOfRegisteredUserXml.get(0);

			assertEquals("update registered user failed to get authority level", "99",
					registeredUserXml.getAuthorityLevel());
			assertEquals("update registered user failed to get email", "dave.rogers@yahoo.co.uk",
					registeredUserXml.getEmail());
			assertEquals("update registered user failed to get first name", "Arnold", registeredUserXml.getFirstname());
			assertEquals("update registered user failed to get last name", "Rogers", registeredUserXml.getLastname());
			assertEquals("update registered user failed to get password", "kelly1233", registeredUserXml.getPassword());
			assertEquals("update registered user failed to get telephone", "01388 445566",
					registeredUserXml.getTelephone());
			assertEquals("update registered user failed to get user id", "Braffa", registeredUserXml.getUserId());
			assertTrue("update registered failed crDate is null", null != registeredUserXml.getCrDate());
			assertTrue("update registered failed updDate is null", null != registeredUserXml.getUpdDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateLastName() {
		try {
			setUpRegisteredUsers();
			RegisteredUser registeredUser = rutd.setUpRegisteredUser5();
			System.out.println(registeredUser.toString());
			
			RegisteredUserXml registeredUserXml = new RegisteredUserXml.RegisteredUserXmlBuilder().authorityLevel("")
					.userId("Braffa").email("").firstname("").lastname("Smithson").password("").telephone("").build();
			
			RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql RegisteredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			RegisteredUserDaoSql.update();

			registeredUserXml = new RegisteredUserXml();
			registeredUserXml.setUserId("Braffa");
			registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql registeredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			registeredUserMsg = (RegisteredUserMsgXml) registeredUserDaoSql.read();
			System.out.println(registeredUserMsg.getSuccess());
			ArrayList<RegisteredUserXml> lOfRegisteredUserXml = registeredUserMsg.getLOfRegisteredUsers();
			registeredUserXml = lOfRegisteredUserXml.get(0);

			assertEquals("update registered user failed to get authority level", "99",
					registeredUserXml.getAuthorityLevel());
			assertEquals("update registered user failed to get email", "dave.rogers@yahoo.co.uk",
					registeredUserXml.getEmail());
			assertEquals("update registered user failed to get first name", "Dave", registeredUserXml.getFirstname());
			assertEquals("update registered user failed to get last name", "Smithson", registeredUserXml.getLastname());
			assertEquals("update registered user failed to get password", "kelly1233", registeredUserXml.getPassword());
			assertEquals("update registered user failed to get telephone", "01388 445566",
					registeredUserXml.getTelephone());
			assertEquals("update registered user failed to get user id", "Braffa", registeredUserXml.getUserId());
			assertTrue("update registered failed crDate is null", null != registeredUserXml.getCrDate());
			assertTrue("update registered failed updDate is null", null != registeredUserXml.getUpdDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdatePassword() {
		try {
			setUpRegisteredUsers();
			RegisteredUser registeredUser = rutd.setUpRegisteredUser5();
			System.out.println(registeredUser.toString());
			
			RegisteredUserXml registeredUserXml = new RegisteredUserXml.RegisteredUserXmlBuilder().authorityLevel("")
					.userId("Braffa").email("").firstname("").lastname("").password("lindsay23").telephone("").build();
			
			RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql RegisteredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			RegisteredUserDaoSql.update();

			registeredUserXml = new RegisteredUserXml();
			registeredUserXml.setUserId("Braffa");
			registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql registeredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			registeredUserMsg = (RegisteredUserMsgXml) registeredUserDaoSql.read();
			System.out.println(registeredUserMsg.getSuccess());
			ArrayList<RegisteredUserXml> lOfRegisteredUserXml = registeredUserMsg.getLOfRegisteredUsers();
			registeredUserXml = lOfRegisteredUserXml.get(0);

			assertEquals("update registered user failed to get authority level", "99",
					registeredUserXml.getAuthorityLevel());
			assertEquals("update registered user failed to get email", "dave.rogers@yahoo.co.uk",
					registeredUserXml.getEmail());
			assertEquals("update registered user failed to get first name", "Dave", registeredUserXml.getFirstname());
			assertEquals("update registered user failed to get last name", "Rogers", registeredUserXml.getLastname());
			assertEquals("update registered user failed to get password", "lindsay23", registeredUserXml.getPassword());
			assertEquals("update registered user failed to get telephone", "01388 445566",
					registeredUserXml.getTelephone());
			assertEquals("update registered user failed to get user id", "Braffa", registeredUserXml.getUserId());
			assertTrue("update registered failed crDate is null", null != registeredUserXml.getCrDate());
			assertTrue("update registered failed updDate is null", null != registeredUserXml.getUpdDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateTelephone() {
		try {
			setUpRegisteredUsers();
			RegisteredUser registeredUser = rutd.setUpRegisteredUser5();
			System.out.println(registeredUser.toString());
			
			RegisteredUserXml registeredUserXml = new RegisteredUserXml.RegisteredUserXmlBuilder().authorityLevel("")
					.userId("Braffa").email("").firstname("").lastname("").password("").telephone("1234 56789123").build();
			
			RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql RegisteredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			RegisteredUserDaoSql.update();

			registeredUserXml = new RegisteredUserXml();
			registeredUserXml.setUserId("Braffa");
			registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);
			RegisteredUserDaoSql registeredUserDaoSql = new RegisteredUserDaoSql(registeredUserMsg);
			registeredUserMsg = (RegisteredUserMsgXml) registeredUserDaoSql.read();
			System.out.println(registeredUserMsg.getSuccess());
			ArrayList<RegisteredUserXml> lOfRegisteredUserXml = registeredUserMsg.getLOfRegisteredUsers();
			registeredUserXml = lOfRegisteredUserXml.get(0);

			assertEquals("update registered user failed to get authority level", "99",
					registeredUserXml.getAuthorityLevel());
			assertEquals("update registered user failed to get email", "dave.rogers@yahoo.co.uk",
					registeredUserXml.getEmail());
			assertEquals("update registered user failed to get first name", "Dave", registeredUserXml.getFirstname());
			assertEquals("update registered user failed to get last name", "Rogers", registeredUserXml.getLastname());
			assertEquals("update registered user failed to get password", "kelly1233", registeredUserXml.getPassword());
			assertEquals("update registered user failed to get telephone", "1234 56789123",
					registeredUserXml.getTelephone());
			assertEquals("update registered user failed to get user id", "Braffa", registeredUserXml.getUserId());
			assertTrue("update registered failed crDate is null", null != registeredUserXml.getCrDate());
			assertTrue("update registered failed updDate is null", null != registeredUserXml.getUpdDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
