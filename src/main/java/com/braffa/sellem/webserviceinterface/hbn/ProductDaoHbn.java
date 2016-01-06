package com.braffa.sellem.webserviceinterface.hbn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.braffa.sellem.hbn.Dao;
import com.braffa.sellem.model.hbn.ProductHbn;
import com.braffa.sellem.model.sql.Product;
import com.braffa.sellem.model.xml.ProductMsgXml;
import com.braffa.sellem.model.xml.ProductXml;

public class ProductDaoHbn extends Dao {

	private static final Logger logger = Logger.getLogger(ProductDaoHbn.class);

	private ProductMsgXml productMsg;

	public ProductDaoHbn(ProductMsgXml productMsg) {
		this.productMsg = productMsg;
	}

	public Object create() {
		if (logger.isDebugEnabled()) {
			logger.debug("create");
		}
		ProductXml newProductXml = productMsg.getProduct();
		ProductHbn newProductHbn = new ProductHbn(newProductXml);

		Session session = getHibernateSessionFactory().openSession();
		session.beginTransaction();
		try {
			ProductHbn product = (ProductHbn) session.get(ProductHbn.class, newProductHbn.getProductId());
			if (null == product) {
				session.save(newProductHbn);
			}
			productMsg.setSuccess("true");
		} catch (Exception e) {
			e.printStackTrace();
			productMsg.setSuccess("false");
		}
		session.getTransaction().commit();
		return productMsg;
	}

	public Object delete() {
		if (logger.isDebugEnabled()) {
			logger.debug("delete");
		}
		ProductXml newProductXml = productMsg.getProduct();
		ProductHbn newProductHbn = new ProductHbn(newProductXml);
		Session session = getHibernateSessionFactory().openSession();
		session.beginTransaction();
		try {
			ProductHbn product = (ProductHbn) session.get(ProductHbn.class, newProductHbn.getProductId());
			if (null != product) {
				session.delete(product);
			}
			productMsg.setSuccess("true");
		} catch (Exception e) {
			e.printStackTrace();
			productMsg.setSuccess("false");
		}
		session.getTransaction().commit();
		return productMsg;
	}

	public int getCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getCount");
		}
		Session session = getHibernateSessionFactory().openSession();
		Query q = session.createQuery("From ProductHbn ");
		List<ProductXml> lOfProducts = q.list();
		return lOfProducts.size();
	}

	private ProductMsgXml executeQuery(String sql) {
		if (logger.isDebugEnabled()) {
			logger.debug("executeQuery " + sql);
		}
		try {
			Session session = getHibernateSessionFactory().openSession();
			Query q = session.createQuery(sql);
			List<ProductHbn> lOfProductHbns = q.list();
			if (lOfProductHbns.size() > 0) {
				ArrayList<ProductXml> lOfProductXml = new ArrayList<ProductXml>();
				for (ProductHbn productHbn : lOfProductHbns) {
					ProductXml productXml = new ProductXml(productHbn);
					lOfProductXml.add(productXml);
				}
				productMsg.setLOfProducts(lOfProductXml);
				productMsg.setSuccess("true");
			} else {
				ArrayList<ProductXml> lOfProductXml = new ArrayList<ProductXml>();
				productMsg.setLOfProducts(lOfProductXml);
				productMsg.setSuccess("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			productMsg.setSuccess("false");
		}
		return productMsg;
	}

	public Object read() {
		if (logger.isDebugEnabled()) {
			logger.debug("read");
		}
		StringBuffer sb = new StringBuffer("From ProductHbn WHERE productId = '"); 
		sb.append(productMsg.getProduct().getProductId());
		sb.append("'");
		return executeQuery(sb.toString());
	}

	public Object readAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("readAll");
		}
		StringBuffer sb = new StringBuffer("From ProductHbn "); 
		return executeQuery(sb.toString());
	}

	public Object readListOfKeys() {
		if (logger.isDebugEnabled()) {
			logger.debug("readAll");
		}
		StringBuffer sb = new StringBuffer("From ProductHbn WHERE ");
		int index = productMsg.getLOfProducts().size();
		int count = 0;
		for (ProductXml product : productMsg.getLOfProducts()) {
			sb.append("(productId = '" + product.getProductId() + "')");
			count++;
			if (index > 1 && count < index) {
				sb.append(" or ");
			}
		}
		return executeQuery(sb.toString());
	}

	public Object update() {
		if (logger.isDebugEnabled()) {
			logger.debug("update");
		}
		try {
			ProductXml productXml = productMsg.getProduct();
			ProductHbn updateProduct = new ProductHbn(productXml);
			Session session = this.getHibernateSessionFactory().openSession();
			session.beginTransaction();
			ProductHbn product = (ProductHbn) session.get(ProductHbn.class,
					updateProduct.getProductId());
			boolean changed = false;
			if (null != product) {
				if (null != updateProduct.getAuthor()
						&& updateProduct.getAuthor().length() > 0
						&& updateProduct.getAuthor() != product.getAuthor()) {
					product.setAuthor(updateProduct.getAuthor());
					changed = true;
				}
				if (null != updateProduct.getImageLargeURL()
						&& updateProduct.getImageLargeURL().length() > 0
						&& updateProduct.getImageLargeURL() != product
								.getImageLargeURL()) {
					product.setImageLargeURL(updateProduct.getImageLargeURL());
					changed = true;
				}
				if (null != updateProduct.getImageURL()
						&& updateProduct.getImageURL().length() > 0
						&& updateProduct.getImageURL() != product
								.getImageURL()) {
					product.setImageURL(updateProduct.getImageURL());
					changed = true;
				}
				if (null != updateProduct.getManufacturer()
						&& updateProduct.getManufacturer().length() > 0
						&& updateProduct.getManufacturer() != product
								.getManufacturer()) {
					product.setManufacturer(updateProduct.getManufacturer());
					changed = true;
				}
				if (null != updateProduct.getProductIndex()
						&& updateProduct.getProductIndex().length() > 0
						&& updateProduct.getProductIndex() != product
								.getProductIndex()) {
					product.setProductIndex(updateProduct.getProductIndex());
					changed = true;
				}
				if (null != updateProduct.getProductgroup()
						&& updateProduct.getProductgroup().length() > 0
						&& updateProduct.getProductgroup() != product
								.getProductgroup()) {
					product.setProductgroup(updateProduct.getProductgroup());
					changed = true;
				}
				if (null != updateProduct.getProductidtype()
						&& updateProduct.getProductidtype().length() > 0
						&& updateProduct.getProductidtype() != product
								.getProductidtype()) {
					product.setProductidtype(updateProduct.getProductidtype());
					changed = true;
				}
				if (null != updateProduct.getSource()
						&& updateProduct.getSource().length() > 0
						&& updateProduct.getSource() != product.getSource()) {
					product.setSource(updateProduct.getSource());
					changed = true;
				}
				if (null != updateProduct.getSourceid()
						&& updateProduct.getSourceid().length() > 0
						&& updateProduct.getSourceid() != product
								.getSourceid()) {
					product.setSourceid(updateProduct.getSourceid());
					changed = true;
				}
				if (null != updateProduct.getTitle()
						&& updateProduct.getTitle().length() > 0
						&& updateProduct.getTitle() != product.getTitle()) {
					product.setTitle(updateProduct.getTitle());
					changed = true;
				}
				if (changed) {
					product.setUpdDate(new Date());
					session.update(product);
					session.getTransaction().commit();
				}
			}
			ArrayList<ProductXml> lOfproductXml = new ArrayList<ProductXml>();
			lOfproductXml.add(new ProductXml(product));
			productMsg = new ProductMsgXml(lOfproductXml);
			productMsg.setSuccess("true");
		} catch (Exception e) {
			e.printStackTrace();
			productMsg.setSuccess("false");
		}
		return productMsg;
	}


	public ProductMsgXml search() {
		if (logger.isDebugEnabled()) {
			logger.debug("search");
		}
		ProductXml searchProduct = productMsg.getProduct();
		String searchField = productMsg.getSearchField();
		StringBuffer sql = new StringBuffer(" FROM ProductHbn where ");

		if (searchField.equals("author")) {
			sql.append("LOWER(author) like '%"
					+ searchProduct.getAuthor().toLowerCase() + "%'");
		} else if (searchField.equals("title")) {
			sql.append("LOWER(title) like '%"
					+ searchProduct.getTitle().toLowerCase() + "%'");
		} else if (searchField.equals("productid")) {
			sql.append("LOWER(productid) like '%"
					+ searchProduct.getProductId().toLowerCase() + "%'");
		} else if (searchField.equals("manufacturer")) {
			sql.append("LOWER(manufacturer) like '%"
					+ searchProduct.getManufacturer().toLowerCase() + "%'");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("SQL " + sql);
		}
		Session session = getHibernateSessionFactory().openSession();
		try {
			Query q = session.createQuery(sql.toString());
			List<ProductHbn> lOfProducts = q.list();
			ArrayList<ProductXml> lOfXmlProducts = new ArrayList<ProductXml>();
			for (ProductHbn product : lOfProducts) {
				ProductXml productXml = new ProductXml.ProductBuilder().author(product.getAuthor())
						.imageURL(product.getImageURL())
						.imageLargeURL(product.getImageLargeURL())
						.manufacturer(product.getManufacturer()).productIndex(product.getProductIndex())
						.productgroup(product.getProductgroup()).productId(product.getProductId())
						.productidtype(product.getProductidtype()).source(product.getSource()).sourceid(product.getSourceid())
						.title(product.getTitle()).crDate(product.getCrDate()).updDate(product.getUpdDate()).build();
				lOfXmlProducts.add(productXml);
			}
			productMsg.setLOfProducts(lOfXmlProducts);
			productMsg.setSuccess("true");
		} catch (Exception e) {
			System.out.println("ERROR");
			e.printStackTrace();
			productMsg.setSuccess("false");
		}
		return productMsg;
	}

	public Object remove() {
		if (logger.isDebugEnabled()) {
			logger.debug("remove");
		}
		Session session = getHibernateSessionFactory().openSession();
		session.beginTransaction();
		try {
			Product product = (Product) session.get(Product.class,
					productMsg.getProduct().getProductId());
			if (null != product) {
				session.delete(product);
				ProductXml productXml = new ProductXml();
				productMsg.setProduct(productXml);
				productMsg.setSuccess("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			productMsg.setSuccess("false");
		}
		session.getTransaction().commit();
		return productMsg;
	}

}
