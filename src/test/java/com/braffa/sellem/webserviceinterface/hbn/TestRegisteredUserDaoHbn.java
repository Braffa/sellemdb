package com.braffa.sellem.webserviceinterface.hbn;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.braffa.sellem.datafortesting.RegisteredUserTestData;
import com.braffa.sellem.hbn.Dao;
import com.braffa.sellem.hbn.DaoFactory;
import com.braffa.sellem.hbn.DaoFactory.daoType;
import com.braffa.sellem.model.sql.RegisteredUser;
import com.braffa.sellem.model.xml.RegisteredUserMsgXml;
import com.braffa.sellem.model.xml.RegisteredUserXml;
import com.braffa.sellem.tables.IDBActions;
import com.braffa.sellem.tables.TableEnum;
import com.braffa.sellem.tables.TableFactory;

public class TestRegisteredUserDaoHbn {

	private static IDBActions mySqlRegisteredUser;

	private RegisteredUserTestData rutd = new RegisteredUserTestData();

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

	private void setUpRegisteredUsers() {
		try {
			RegisteredUser registeredUser = rutd.setUpRegisteredUser1();
			mySqlRegisteredUser.create(registeredUser);
			registeredUser = rutd.setUpRegisteredUser2();
			mySqlRegisteredUser.create(registeredUser);
			registeredUser = rutd.setUpRegisteredUser3();
			mySqlRegisteredUser.create(registeredUser);
			registeredUser = rutd.setUpRegisteredUser4();
			mySqlRegisteredUser.create(registeredUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateRowFromXmlObject() {
		setUpRegisteredUsers();
		RegisteredUserXml RegisteredUserXml = new RegisteredUserXml.RegisteredUserXmlBuilder().authorityLevel("22")
				.userId("donaldduck").email("donald@gmail.com").firstname("donny").lastname("Duck")
				.password("mickeymouse").telephone("8888 999999").build();

		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(RegisteredUserXml);
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);
		registeredUserMsg = (RegisteredUserMsgXml) registeredUserDao.create();
		System.out.println(registeredUserMsg.getSuccess());
	}

	@Test
	public void testCreateRowFromSqlObject() {
		setUpRegisteredUsers();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("33")
				.userId("bettyboops").email("betty@gmail.com").firstname("betty").lastname("boops").password("betty567")
				.telephone("333 666666").build();
		RegisteredUserXml RegisteredUserXml = new RegisteredUserXml(registeredUser);
		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(RegisteredUserXml);

		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);

		registeredUserMsg = (RegisteredUserMsgXml) registeredUserDao.create();
		System.out.println(registeredUserMsg.getSuccess());
	}

	@Test
	public void testDeleteRowFromSqlObject() {
		setUpRegisteredUsers();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("").userId("georgie")
				.email("").firstname("").lastname("").password("").telephone("").build();
		RegisteredUserXml RegisteredUserXml = new RegisteredUserXml(registeredUser);
		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(RegisteredUserXml);

		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);
		registeredUserMsg = (RegisteredUserMsgXml) registeredUserDao.delete();
		System.out.println(registeredUserMsg.getSuccess());
	}

	@Test
	public void testCount() {
		setUpRegisteredUsers();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("").userId("")
				.email("").firstname("").lastname("").password("").telephone("").build();
		RegisteredUserXml RegisteredUserXml = new RegisteredUserXml(registeredUser);
		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(RegisteredUserXml);
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);
		int count = registeredUserDao.getCount();
		assertEquals("getCountTest failedIncorrect number of rows ", 4, count);
	}

	@Test
	public void testRead() {
		setUpRegisteredUsers();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("").userId("dave123")
				.email("").firstname("").lastname("").password("").telephone("").build();
		RegisteredUserXml RegisteredUserXml = new RegisteredUserXml(registeredUser);
		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(RegisteredUserXml);
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);
		registeredUserMsg = (RegisteredUserMsgXml) registeredUserDao.read();

		ArrayList<RegisteredUserXml> lOfRegisteredUsers = registeredUserMsg.getLOfRegisteredUsers();
		RegisteredUserXml registeredUserXml = lOfRegisteredUsers.get(0);
		System.out.println(registeredUserXml.toString());

		assertEquals("read failed - authorityLevel incorrect ", "99", registeredUserXml.getAuthorityLevel());
		assertEquals("read failed - email incorrect ", "dave@gmail.com", registeredUserXml.getEmail());
		assertEquals("read failed - firstname incorrect ", "Dave", registeredUserXml.getFirstname());
		assertEquals("read failed - lastname incorrect ", "Allen", registeredUserXml.getLastname());
		assertEquals("read failed - password incorrect ", "kelly1233", registeredUserXml.getPassword());
		assertEquals("read failed - telephone incorrect ", "0772 234654", registeredUserXml.getTelephone());
		assertEquals("read failed - userId incorrect ", "dave123", registeredUserXml.getUserId());
	}

	@Test
	public void testReadAll() {
		setUpRegisteredUsers();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("").userId("dave123")
				.email("").firstname("").lastname("").password("").telephone("").build();
		RegisteredUserXml RegisteredUserXml = new RegisteredUserXml(registeredUser);
		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(RegisteredUserXml);
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);

		registeredUserMsg = (RegisteredUserMsgXml) registeredUserDao.readAll();
		ArrayList<RegisteredUserXml> lOfRegisteredUsers = registeredUserMsg.getLOfRegisteredUsers();

		assertEquals("read All failed - expected  4 rows ", 4, lOfRegisteredUsers.size());
	}

	@Test
	public void testReadListOfKeys() {
		setUpRegisteredUsers();
		ArrayList<RegisteredUserXml> lOfRegisteredUsers = new ArrayList<RegisteredUserXml>();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("").userId("dave123")
				.email("").firstname("").lastname("").password("").telephone("").build();
		RegisteredUserXml registeredUserXml = new RegisteredUserXml(registeredUser);
		lOfRegisteredUsers.add(registeredUserXml);

		registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("").userId("rachel33").email("")
				.firstname("").lastname("").password("").telephone("").build();
		registeredUserXml = new RegisteredUserXml(registeredUser);
		lOfRegisteredUsers.add(registeredUserXml);

		registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("").userId("gordon456").email("")
				.firstname("").lastname("").password("").telephone("").build();
		registeredUserXml = new RegisteredUserXml(registeredUser);
		lOfRegisteredUsers.add(registeredUserXml);

		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml();
		registeredUserMsg.setLOfRegisteredUsers(lOfRegisteredUsers);
		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);
		registeredUserMsg = (RegisteredUserMsgXml) registeredUserDao.readListOfKeys();

		assertEquals("readListOfKeys failed Incorrect number of rows ", 3,
				registeredUserMsg.getLOfRegisteredUsers().size());

		lOfRegisteredUsers = registeredUserMsg.getLOfRegisteredUsers();
		registeredUserXml = lOfRegisteredUsers.get(0);

		System.out.println(registeredUserXml.toString());

		assertEquals("read failed - authorityLevel incorrect ", "99", registeredUserXml.getAuthorityLevel());
		assertEquals("read failed - email incorrect ", "dave@gmail.com", registeredUserXml.getEmail());
		assertEquals("read failed - firstname incorrect ", "Dave", registeredUserXml.getFirstname());
		assertEquals("read failed - lastname incorrect ", "Allen", registeredUserXml.getLastname());
		assertEquals("read failed - password incorrect ", "kelly1233", registeredUserXml.getPassword());
		assertEquals("read failed - telephone incorrect ", "0772 234654", registeredUserXml.getTelephone());
		assertEquals("read failed - userId incorrect ", "dave123", registeredUserXml.getUserId());
	}

	@Test
	public void testUpdateAuthorityLevell() {
		setUpRegisteredUsers();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("22")
				.userId("dave123").email("").firstname("").lastname("").password("").telephone("").build();
		RegisteredUserXml registeredUserXml = new RegisteredUserXml(registeredUser);
		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);

		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);
		registeredUserMsg = (RegisteredUserMsgXml) registeredUserDao.update();

		ArrayList<RegisteredUserXml> lOfRegisteredUsers = registeredUserMsg.getLOfRegisteredUsers();
		registeredUserXml = lOfRegisteredUsers.get(0);
		System.out.println(registeredUserXml.toString());

		assertEquals("read failed - authorityLevel incorrect ", "22", registeredUserXml.getAuthorityLevel());
		assertEquals("read failed - email incorrect ", "dave@gmail.com", registeredUserXml.getEmail());
		assertEquals("read failed - firstname incorrect ", "Dave", registeredUserXml.getFirstname());
		assertEquals("read failed - lastname incorrect ", "Allen", registeredUserXml.getLastname());
		assertEquals("read failed - password incorrect ", "kelly1233", registeredUserXml.getPassword());
		assertEquals("read failed - telephone incorrect ", "0772 234654", registeredUserXml.getTelephone());
		assertEquals("read failed - userId incorrect ", "dave123", registeredUserXml.getUserId());
	}

	@Test
	public void testUpdateEmail() {
		setUpRegisteredUsers();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("").userId("dave123")
				.email("braffa@yahoo.co.uk").firstname("").lastname("").password("").telephone("").build();
		RegisteredUserXml registeredUserXml = new RegisteredUserXml(registeredUser);
		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);

		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);
		registeredUserMsg = (RegisteredUserMsgXml) registeredUserDao.update();

		ArrayList<RegisteredUserXml> lOfRegisteredUsers = registeredUserMsg.getLOfRegisteredUsers();
		registeredUserXml = lOfRegisteredUsers.get(0);
		System.out.println(registeredUserXml.toString());

		assertEquals("read failed - authorityLevel incorrect ", "99", registeredUserXml.getAuthorityLevel());
		assertEquals("read failed - email incorrect ", "braffa@yahoo.co.uk", registeredUserXml.getEmail());
		assertEquals("read failed - firstname incorrect ", "Dave", registeredUserXml.getFirstname());
		assertEquals("read failed - lastname incorrect ", "Allen", registeredUserXml.getLastname());
		assertEquals("read failed - password incorrect ", "kelly1233", registeredUserXml.getPassword());
		assertEquals("read failed - telephone incorrect ", "0772 234654", registeredUserXml.getTelephone());
		assertEquals("read failed - userId incorrect ", "dave123", registeredUserXml.getUserId());
	}

	@Test
	public void testUpdateFirstname() {
		setUpRegisteredUsers();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("").userId("dave123")
				.email("").firstname("Stephen").lastname("").password("").telephone("").build();
		RegisteredUserXml registeredUserXml = new RegisteredUserXml(registeredUser);
		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);

		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);
		registeredUserMsg = (RegisteredUserMsgXml) registeredUserDao.update();

		ArrayList<RegisteredUserXml> lOfRegisteredUsers = registeredUserMsg.getLOfRegisteredUsers();
		registeredUserXml = lOfRegisteredUsers.get(0);
		System.out.println(registeredUserXml.toString());

		assertEquals("read failed - authorityLevel incorrect ", "99", registeredUserXml.getAuthorityLevel());
		assertEquals("read failed - email incorrect ", "dave@gmail.com", registeredUserXml.getEmail());
		assertEquals("read failed - firstname incorrect ", "Stephen", registeredUserXml.getFirstname());
		assertEquals("read failed - lastname incorrect ", "Allen", registeredUserXml.getLastname());
		assertEquals("read failed - password incorrect ", "kelly1233", registeredUserXml.getPassword());
		assertEquals("read failed - telephone incorrect ", "0772 234654", registeredUserXml.getTelephone());
		assertEquals("read failed - userId incorrect ", "dave123", registeredUserXml.getUserId());
	}

	@Test
	public void testUpdateLastname() {
		setUpRegisteredUsers();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("").userId("dave123")
				.email("").firstname("").lastname("Thorburn").password("").telephone("").build();
		RegisteredUserXml registeredUserXml = new RegisteredUserXml(registeredUser);
		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);

		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);
		registeredUserMsg = (RegisteredUserMsgXml) registeredUserDao.update();

		ArrayList<RegisteredUserXml> lOfRegisteredUsers = registeredUserMsg.getLOfRegisteredUsers();
		registeredUserXml = lOfRegisteredUsers.get(0);
		System.out.println(registeredUserXml.toString());

		assertEquals("read failed - authorityLevel incorrect ", "99", registeredUserXml.getAuthorityLevel());
		assertEquals("read failed - email incorrect ", "dave@gmail.com", registeredUserXml.getEmail());
		assertEquals("read failed - firstname incorrect ", "Dave", registeredUserXml.getFirstname());
		assertEquals("read failed - lastname incorrect ", "Thorburn", registeredUserXml.getLastname());
		assertEquals("read failed - password incorrect ", "kelly1233", registeredUserXml.getPassword());
		assertEquals("read failed - telephone incorrect ", "0772 234654", registeredUserXml.getTelephone());
		assertEquals("read failed - userId incorrect ", "dave123", registeredUserXml.getUserId());
	}

	@Test
	public void testUpdatePassword() {
		setUpRegisteredUsers();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("").userId("dave123")
				.email("").firstname("").lastname("").password("tester").telephone("").build();
		RegisteredUserXml registeredUserXml = new RegisteredUserXml(registeredUser);
		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);

		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);
		registeredUserMsg = (RegisteredUserMsgXml) registeredUserDao.update();

		ArrayList<RegisteredUserXml> lOfRegisteredUsers = registeredUserMsg.getLOfRegisteredUsers();
		registeredUserXml = lOfRegisteredUsers.get(0);
		System.out.println(registeredUserXml.toString());

		assertEquals("read failed - authorityLevel incorrect ", "99", registeredUserXml.getAuthorityLevel());
		assertEquals("read failed - email incorrect ", "dave@gmail.com", registeredUserXml.getEmail());
		assertEquals("read failed - firstname incorrect ", "Dave", registeredUserXml.getFirstname());
		assertEquals("read failed - lastname incorrect ", "Allen", registeredUserXml.getLastname());
		assertEquals("read failed - password incorrect ", "tester", registeredUserXml.getPassword());
		assertEquals("read failed - telephone incorrect ", "0772 234654", registeredUserXml.getTelephone());
		assertEquals("read failed - userId incorrect ", "dave123", registeredUserXml.getUserId());
	}

	@Test
	public void testUpdateTelephone() {
		setUpRegisteredUsers();
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("").userId("dave123")
				.email("").firstname("").lastname("").password("").telephone("1234 123456").build();
		RegisteredUserXml registeredUserXml = new RegisteredUserXml(registeredUser);
		RegisteredUserMsgXml registeredUserMsg = new RegisteredUserMsgXml(registeredUserXml);

		Dao registeredUserDao = DaoFactory.getDAO(daoType.REGISTERED_USER_DAO, registeredUserMsg);
		registeredUserMsg = (RegisteredUserMsgXml) registeredUserDao.update();

		ArrayList<RegisteredUserXml> lOfRegisteredUsers = registeredUserMsg.getLOfRegisteredUsers();
		registeredUserXml = lOfRegisteredUsers.get(0);
		System.out.println(registeredUserXml.toString());

		assertEquals("read failed - authorityLevel incorrect ", "99", registeredUserXml.getAuthorityLevel());
		assertEquals("read failed - email incorrect ", "dave@gmail.com", registeredUserXml.getEmail());
		assertEquals("read failed - firstname incorrect ", "Dave", registeredUserXml.getFirstname());
		assertEquals("read failed - lastname incorrect ", "Allen", registeredUserXml.getLastname());
		assertEquals("read failed - password incorrect ", "kelly1233", registeredUserXml.getPassword());
		assertEquals("read failed - telephone incorrect ", "1234 123456", registeredUserXml.getTelephone());
		assertEquals("read failed - userId incorrect ", "dave123", registeredUserXml.getUserId());
	}

}
