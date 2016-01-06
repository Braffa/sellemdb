package com.braffa.sellem.mysql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class TestMysqlUserToProduct {

	private static IDBActions mySqUserToProduct;

	private UserToProductTestData utptd = new UserToProductTestData();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mySqUserToProduct = TableFactory.getTable(TableEnum.USER_TO_PRODUCT);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		mySqUserToProduct.dropTable();
	}

	@Before
	public void setUp() throws Exception {
		try {
			mySqUserToProduct.dropTable();
		} catch (Exception e) {
			// ignore if no table found
		}
		mySqUserToProduct.createTable();
	}

	@Test
	public void testSingleRow() {
		try {
			UserToProduct userToProduct = utptd.insertUserToProductTable1();
			mySqUserToProduct.create(userToProduct);
			List<UserToProduct> lOfUserToProductc = (List<UserToProduct>) mySqUserToProduct.read("georgie");
			assertTrue("Only one row was expected ", 1 == lOfUserToProductc.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setUpUserToProducts() throws SQLException {
		UserToProduct userToProduct = utptd.insertUserToProductTable1();
		mySqUserToProduct.create(userToProduct);
		userToProduct = utptd.insertUserToProductTable2();
		mySqUserToProduct.create(userToProduct);
		userToProduct = utptd.insertUserToProductTable3();
		mySqUserToProduct.create(userToProduct);
		userToProduct = utptd.insertUserToProductTable4();
		mySqUserToProduct.create(userToProduct);
	}

	@Test
	public void testMultipleRow() {
		try {
			setUpUserToProducts();
			List<UserToProduct> lOfUserToProductc = (List<UserToProduct>) mySqUserToProduct.readAll();
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
			List<UserToProduct> lOfUserToProductc = (List<UserToProduct>) mySqUserToProduct.readAll();
			assertTrue("Only one row was expected ", 4 == lOfUserToProductc.size());

			mySqUserToProduct.delete("georgie");

			lOfUserToProductc = (List<UserToProduct>) mySqUserToProduct.readAll();
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
			List<UserToProduct> lOfUserToProducts = (List<UserToProduct>) mySqUserToProduct.readAll();
			assertTrue("Only one row was expected ", 4 == lOfUserToProducts.size());

			UserToProduct UserToProduct = new UserToProduct.UserToProductBuilder().userId("georgie")
					.productId("9999999999999").productIndex("").crDate(new Date()).updDate(new Date()).build();
			
			mySqUserToProduct.update(UserToProduct);
			
			lOfUserToProducts = (List<UserToProduct>) mySqUserToProduct.read("georgie");
			assertEquals("Number of rows on database ", 2, lOfUserToProducts.size());
			
			UserToProduct userToProduct = lOfUserToProducts.get(0);
			assertEquals("product id should heav been updated ", "9999999999999", userToProduct.getProductId());
			
			userToProduct = lOfUserToProducts.get(1);
			assertEquals("product id should heav been updated ", "9999999999999", userToProduct.getProductId());
			
			UserToProduct = new UserToProduct.UserToProductBuilder().userId("georgie")
					.productId("").productIndex("9").crDate(new Date()).updDate(new Date()).build();
			
			mySqUserToProduct.update(UserToProduct);
			lOfUserToProducts = (List<UserToProduct>) mySqUserToProduct.read("georgie");
			
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
