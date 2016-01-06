package com.braffa.sellem.webserviceinterface.hbn;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.braffa.sellem.datafortesting.ProductTestData;
import com.braffa.sellem.datafortesting.UserToProductTestData;
import com.braffa.sellem.model.sql.Product;
import com.braffa.sellem.model.sql.UserToProduct;
import com.braffa.sellem.model.xml.ProductMsgXml;
import com.braffa.sellem.model.xml.ProductXml;
import com.braffa.sellem.model.xml.UserToProductMsgXml;
import com.braffa.sellem.model.xml.UserToProductXml;
import com.braffa.sellem.tables.IDBActions;
import com.braffa.sellem.tables.TableEnum;
import com.braffa.sellem.tables.TableFactory;

public class TestUserToProductDaoHbn {

	private static IDBActions mysqlUserToProduct;

	private UserToProductTestData utptd = new UserToProductTestData();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mysqlUserToProduct = TableFactory.getTable(TableEnum.USER_TO_PRODUCT);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// mysqlUserToProduct.dropTable();
	}

	@Before
	public void setUp() throws Exception {
		try {
			mysqlUserToProduct.dropTable();
		} catch (Exception e) {
			// ignore if no table found
		}
		mysqlUserToProduct.createTable();
	}

	private void setUpUserToProducts() {
		try {
			UserToProduct userToProduct = utptd.insertUserToProductTable1();
			mysqlUserToProduct.create(userToProduct);
			userToProduct = utptd.insertUserToProductTable2();
			mysqlUserToProduct.create(userToProduct);
			userToProduct = utptd.insertUserToProductTable3();
			mysqlUserToProduct.create(userToProduct);
			userToProduct = utptd.insertUserToProductTable4();
			mysqlUserToProduct.create(userToProduct);
			userToProduct = utptd.insertUserToProductTable5();
			mysqlUserToProduct.create(userToProduct);
			userToProduct = utptd.insertUserToProductTable6();
			mysqlUserToProduct.create(userToProduct);
			userToProduct = utptd.insertUserToProductTable7();
			mysqlUserToProduct.create(userToProduct);
			userToProduct = utptd.insertUserToProductTable8();
			mysqlUserToProduct.create(userToProduct);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreate() {
		try {
			UserToProduct userToProduct = utptd.insertUserToProductTable1();
			UserToProductXml userToProductXml = new UserToProductXml.UserToProductBuilder().id(userToProduct.getId())
					.productId(userToProduct.getProductId()).productIndex(userToProduct.getProductIndex())
					.userId(userToProduct.getUserId()).build();
			UserToProductMsgXml userToProductMsg = new UserToProductMsgXml(userToProductXml);
			UserToProductDaoHbn UserToProductDaoHbn = new UserToProductDaoHbn(userToProductMsg);
			userToProductMsg = (UserToProductMsgXml) UserToProductDaoHbn.create();
			assertEquals("Create returned an error ", "true", userToProductMsg.getSuccess());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			setUpUserToProducts();
			UserToProduct userToProduct = utptd.insertUserToProductTable6();
			UserToProductXml userToProductXml = new UserToProductXml.UserToProductBuilder().id(userToProduct.getId())
					.productId(userToProduct.getProductId()).productIndex(userToProduct.getProductIndex())
					.userId(userToProduct.getUserId()).build();
			UserToProductMsgXml userToProductMsg = new UserToProductMsgXml(userToProductXml);
			UserToProductDaoHbn UserToProductDaoHbn = new UserToProductDaoHbn(userToProductMsg);
			userToProductMsg = (UserToProductMsgXml) UserToProductDaoHbn.delete();
			assertEquals("Delete returned an error ", "true", userToProductMsg.getSuccess());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCount() {
		try {
			setUpUserToProducts();
			UserToProductXml userToProductXml = new UserToProductXml();
			UserToProductMsgXml userToProductMsg = new UserToProductMsgXml(userToProductXml);
			UserToProductDaoHbn UserToProductDaoHbn = new UserToProductDaoHbn(userToProductMsg);
			int count = UserToProductDaoHbn.getCount();
			assertEquals("getcount expected a count of 8 ", 8, count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadByProducIdProductIndexUserId() {
		try {
			setUpUserToProducts();
			UserToProductXml userToProductXml = new UserToProductXml();
			userToProductXml.setProductId("978098056856");
			userToProductXml.setProductIndex("0");
			userToProductXml.setUserId("Braffa");
			UserToProductMsgXml userToProductMsg = new UserToProductMsgXml(userToProductXml);
			UserToProductDaoHbn UserToProductDaoHbn = new UserToProductDaoHbn(userToProductMsg);
			userToProductMsg = (UserToProductMsgXml) UserToProductDaoHbn.read();
			ArrayList<UserToProductXml> lOfUserToProduct = userToProductMsg.getLOfUserToProduct();
			assertEquals("read failed only one row  should be returned ", 1, lOfUserToProduct.size());
			userToProductXml = lOfUserToProduct.get(0);
			assertEquals("read failed id should be 5 ", 5, userToProductXml.getId());
			assertEquals("read failed productId should be 978098056856 ", "978098056856",
					userToProductXml.getProductId());
			assertEquals("read failed productIndex should be 0 ", "0", userToProductXml.getProductIndex());
			assertEquals("read failed userid should be Braffa ", "Braffa", userToProductXml.getUserId());
			assertTrue("read failed updDate is null", null != userToProductXml.getUpdDate());
			assertTrue("read failed updDate is null", null != userToProductXml.getCrDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadByProducIdProductIndex() {
		try {
			setUpUserToProducts();
			UserToProductXml userToProductXml = new UserToProductXml();
			userToProductXml.setProductId("9999999999000");
			userToProductXml.setProductIndex("4");
			UserToProductMsgXml userToProductMsg = new UserToProductMsgXml(userToProductXml);
			UserToProductDaoHbn UserToProductDaoHbn = new UserToProductDaoHbn(userToProductMsg);
			userToProductMsg = (UserToProductMsgXml) UserToProductDaoHbn.read();
			ArrayList<UserToProductXml> lOfUserToProduct = userToProductMsg.getLOfUserToProduct();
			assertEquals("read failed only one row  should be returned ", 1, lOfUserToProduct.size());
			userToProductXml = lOfUserToProduct.get(0);
			assertEquals("read failed id should be 8 ", 8, userToProductXml.getId());
			assertEquals("read failed productId should be 9999999999000 ", "9999999999000",
					userToProductXml.getProductId());
			assertEquals("read failed productIndex should be 4 ", "4", userToProductXml.getProductIndex());
			assertEquals("read failed userid should be amanda33 ", "amanda33", userToProductXml.getUserId());
			assertTrue("read failed updDate is null", null != userToProductXml.getUpdDate());
			assertTrue("read failed updDate is null", null != userToProductXml.getCrDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadByProductId() {
		try {
			setUpUserToProducts();
			UserToProductXml userToProductXml = new UserToProductXml();
			userToProductXml.setProductId("9780789724410");
			UserToProductMsgXml userToProductMsg = new UserToProductMsgXml(userToProductXml);
			UserToProductDaoHbn UserToProductDaoHbn = new UserToProductDaoHbn(userToProductMsg);
			userToProductMsg = (UserToProductMsgXml) UserToProductDaoHbn.read();
			ArrayList<UserToProductXml> lOfUserToProduct = userToProductMsg.getLOfUserToProduct();
			assertEquals("read failed only one row  should be returned ", 2, lOfUserToProduct.size());
			userToProductXml = lOfUserToProduct.get(0);
			assertEquals("read failed id should be 2 ", 2, userToProductXml.getId());
			assertEquals("read failed productId should be 9780789724410 ", "9780789724410",
					userToProductXml.getProductId());
			assertEquals("read failed productIndex should be 0 ", "0", userToProductXml.getProductIndex());
			assertEquals("read failed userid should be georgie ", "georgie", userToProductXml.getUserId());
			assertTrue("read failed updDate is null", null != userToProductXml.getUpdDate());
			assertTrue("read failed updDate is null", null != userToProductXml.getCrDate());
			userToProductXml = lOfUserToProduct.get(1);
			assertEquals("read failed id should be 3 ", 3, userToProductXml.getId());
			assertEquals("read failed productId should be 9780789724410 ", "9780789724410",
					userToProductXml.getProductId());
			assertEquals("read failed productIndex should be 0 ", "0", userToProductXml.getProductIndex());
			assertEquals("read failed userid should be Braffa ", "Braffa", userToProductXml.getUserId());
			assertTrue("read failed updDate is null", null != userToProductXml.getUpdDate());
			assertTrue("read failed updDate is null", null != userToProductXml.getCrDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadByProductIdUserId() {
		try {
			setUpUserToProducts();
			UserToProductXml userToProductXml = new UserToProductXml();
			userToProductXml.setProductId("978098056856");
			userToProductXml.setUserId("Braffa");
			UserToProductMsgXml userToProductMsg = new UserToProductMsgXml(userToProductXml);
			UserToProductDaoHbn UserToProductDaoHbn = new UserToProductDaoHbn(userToProductMsg);
			userToProductMsg = (UserToProductMsgXml) UserToProductDaoHbn.read();
			ArrayList<UserToProductXml> lOfUserToProduct = userToProductMsg.getLOfUserToProduct();
			assertEquals("read failed only one row  should be returned ", 1, lOfUserToProduct.size());
			userToProductXml = lOfUserToProduct.get(0);
			assertEquals("read failed id should be 5 ", 5, userToProductXml.getId());
			assertEquals("read failed productId should be 978098056856 ", "978098056856",
					userToProductXml.getProductId());
			assertEquals("read failed productIndex should be 0 ", "0", userToProductXml.getProductIndex());
			assertEquals("read failed userid should be Braffa ", "Braffa", userToProductXml.getUserId());
			assertTrue("read failed updDate is null", null != userToProductXml.getUpdDate());
			assertTrue("read failed updDate is null", null != userToProductXml.getCrDate());
			System.out.println(userToProductXml.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testReadByUserId() {
		try {
			setUpUserToProducts();
			UserToProductXml userToProductXml = new UserToProductXml();
			userToProductXml.setUserId("Braffa");
			UserToProductMsgXml userToProductMsg = new UserToProductMsgXml(userToProductXml);
			UserToProductDaoHbn UserToProductDaoHbn = new UserToProductDaoHbn(userToProductMsg);
			userToProductMsg = (UserToProductMsgXml) UserToProductDaoHbn.read();
			ArrayList<UserToProductXml> lOfUserToProduct = userToProductMsg.getLOfUserToProduct();
			assertEquals("read failed five rows  should be returned ", 5, lOfUserToProduct.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testReadAll() {
		try {
			setUpUserToProducts();
			UserToProductXml userToProductXml = new UserToProductXml();
			UserToProductMsgXml userToProductMsg = new UserToProductMsgXml(userToProductXml);
			UserToProductDaoHbn UserToProductDaoHbn = new UserToProductDaoHbn(userToProductMsg);
			userToProductMsg = (UserToProductMsgXml) UserToProductDaoHbn.readAll();
			ArrayList<UserToProductXml> lOfUserToProduct = userToProductMsg.getLOfUserToProduct();
			assertEquals("read failed five rows  should be returned ", 8, lOfUserToProduct.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
