package com.braffa.sellem.webserviceinterface.sql;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.braffa.sellem.hbn.Dao;
import com.braffa.sellem.model.sql.Product;
import com.braffa.sellem.model.xml.ProductMsgXml;
import com.braffa.sellem.model.xml.ProductXml;
import com.braffa.sellem.tables.IDBActions;
import com.braffa.sellem.tables.TableEnum;
import com.braffa.sellem.tables.TableFactory;

public class ProductDaoSql extends Dao {

	private static final Logger logger = Logger.getLogger(ProductDaoSql.class);

	private ProductMsgXml productMsg;

	private static IDBActions mysqlProduct = TableFactory.getTable(TableEnum.PRODUCT);

	public ProductDaoSql(ProductMsgXml productMsg) {
		this.productMsg = productMsg;
	}

	public Object create() {
		if (logger.isDebugEnabled()) {
			logger.debug("create");
		}
		ProductXml productXml = productMsg.getProduct();
		Product product = new Product.ProductBuilder().author(productXml.getAuthor()).imageURL(productXml.getImageURL())
				.imageLargeURL(productXml.getImageLargeURL()).manufacturer(productXml.getManufacturer())
				.productIndex(productXml.getProductIndex()).productgroup(productXml.getProductgroup())
				.productId(productXml.getProductId()).productidtype(productXml.getProductidtype())
				.source(productXml.getSource()).sourceid(productXml.getSourceid()).title(productXml.getTitle()).build();
		try {
			mysqlProduct.create(product);
			productMsg.setSuccess("true");
		} catch (Exception e) {
			productMsg.setSuccess("false");
		}
		return productMsg;
	}

	public Object delete() {
		if (logger.isDebugEnabled()) {
			logger.debug("delete");
		}
		ProductXml productXml = productMsg.getProduct();
		Product product = new Product.ProductBuilder().author(productXml.getAuthor()).imageURL(productXml.getImageURL())
				.imageLargeURL(productXml.getImageLargeURL()).manufacturer(productXml.getManufacturer())
				.productIndex(productXml.getProductIndex()).productgroup(productXml.getProductgroup())
				.productId(productXml.getProductId()).productidtype(productXml.getProductidtype())
				.source(productXml.getSource()).sourceid(productXml.getSourceid()).title(productXml.getTitle()).build();
		try {
			mysqlProduct.delete(product.getProductId());
			productMsg.setSuccess("true");
		} catch (Exception e) {
			e.printStackTrace();
			productMsg.setSuccess("false");
		}
		return productMsg;
	}

	public int getCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getCount");
		}
		try {
			List<Product> lOfProducts = (List<Product>) mysqlProduct.readAll();
			productMsg.setSuccess("true");
			return lOfProducts.size();
		} catch (Exception e) {
			e.printStackTrace();
			productMsg.setSuccess("false");
			return 0;
		}
	}

	private ProductMsgXml processList(List<Product> lOfProducts) {
		ArrayList<ProductXml> lOfProductXmls = new ArrayList<ProductXml>();
		for (Product product : lOfProducts) {
			ProductXml productXml = new ProductXml.ProductBuilder().author(product.getAuthor())
					.imageURL(product.getImageURL()).imageLargeURL(product.getImageLargeURL())
					.manufacturer(product.getManufacturer()).productIndex(product.getProductIndex())
					.productgroup(product.getProductgroup()).productId(product.getProductId())
					.productidtype(product.getProductidtype()).source(product.getSource())
					.sourceid(product.getSourceid()).title(product.getTitle()).crDate(product.getCrDate())
					.updDate(product.getUpdDate()).build();
			lOfProductXmls.add(productXml);
		}
		productMsg.setLOfProducts(lOfProductXmls);
		productMsg.setSuccess("true");
		return productMsg;
	}

	public Object read() {
		if (logger.isDebugEnabled()) {
			logger.debug("read");
		}
		ProductXml productXml = productMsg.getProduct();
		try {
			List<Product> lOfProducts = (List<Product>) mysqlProduct.read(productXml.getProductId());
			return processList(lOfProducts);
		} catch (Exception e) {
			e.printStackTrace();
			productMsg.setSuccess("false");
			return productMsg;
		}
	}

	public Object readAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("read");
		}
		try {
			List<Product> lOfProducts = (List<Product>) mysqlProduct.readAll();
			return processList(lOfProducts);
		} catch (Exception e) {
			e.printStackTrace();
			productMsg.setSuccess("false");
			return productMsg;
		}
	}

	public Object readListOfKeys() {
		if (logger.isDebugEnabled()) {
			logger.debug("readAll");
		}
		StringBuffer sb = new StringBuffer();
		int index = productMsg.getLOfProducts().size();
		int count = 0;
		for (ProductXml product : productMsg.getLOfProducts()) {
			sb.append("(productId = '" + product.getProductId() + "')");
			count++;
			if (index > 1 && count < index) {
				sb.append(" or ");
			}
		}
		try {
			List<Product> lOfProducts = (List<Product>) mysqlProduct.readByListOfKeys(sb.toString());
			return processList(lOfProducts);
		} catch (Exception e) {
			e.printStackTrace();
			productMsg.setSuccess("false");
			return productMsg;
		}
	}

	public Object update() {
		if (logger.isDebugEnabled()) {
			logger.debug("update");
		}
		try {
			ProductXml productXml = productMsg.getProduct();
			Product product = new Product.ProductBuilder().author(productXml.getAuthor())
					.imageURL(productXml.getImageURL()).imageLargeURL(productXml.getImageLargeURL())
					.manufacturer(productXml.getManufacturer()).productIndex(productXml.getProductIndex())
					.productgroup(productXml.getProductgroup()).productId(productXml.getProductId())
					.productidtype(productXml.getProductidtype()).source(productXml.getSource())
					.sourceid(productXml.getSourceid()).title(productXml.getTitle()).build();
			mysqlProduct.update(product);
			productMsg.setSuccess("true");
			return productMsg;
		} catch (Exception e) {
			e.printStackTrace();
			productMsg.setSuccess("false");
			return productMsg;
		}
	}

	public ProductMsgXml search() {
		if (logger.isDebugEnabled()) {
			logger.debug("search");
		}

		return null;
	}

	public Object remove() {
		if (logger.isDebugEnabled()) {
			logger.debug("remove");
		}

		return null;
	}

}
