package com.braffa.sellem.hsql;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.braffa.sellem.datafortesting.UserToProductTestData;
import com.braffa.sellem.model.sql.UserToProduct;
import com.braffa.sellem.tables.IDBActions;
import com.braffa.sellem.tables.TableEnum;
import com.braffa.sellem.tables.TableFactory;

public class TestHsqlUserToProduct {

	private static IDBActions hsqlUserToProduct;

	private UserToProductTestData utptd = new UserToProductTestData();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		hsqlUserToProduct = TableFactory.getTable(TableEnum.USER_TO_PRODUCT_HSQL);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		hsqlUserToProduct.dropTable();
	}

	@Before
	public void setUp() throws Exception {
		try {
			hsqlUserToProduct.dropTable();
		} catch (Exception e) {
			// ignore if no table found
		}
		hsqlUserToProduct.createTable();
	}

	@Test
	public void testSingleRow() {
		try {
			UserToProduct userToProduct = utptd.insertUserToProductTable1();
			hsqlUserToProduct.create(userToProduct);
			List<UserToProduct> lOfUserToProductc = (List<UserToProduct>) hsqlUserToProduct.read(userToProduct);
			assertTrue("Only one row was expected ", 1 == lOfUserToProductc.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	private void setUpUserToProducts() throws SQLException {
		UserToProduct userToProduct = utptd.insertUserToProductTable1();
		hsqlUserToProduct.create(userToProduct);
		userToProduct = utptd.insertUserToProductTable2();
		hsqlUserToProduct.create(userToProduct);
		userToProduct = utptd.insertUserToProductTable3();
		hsqlUserToProduct.create(userToProduct);
		userToProduct = utptd.insertUserToProductTable4();
		hsqlUserToProduct.create(userToProduct);
	}

	@Test
	public void testMultipleRow() {
		try {
			setUpUserToProducts();
			List<UserToProduct> lOfUserToProductc = (List<UserToProduct>) hsqlUserToProduct.readAll();
			assertTrue("Only one row was expected ", 4 == lOfUserToProductc.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			setUpUserToProducts();
			List<UserToProduct> lOfUserToProductc = (List<UserToProduct>) hsqlUserToProduct.readAll();
			assertTrue("Only one row was expected ", 4 == lOfUserToProductc.size());

			hsqlUserToProduct.delete("georgie");

			lOfUserToProductc = (List<UserToProduct>) hsqlUserToProduct.readAll();
			assertEquals("Number of rows on dfatabase ", 2, lOfUserToProductc.size());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		try {
			setUpUserToProducts();
			List<UserToProduct> lOfUserToProducts = (List<UserToProduct>) hsqlUserToProduct.readAll();
			assertTrue("Only one row was expected ", 4 == lOfUserToProducts.size());

			UserToProduct UserToProduct = new UserToProduct.UserToProductBuilder().userId("georgie")
					.productId("9999999999999").productIndex("").crDate(new Date()).updDate(new Date()).build();

			hsqlUserToProduct.update(UserToProduct);

			lOfUserToProducts = (List<UserToProduct>) hsqlUserToProduct.read("georgie");
			assertEquals("Number of rows on database ", 2, lOfUserToProducts.size());

			UserToProduct userToProduct = lOfUserToProducts.get(0);
			assertEquals("product id should heav been updated ", "9999999999999", userToProduct.getProductId());

			userToProduct = lOfUserToProducts.get(1);
			assertEquals("product id should heav been updated ", "9999999999999", userToProduct.getProductId());

			UserToProduct = new UserToProduct.UserToProductBuilder().userId("georgie").productId("").productIndex("9")
					.crDate(new Date()).updDate(new Date()).build();

			hsqlUserToProduct.update(UserToProduct);
			lOfUserToProducts = (List<UserToProduct>) hsqlUserToProduct.read("georgie");

			userToProduct = lOfUserToProducts.get(0);

			assertEquals("product index should heav been updated ", "9", userToProduct.getProductIndex());

			userToProduct = lOfUserToProducts.get(1);
			assertEquals("product index should heav been updated ", "9", userToProduct.getProductIndex());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
