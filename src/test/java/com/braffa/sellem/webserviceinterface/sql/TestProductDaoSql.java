package com.braffa.sellem.webserviceinterface.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.braffa.sellem.datafortesting.ProductTestData;
import com.braffa.sellem.model.sql.Product;
import com.braffa.sellem.model.xml.ProductMsgXml;
import com.braffa.sellem.model.xml.ProductXml;
import com.braffa.sellem.tables.IDBActions;
import com.braffa.sellem.tables.TableEnum;
import com.braffa.sellem.tables.TableFactory;
import com.braffa.sellem.webserviceinterface.sql.ProductDaoSql;

public class TestProductDaoSql {

	private static IDBActions mysqlProduct;

	private ProductTestData pdt = new ProductTestData();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mysqlProduct = TableFactory.getTable(TableEnum.PRODUCT);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// mysqlProduct.dropTable();
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
	public void testCreate() {
		try {
			Product product = pdt.setUpProduct1();

			ProductXml productXml = new ProductXml.ProductBuilder().author(product.getAuthor())
					.imageURL(product.getImageURL()).imageLargeURL(product.getImageLargeURL())
					.manufacturer(product.getManufacturer()).productIndex(product.getProductIndex())
					.productgroup(product.getProductgroup()).productId(product.getProductId())
					.productidtype(product.getProductidtype()).source(product.getSource())
					.sourceid(product.getSourceid()).title(product.getTitle()).build();

			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml) productDaoSql.create();
			System.out.println(productMsg.getSuccess());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			setUpProducts();
			Product product = pdt.setUpProduct1();

			ProductXml productXml = new ProductXml.ProductBuilder().author(product.getAuthor())
					.imageURL(product.getImageURL()).imageLargeURL(product.getImageLargeURL())
					.manufacturer(product.getManufacturer()).productIndex(product.getProductIndex())
					.productgroup(product.getProductgroup()).productId(product.getProductId())
					.productidtype(product.getProductidtype()).source(product.getSource())
					.sourceid(product.getSourceid()).title(product.getTitle()).build();

			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml) productDaoSql.delete();
			System.out.println(productMsg.getSuccess());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCount() {
		try {
			setUpProducts();
			ProductXml productXml = new ProductXml(); 
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productDaoSql.getCount();
			System.out.println(productDaoSql.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRead() {
		try {
			setUpProducts();
			ProductXml productXml = new ProductXml(); 
			productXml.setProductId("9780789724410");
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml)productDaoSql.read();
			System.out.println(productMsg.getSuccess());
			ArrayList<ProductXml> lOfProducts = productMsg.getLOfProducts();
			productXml = lOfProducts.get(0);
			System.out.println(productXml.toString());
			assertEquals("getProduct failed Incorrect author returned ",
					"Mark Wutka", productXml.getAuthor());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"QUE", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product group returned ",
					"Book", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"9780789724410", productXml.getProductId());
			assertEquals("getProduct failed Incorrect product type returned ",
					"EAN", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "Amazon",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"789724413", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"Using Java Server Pages and Servlets Special Edition (Special Edition Using)",
					productXml.getTitle());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadAll() {
		try {
			setUpProducts();
			ProductXml productXml = new ProductXml(); 
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml)productDaoSql.readAll();
			System.out.println(productMsg.getSuccess());
			ArrayList<ProductXml> lOfProducts = productMsg.getLOfProducts();
			System.out.println(lOfProducts.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadByListOfKeys() {
		try {
			setUpProducts();
			ProductXml productXml = new ProductXml(); 
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			ArrayList<ProductXml> lOfProducts = new ArrayList<ProductXml> ();
			productXml = new ProductXml();
			productXml.setProductId("978098056856");
			lOfProducts.add(productXml);
			productXml = new ProductXml();
			productXml.setProductId("9781861005618");
			lOfProducts.add(productXml);
			productXml = new ProductXml();
			productXml.setProductId("9780789799999");
			lOfProducts.add(productXml);
			productMsg.setLOfProducts(lOfProducts);
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml)productDaoSql.readListOfKeys();
			System.out.println(productMsg.getSuccess());
			lOfProducts = productMsg.getLOfProducts();
			
			assertEquals("testReadByListOfKeys wrong number of rows returned ",
					3, lOfProducts.size());
			productXml = lOfProducts.get(0);
			assertEquals("getProduct failed Incorrect author returned ",
					"Willy wonka", productXml.getAuthor());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"QUE", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product index returned ",
					"0", productXml.getProductIndex());
			assertEquals("getProduct failed Incorrect product group returned ",
					"DVD", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"9780789799999", productXml.getProductId());
			assertEquals("getProduct failed Incorrect product type returned ",
					"EAN", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "Amazon",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"789724999", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"The Choocolate Factory",
					productXml.getTitle());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
			
			productXml = lOfProducts.get(1);
			
			assertEquals("getProduct failed Incorrect author returned ",
					"Craig Sharkie", productXml.getAuthor());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"http://ecx.images-amazon.com/images/I/412Ddd%2Bq1ZL._SL75_.jpg",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"http://ecx.images-amazon.com/images/I/412Ddd%2Bq1ZL._SL75_.jpg",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"SITEPOINT (ACADEMIC)", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product index returned ",
					"0", productXml.getProductIndex());
			assertEquals("getProduct failed Incorrect product group returned ",
					"Book", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"978098056856", productXml.getProductId());
			assertEquals("getProduct failed Incorrect product type returned ",
					"EAN", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "Amazon",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"980576857", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"jQuery: Novice to Ninja",
					productXml.getTitle());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
			
			productXml = lOfProducts.get(2);
			
			assertEquals("getProduct failed Incorrect author returned ",
					"Subrahmanyam Allamaraju", productXml.getAuthor());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"http://ecx.images-amazon.com/images/I/51YKR0ZVKRL._SL75_.jpg",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"http://ecx.images-amazon.com/images/I/51YKR0ZVKRL._SL75_.jpg",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"WROX Press Ltd", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product index returned ",
					"0", productXml.getProductIndex());
			assertEquals("getProduct failed Incorrect product group returned ",
					"Book", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"9781861005618", productXml.getProductId());
			assertEquals("getProduct failed Incorrect product type returned ",
					"EAN", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "Amazon",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"186100561X", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"",
					productXml.getTitle());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateAuthor() {
		try {
			setUpProducts();
			Product product = pdt.setUpProduct6();
			ProductXml productXml = new ProductXml.ProductBuilder().author("change of author")
					.imageURL("")
					.imageLargeURL("")
					.manufacturer("").productIndex("")
					.productgroup("").productId(product.getProductId())
					.productidtype("").source("").sourceid("")
					.title("").build();
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productDaoSql.update();
			productXml = new ProductXml(); 
			productXml.setProductId("9780789799999");
			productMsg = new ProductMsgXml(productXml);
			productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml)productDaoSql.read();
			
			System.out.println(productMsg.getSuccess());
			productXml = productMsg.getLOfProducts().get(0); 
			System.out.println(productXml.toString());
			assertEquals("getProduct failed Incorrect author returned ",
					"change of author", productXml.getAuthor());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"QUE", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product index returned ",
					"0", productXml.getProductIndex());
			assertEquals("getProduct failed Incorrect product group returned ",
					"DVD", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"9780789799999", productXml.getProductId());
			assertEquals("getProduct failed Incorrect product type returned ",
					"EAN", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "Amazon",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"789724999", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"The Choocolate Factory",
					productXml.getTitle());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateImageUrl() {
		try {
			setUpProducts();
			Product product = pdt.setUpProduct6();
			ProductXml productXml = new ProductXml.ProductBuilder().author("")
					.imageURL("change of image url")
					.imageLargeURL("")
					.manufacturer("").productIndex("")
					.productgroup("").productId(product.getProductId())
					.productidtype("").source("").sourceid("")
					.title("").build();
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productDaoSql.update();
			productXml = new ProductXml(); 
			productXml.setProductId("9780789799999");
			productMsg = new ProductMsgXml(productXml);
			productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml)productDaoSql.read();
			System.out.println(productMsg.getSuccess());
			
			productXml = productMsg.getLOfProducts().get(0); 

			assertEquals("getProduct failed Incorrect author returned ",
					"Willy wonka", productXml.getAuthor());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"change of image url",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"QUE", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product index returned ",
					"0", productXml.getProductIndex());
			assertEquals("getProduct failed Incorrect product group returned ",
					"DVD", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"9780789799999", productXml.getProductId());
			assertEquals("getProduct failed Incorrect product type returned ",
					"EAN", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "Amazon",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"789724999", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"The Choocolate Factory",
					productXml.getTitle());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateLargeImageUrl() {
		try {
			setUpProducts();
			Product product = pdt.setUpProduct6();
			ProductXml productXml = new ProductXml.ProductBuilder().author("")
					.imageURL("")
					.imageLargeURL("change of large image url")
					.manufacturer("").productIndex("")
					.productgroup("").productId(product.getProductId())
					.productidtype("").source("").sourceid("")
					.title("").build();
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productDaoSql.update();
			productXml = new ProductXml(); 
			productXml.setProductId("9780789799999");
			productMsg = new ProductMsgXml(productXml);
			productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml)productDaoSql.read();
			System.out.println(productMsg.getSuccess());
			
			productXml = productMsg.getLOfProducts().get(0); 

			assertEquals("getProduct failed Incorrect author returned ",
					"Willy wonka", productXml.getAuthor());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"change of large image url",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"QUE", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product index returned ",
					"0", productXml.getProductIndex());
			assertEquals("getProduct failed Incorrect product group returned ",
					"DVD", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"9780789799999", productXml.getProductId());
			assertEquals("getProduct failed Incorrect product type returned ",
					"EAN", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "Amazon",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"789724999", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"The Choocolate Factory",
					productXml.getTitle());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdateManufacturer() {
		try {
			setUpProducts();
			Product product = pdt.setUpProduct6();
			ProductXml productXml = new ProductXml.ProductBuilder().author("")
					.imageURL("")
					.imageLargeURL("")
					.manufacturer("change of Manufacturer").productIndex("")
					.productgroup("").productId(product.getProductId())
					.productidtype("").source("").sourceid("")
					.title("").build();
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productDaoSql.update();
			productXml = new ProductXml(); 
			productXml.setProductId("9780789799999");
			productMsg = new ProductMsgXml(productXml);
			productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml)productDaoSql.read();
			System.out.println(productMsg.getSuccess());
			
			productXml = productMsg.getLOfProducts().get(0); 

			assertEquals("getProduct failed Incorrect author returned ",
					"Willy wonka", productXml.getAuthor());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"change of Manufacturer", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product index returned ",
					"0", productXml.getProductIndex());
			assertEquals("getProduct failed Incorrect product group returned ",
					"DVD", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"9780789799999", productXml.getProductId());
			assertEquals("getProduct failed Incorrect product type returned ",
					"EAN", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "Amazon",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"789724999", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"The Choocolate Factory",
					productXml.getTitle());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testUpdateProductIndex() {
		try {
			setUpProducts();
			Product product = pdt.setUpProduct6();
			ProductXml productXml = new ProductXml.ProductBuilder().author("")
					.imageURL("")
					.imageLargeURL("")
					.manufacturer("").productIndex("1")
					.productgroup("").productId(product.getProductId())
					.productidtype("").source("").sourceid("")
					.title("").build();
			System.out.println(productXml.toString());
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productDaoSql.update();
			productXml = new ProductXml(); 
			productXml.setProductId("9780789799999");
			productMsg = new ProductMsgXml(productXml);
			productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml)productDaoSql.read();
			System.out.println(productMsg.getSuccess());
			
			productXml = productMsg.getLOfProducts().get(0); 

			assertEquals("getProduct failed Incorrect author returned ",
					"Willy wonka", productXml.getAuthor());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"QUE", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product index returned ",
					"1", productXml.getProductIndex());
			assertEquals("getProduct failed Incorrect product group returned ",
					"DVD", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"9780789799999", productXml.getProductId());
			assertEquals("getProduct failed Incorrect product type returned ",
					"EAN", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "Amazon",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"789724999", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"The Choocolate Factory",
					productXml.getTitle());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testUpdateProductGroup() {
		try {
			setUpProducts();
			Product product = pdt.setUpProduct6();
			ProductXml productXml = new ProductXml.ProductBuilder().author("")
					.imageURL("")
					.imageLargeURL("")
					.manufacturer("").productIndex("")
					.productgroup("test").productId(product.getProductId())
					.productidtype("").source("").sourceid("")
					.title("").build();
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productDaoSql.update();
			productXml = new ProductXml(); 
			productXml.setProductId("9780789799999");
			productMsg = new ProductMsgXml(productXml);
			productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml)productDaoSql.read();
			System.out.println(productMsg.getSuccess());
			
			productXml = productMsg.getLOfProducts().get(0); 

			assertEquals("getProduct failed Incorrect author returned ",
					"Willy wonka", productXml.getAuthor());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"QUE", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product index returned ",
					"0", productXml.getProductIndex());
			assertEquals("getProduct failed Incorrect product group returned ",
					"test", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"9780789799999", productXml.getProductId());
			assertEquals("getProduct failed Incorrect product type returned ",
					"EAN", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "Amazon",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"789724999", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"The Choocolate Factory",
					productXml.getTitle());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testUpdateProductIdType() {
		try {
			setUpProducts();
			Product product = pdt.setUpProduct6();
			ProductXml productXml = new ProductXml.ProductBuilder().author("")
					.imageURL("")
					.imageLargeURL("")
					.manufacturer("").productIndex("")
					.productgroup("").productId(product.getProductId())
					.productidtype("DAB").source("").sourceid("")
					.title("").build();
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productDaoSql.update();
			productXml = new ProductXml(); 
			productXml.setProductId("9780789799999");
			productMsg = new ProductMsgXml(productXml);
			productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml)productDaoSql.read();
			System.out.println(productMsg.getSuccess());
			
			productXml = productMsg.getLOfProducts().get(0); 

			assertEquals("getProduct failed Incorrect author returned ",
					"Willy wonka", productXml.getAuthor());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"QUE", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product index returned ",
					"0", productXml.getProductIndex());
			assertEquals("getProduct failed Incorrect product group returned ",
					"DVD", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"9780789799999", productXml.getProductId());
			assertEquals("getProduct failed Incorrect product type returned ",
					"DAB", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "Amazon",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"789724999", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"The Choocolate Factory",
					productXml.getTitle());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
		
	@Test
	public void testUpdateSource() {
		try {
			setUpProducts();
			Product product = pdt.setUpProduct6();
			ProductXml productXml = new ProductXml.ProductBuilder().author("")
					.imageURL("")
					.imageLargeURL("")
					.manufacturer("").productIndex("")
					.productgroup("").productId(product.getProductId())
					.productidtype("").source("TESCO").sourceid("")
					.title("").build();
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productDaoSql.update();
			productXml = new ProductXml(); 
			productXml.setProductId("9780789799999");
			productMsg = new ProductMsgXml(productXml);
			productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml)productDaoSql.read();
			System.out.println(productMsg.getSuccess());
			
			productXml = productMsg.getLOfProducts().get(0); 

			assertEquals("getProduct failed Incorrect author returned ",
					"Willy wonka", productXml.getAuthor());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"QUE", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product index returned ",
					"0", productXml.getProductIndex());
			assertEquals("getProduct failed Incorrect product group returned ",
					"DVD", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"9780789799999", productXml.getProductId());
			assertEquals("getProduct failed Incorrect id product type returned ",
					"EAN", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "TESCO",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"789724999", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"The Choocolate Factory",
					productXml.getTitle());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testUpdateSourceID() {
		try {
			setUpProducts();
			Product product = pdt.setUpProduct6();
			ProductXml productXml = new ProductXml.ProductBuilder().author("")
					.imageURL("")
					.imageLargeURL("")
					.manufacturer("").productIndex("")
					.productgroup("").productId(product.getProductId())
					.productidtype("").source("").sourceid("999999")
					.title("").build();
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productDaoSql.update();
			productXml = new ProductXml(); 
			productXml.setProductId("9780789799999");
			productMsg = new ProductMsgXml(productXml);
			productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml)productDaoSql.read();
			System.out.println(productMsg.getSuccess());
			
			productXml = productMsg.getLOfProducts().get(0); 

			assertEquals("getProduct failed Incorrect author returned ",
					"Willy wonka", productXml.getAuthor());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"QUE", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product index returned ",
					"0", productXml.getProductIndex());
			assertEquals("getProduct failed Incorrect product group returned ",
					"DVD", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"9780789799999", productXml.getProductId());
			assertEquals("getProduct failed Incorrect id product type returned ",
					"EAN", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "Amazon",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"999999", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"The Choocolate Factory",
					productXml.getTitle());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testUpdateTitle() {
		try {
			setUpProducts();
			Product product = pdt.setUpProduct6();
			ProductXml productXml = new ProductXml.ProductBuilder().author("")
					.imageURL("")
					.imageLargeURL("")
					.manufacturer("").productIndex("")
					.productgroup("").productId(product.getProductId())
					.productidtype("").source("").sourceid("")
					.title("Change of title").build();
			ProductMsgXml productMsg = new ProductMsgXml(productXml);
			
			ProductDaoSql productDaoSql = new ProductDaoSql(productMsg);
			productDaoSql.update();
			productXml = new ProductXml(); 
			productXml.setProductId("9780789799999");
			productMsg = new ProductMsgXml(productXml);
			productDaoSql = new ProductDaoSql(productMsg);
			productMsg = (ProductMsgXml)productDaoSql.read();
			System.out.println(productMsg.getSuccess());
			
			productXml = productMsg.getLOfProducts().get(0); 

			assertEquals("getProduct failed Incorrect author returned ",
					"Willy wonka", productXml.getAuthor());
			assertEquals("getProduct failed Incorrect image URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageURL());
			assertEquals("getProduct failed Incorrect image large URL returned ",
					"http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg",
					productXml.getImageLargeURL());
			assertEquals("getProduct failed Incorrect manufacturer returned ",
					"QUE", productXml.getManufacturer());
			assertEquals("getProduct failed Incorrect product index returned ",
					"0", productXml.getProductIndex());
			assertEquals("getProduct failed Incorrect product group returned ",
					"DVD", productXml.getProductgroup());
			assertEquals("getProduct failed Incorrect product id returned ",
					"9780789799999", productXml.getProductId());
			assertEquals("getProduct failed Incorrect id product type returned ",
					"EAN", productXml.getProductidtype());
			assertEquals("getProduct failed Incorrect source returned ", "Amazon",
					productXml.getSource());
			assertEquals("actualProduct failed Incorrect source id returned ",
					"789724999", productXml.getSourceid());
			assertEquals(
					"getProduct failed Incorrect title returned ",
					"Change of title",
					productXml.getTitle());
			assertTrue("getProduct failed crDate is null",
					null != productXml.getCrDate());
			assertTrue("getProduct failed updDate is null",
					null != productXml.getUpdDate());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}
