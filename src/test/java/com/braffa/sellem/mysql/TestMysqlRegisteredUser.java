package com.braffa.sellem.mysql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.braffa.sellem.datafortesting.RegisteredUserTestData;
import com.braffa.sellem.model.sql.RegisteredUser;
import com.braffa.sellem.tables.IDBActions;
import com.braffa.sellem.tables.TableEnum;
import com.braffa.sellem.tables.TableFactory;

public class TestMysqlRegisteredUser {

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

	@Test
	public void testSingleRow() {
		try {
			RegisteredUser registeredUser = rutd.setUpRegisteredUser1();
			mySqlRegisteredUser.create(registeredUser);

			List<RegisteredUser> lOfRegisteredUser = (List<RegisteredUser>) mySqlRegisteredUser.read("dave123");
			assertTrue("Only one row was expected ", 1 == lOfRegisteredUser.size());
			assertEquals("Incorrect AuthorityLevel ", "99", lOfRegisteredUser.get(0).getAuthorityLevel());
			assertEquals("Incorrect username ", "dave123", lOfRegisteredUser.get(0).getUserId());
			assertEquals("Incorrect email ", "dave@gmail.com", lOfRegisteredUser.get(0).getEmail());
			assertEquals("Incorrect firstname ", "Dave", lOfRegisteredUser.get(0).getFirstname());
			assertEquals("Incorrect lastname ", "Allen", lOfRegisteredUser.get(0).getLastname());
			assertEquals("Incorrect password ", "kelly1233", lOfRegisteredUser.get(0).getPassword());
			assertEquals("Incorrect telephone ", "0772 234654", lOfRegisteredUser.get(0).getTelephone());
			System.out.println(lOfRegisteredUser.get(0).toString());
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Failure");
		}
	}

	private void setUpRegisteredUsers() throws SQLException {
		RegisteredUser registeredUser = rutd.setUpRegisteredUser1();
		mySqlRegisteredUser.create(registeredUser);
		registeredUser = rutd.setUpRegisteredUser2();
		mySqlRegisteredUser.create(registeredUser);
		registeredUser = rutd.setUpRegisteredUser3();
		mySqlRegisteredUser.create(registeredUser);
		registeredUser = rutd.setUpRegisteredUser4();
		mySqlRegisteredUser.create(registeredUser);
	}

	@Test
	public void testMultipleRow() {
		try {
			setUpRegisteredUsers();
			List<RegisteredUser> lOfRegisteredUser = (List<RegisteredUser>) mySqlRegisteredUser.readAll();
			assertTrue("four rows were expected ", 4 == lOfRegisteredUser.size());

			assertEquals("Incorrect AuthorityLevel ", "99", lOfRegisteredUser.get(0).getAuthorityLevel());
			assertEquals("Incorrect username ", "dave123", lOfRegisteredUser.get(0).getUserId());
			assertEquals("Incorrect email ", "dave@gmail.com", lOfRegisteredUser.get(0).getEmail());
			assertEquals("Incorrect firstname ", "Dave", lOfRegisteredUser.get(0).getFirstname());
			assertEquals("Incorrect lastname ", "Allen", lOfRegisteredUser.get(0).getLastname());
			assertEquals("Incorrect password ", "kelly1233", lOfRegisteredUser.get(0).getPassword());
			assertEquals("Incorrect telephone ", "0772 234654", lOfRegisteredUser.get(0).getTelephone());

			assertEquals("Incorrect AuthorityLevel ", "99", lOfRegisteredUser.get(0).getAuthorityLevel());
			assertEquals("Incorrect username ", "gordon456", lOfRegisteredUser.get(1).getUserId());
			assertEquals("Incorrect email ", "gordon@gmail.com", lOfRegisteredUser.get(1).getEmail());
			assertEquals("Incorrect firstname ", "gordon", lOfRegisteredUser.get(1).getFirstname());
			assertEquals("Incorrect lastname ", "Mills", lOfRegisteredUser.get(1).getLastname());
			assertEquals("Incorrect password ", "kelly1233", lOfRegisteredUser.get(0).getPassword());
			assertEquals("Incorrect telephone ", "1111 4444444", lOfRegisteredUser.get(1).getTelephone());

			assertEquals("Incorrect AuthorityLevel ", "99", lOfRegisteredUser.get(0).getAuthorityLevel());
			assertEquals("Incorrect username ", "naiomi876", lOfRegisteredUser.get(2).getUserId());
			assertEquals("Incorrect email ", "naiomi@gmail.com", lOfRegisteredUser.get(2).getEmail());
			assertEquals("Incorrect firstname ", "naiomi", lOfRegisteredUser.get(2).getFirstname());
			assertEquals("Incorrect lastname ", "Smith", lOfRegisteredUser.get(2).getLastname());
			assertEquals("Incorrect password ", "kelly1233", lOfRegisteredUser.get(0).getPassword());
			assertEquals("Incorrect telephone ", "2222 5555555", lOfRegisteredUser.get(2).getTelephone());

			assertEquals("Incorrect AuthorityLevel ", "99", lOfRegisteredUser.get(0).getAuthorityLevel());
			assertEquals("Incorrect username ", "rachel33", lOfRegisteredUser.get(3).getUserId());
			assertEquals("Incorrect email ", "rachel@gmail.com", lOfRegisteredUser.get(3).getEmail());
			assertEquals("Incorrect firstname ", "rachel", lOfRegisteredUser.get(3).getFirstname());
			assertEquals("Incorrect lastname ", "scott", lOfRegisteredUser.get(3).getLastname());
			assertEquals("Incorrect password ", "kelly1233", lOfRegisteredUser.get(0).getPassword());
			assertEquals("Incorrect telephone ", "3333 6666666", lOfRegisteredUser.get(3).getTelephone());

		} catch (SQLException e) {
			e.printStackTrace();
			fail("Failure");
		}
	}

	@Test
	public void testDelete() {
		try {
			setUpRegisteredUsers();
			List<RegisteredUser> lOfRegisteredUser = (List<RegisteredUser>) mySqlRegisteredUser.readAll();
			assertTrue("four rows were expected ", 4 == lOfRegisteredUser.size());

			mySqlRegisteredUser.delete("naiomi876");

			lOfRegisteredUser = (List<RegisteredUser>) mySqlRegisteredUser.readAll();
			assertTrue("three rows were expected ", 3 == lOfRegisteredUser.size());

			assertEquals("Incorrect AuthorityLevel ", "99", lOfRegisteredUser.get(0).getAuthorityLevel());
			assertEquals("Incorrect username ", "dave123", lOfRegisteredUser.get(0).getUserId());
			assertEquals("Incorrect email ", "dave@gmail.com", lOfRegisteredUser.get(0).getEmail());
			assertEquals("Incorrect firstname ", "Dave", lOfRegisteredUser.get(0).getFirstname());
			assertEquals("Incorrect lastname ", "Allen", lOfRegisteredUser.get(0).getLastname());
			assertEquals("Incorrect password ", "kelly1233", lOfRegisteredUser.get(0).getPassword());
			assertEquals("Incorrect telephone ", "0772 234654", lOfRegisteredUser.get(0).getTelephone());

			assertEquals("Incorrect AuthorityLevel ", "99", lOfRegisteredUser.get(0).getAuthorityLevel());
			assertEquals("Incorrect username ", "gordon456", lOfRegisteredUser.get(1).getUserId());
			assertEquals("Incorrect email ", "gordon@gmail.com", lOfRegisteredUser.get(1).getEmail());
			assertEquals("Incorrect firstname ", "gordon", lOfRegisteredUser.get(1).getFirstname());
			assertEquals("Incorrect lastname ", "Mills", lOfRegisteredUser.get(1).getLastname());
			assertEquals("Incorrect password ", "kelly1233", lOfRegisteredUser.get(0).getPassword());
			assertEquals("Incorrect telephone ", "1111 4444444", lOfRegisteredUser.get(1).getTelephone());

			assertEquals("Incorrect AuthorityLevel ", "99", lOfRegisteredUser.get(0).getAuthorityLevel());
			assertEquals("Incorrect username ", "rachel33", lOfRegisteredUser.get(2).getUserId());
			assertEquals("Incorrect email ", "rachel@gmail.com", lOfRegisteredUser.get(2).getEmail());
			assertEquals("Incorrect firstname ", "rachel", lOfRegisteredUser.get(2).getFirstname());
			assertEquals("Incorrect lastname ", "scott", lOfRegisteredUser.get(2).getLastname());
			assertEquals("Incorrect password ", "kelly1233", lOfRegisteredUser.get(0).getPassword());
			assertEquals("Incorrect telephone ", "3333 6666666", lOfRegisteredUser.get(2).getTelephone());

		} catch (SQLException e) {
			e.printStackTrace();
			fail("Failure");
		}
	}

	@Test
	public void testUpdate() {
		try {
			setUpRegisteredUsers();
			List<RegisteredUser> lOfRegisteredUser = (List<RegisteredUser>) mySqlRegisteredUser.readAll();
			assertTrue("four rows were expected ", 4 == lOfRegisteredUser.size());

			RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("")
					.userId("dave123").email("dave@yahoo.co.uk").firstname("").lastname("").password("").telephone("")
					.build();

			mySqlRegisteredUser.update(registeredUser);

			lOfRegisteredUser = (List<RegisteredUser>) mySqlRegisteredUser.read("dave123");
			assertTrue("Only one row was expected ", 1 == lOfRegisteredUser.size());
			assertEquals("Incorrect username ", "dave123", lOfRegisteredUser.get(0).getUserId());
			assertEquals("Incorrect email ", "dave@yahoo.co.uk", lOfRegisteredUser.get(0).getEmail());
			assertEquals("Incorrect firstname ", "Dave", lOfRegisteredUser.get(0).getFirstname());
			assertEquals("Incorrect lastname ", "Allen", lOfRegisteredUser.get(0).getLastname());
			assertEquals("Incorrect telephone ", "0772 234654", lOfRegisteredUser.get(0).getTelephone());
			
			registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("")
					.userId("dave123").email("").firstname("Roger").lastname("").password("").telephone("")
					.build();

			mySqlRegisteredUser.update(registeredUser);
			lOfRegisteredUser = (List<RegisteredUser>) mySqlRegisteredUser.read("dave123");

			assertTrue("Only one row was expected ", 1 == lOfRegisteredUser.size());
			assertEquals("Incorrect username ", "dave123", lOfRegisteredUser.get(0).getUserId());
			assertEquals("Incorrect email ", "dave@yahoo.co.uk", lOfRegisteredUser.get(0).getEmail());
			assertEquals("Incorrect firstname ", "Roger", lOfRegisteredUser.get(0).getFirstname());
			assertEquals("Incorrect lastname ", "Allen", lOfRegisteredUser.get(0).getLastname());
			assertEquals("Incorrect telephone ", "0772 234654", lOfRegisteredUser.get(0).getTelephone());
			
			registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("")
					.userId("dave123").email("").firstname("").lastname("Johnson").password("").telephone("")
					.build();

			mySqlRegisteredUser.update(registeredUser);
			lOfRegisteredUser = (List<RegisteredUser>) mySqlRegisteredUser.read("dave123");

			assertTrue("Only one row was expected ", 1 == lOfRegisteredUser.size());
			assertEquals("Incorrect username ", "dave123", lOfRegisteredUser.get(0).getUserId());
			assertEquals("Incorrect email ", "dave@yahoo.co.uk", lOfRegisteredUser.get(0).getEmail());
			assertEquals("Incorrect firstname ", "Roger", lOfRegisteredUser.get(0).getFirstname());
			assertEquals("Incorrect lastname ", "Johnson", lOfRegisteredUser.get(0).getLastname());
			assertEquals("Incorrect telephone ", "0772 234654", lOfRegisteredUser.get(0).getTelephone());
			
			registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("")
					.userId("dave123").email("").firstname("").lastname("").password("").telephone("9999 999999")
					.build();

			mySqlRegisteredUser.update(registeredUser);

			lOfRegisteredUser = (List<RegisteredUser>) mySqlRegisteredUser.read("dave123");

			assertTrue("Only one row was expected ", 1 == lOfRegisteredUser.size());
			assertEquals("Incorrect username ", "dave123", lOfRegisteredUser.get(0).getUserId());
			assertEquals("Incorrect email ", "dave@yahoo.co.uk", lOfRegisteredUser.get(0).getEmail());
			assertEquals("Incorrect firstname ", "Roger", lOfRegisteredUser.get(0).getFirstname());
			assertEquals("Incorrect lastname ", "Johnson", lOfRegisteredUser.get(0).getLastname());
			assertEquals("Incorrect telephone ", "9999 999999", lOfRegisteredUser.get(0).getTelephone());
			
			registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("01")
					.userId("naiomi876").email("susan@gmail.com").firstname("susan").lastname("jones").password("willywonka").telephone("9999 999999")
					.build();

			mySqlRegisteredUser.update(registeredUser);

			lOfRegisteredUser = (List<RegisteredUser>) mySqlRegisteredUser.read("naiomi876");
			assertTrue("Only one row was expected ", 1 == lOfRegisteredUser.size());
			assertEquals("Incorrect username ", "naiomi876", lOfRegisteredUser.get(0).getUserId());
			assertEquals("Incorrect email ", "susan@gmail.com", lOfRegisteredUser.get(0).getEmail());
			assertEquals("Incorrect firstname ", "susan", lOfRegisteredUser.get(0).getFirstname());
			assertEquals("Incorrect lastname ", "jones", lOfRegisteredUser.get(0).getLastname());
			assertEquals("Incorrect telephone ", "9999 999999", lOfRegisteredUser.get(0).getTelephone());

		} catch (SQLException e) {
			e.printStackTrace();
			fail("Failure");
		}
	}
}
