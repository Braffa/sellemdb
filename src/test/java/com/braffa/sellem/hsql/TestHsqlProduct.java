package com.braffa.sellem.hsql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class TestHsqlProduct {

	private static IDBActions hsqlProduct;

	private ProductTestData pdt = new ProductTestData();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		hsqlProduct = TableFactory.getTable(TableEnum.PRODUCT_HSQL);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		try {
			hsqlProduct.dropTable();
		} catch (Exception e) {
			// ignore if no table found
		}
		hsqlProduct.createTable();
	}

	@Test
	public void testSingleRow() {
		try {
			Product product = pdt.setUpProduct1();
			hsqlProduct.create(product);
			List<Product> lOfProducts = (List<Product>) hsqlProduct.read("9781849386685");
			System.out.println(lOfProducts.get(0).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setUpProducts() {
		try {
			Product product = pdt.setUpProduct1();
			hsqlProduct.create(product);
			product = pdt.setUpProduct2();
			hsqlProduct.create(product);
			product = pdt.setUpProduct3();
			hsqlProduct.create(product);
			product = pdt.setUpProduct4();
			hsqlProduct.create(product);
			product = pdt.setUpProduct5();
			hsqlProduct.create(product);
			product = pdt.setUpProduct6();
			hsqlProduct.create(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMultipleRow() {
		try {
			setUpProducts();
			List<Product> lOfProducts = (List<Product>) hsqlProduct.readAll();
			assertTrue("six rows were expected ", 6 == lOfProducts.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			setUpProducts();
			List<Product> lOfProducts = (List<Product>) hsqlProduct.readAll();
			assertTrue("six rows were expected ", 6 == lOfProducts.size());

			hsqlProduct.delete("5050582388237");
			lOfProducts = (List<Product>) hsqlProduct.readAll();

			assertTrue("six rows were expected ", 5 == lOfProducts.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		try {
			setUpProducts();
			List<Product> lOfProducts = (List<Product>) hsqlProduct.readAll();
			assertTrue("six rows were expected ", 6 == lOfProducts.size());

			Product product = new Product.ProductBuilder().author("John brown").imageURL("").imageLargeURL("")
					.manufacturer("").productIndex("").productgroup("").productId("978098056856").productidtype("")
					.source("").sourceid("").title("").build();

			hsqlProduct.update(product);
			lOfProducts = (List<Product>) hsqlProduct.read("978098056856");

			assertTrue("six rows were expected ", 1 == lOfProducts.size());
			assertEquals("author should be updated ", "John brown", lOfProducts.get(0).getAuthor());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
