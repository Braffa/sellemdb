package com.braffa.sellem.mysql;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.braffa.sellem.datafortesting.ProductTestData;
import com.braffa.sellem.model.sql.Product;
import com.braffa.sellem.tables.IDBActions;
import com.braffa.sellem.tables.TableEnum;
import com.braffa.sellem.tables.TableFactory;

public class TestMysqlProduct {

	private static IDBActions mysqlProduct;

	private ProductTestData pdt = new ProductTestData();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mysqlProduct = TableFactory.getTable(TableEnum.PRODUCT);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		mysqlProduct.dropTable();
	}

	@Before
	public void setUp() throws Exception {
		try {
			mysqlProduct.dropTable();
		} catch (Exception e) {
			// ignore if no table found
		}
		mysqlProduct.createTable();
	}

	@Test
	public void testSingleRow() {
		try {
			Product product = pdt.setUpProduct1();
			mysqlProduct.create(product);

			List<Product> lOfProducts = (List<Product>) mysqlProduct.read("9781849386685");
			System.out.println(lOfProducts.get(0).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setUpProducts() {
		try {
			Product product = pdt.setUpProduct1();
			mysqlProduct.create(product);
			product = pdt.setUpProduct2();
			mysqlProduct.create(product);
			product = pdt.setUpProduct3();
			mysqlProduct.create(product);
			product = pdt.setUpProduct4();
			mysqlProduct.create(product);
			product = pdt.setUpProduct5();
			mysqlProduct.create(product);
			product = pdt.setUpProduct6();
			mysqlProduct.create(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMultipleRow() {
		try {
			setUpProducts();
			List<Product> lOfProducts = (List<Product>) mysqlProduct.readAll();
			assertTrue("six rows were expected ", 6 == lOfProducts.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			setUpProducts();
			List<Product> lOfProducts = (List<Product>) mysqlProduct.readAll();
			assertTrue("six rows were expected ", 6 == lOfProducts.size());

			mysqlProduct.delete("5050582388237");
			lOfProducts = (List<Product>) mysqlProduct.readAll();

			assertTrue("six rows were expected ", 5 == lOfProducts.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		try {
			setUpProducts();
			List<Product> lOfProducts = (List<Product>) mysqlProduct.readAll();
			assertTrue("six rows were expected ", 6 == lOfProducts.size());

			Product product = new Product.ProductBuilder().author("John brown").imageURL("").imageLargeURL("")
					.manufacturer("").productIndex("").productgroup("").productId("978098056856").productidtype("")
					.source("").sourceid("").title("").build();

			mysqlProduct.update(product);
			lOfProducts = (List<Product>) mysqlProduct.read("978098056856");

			assertTrue("six rows were expected ", 1 == lOfProducts.size());
			assertEquals("author should be updated ", "John brown", lOfProducts.get(0).getAuthor());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
