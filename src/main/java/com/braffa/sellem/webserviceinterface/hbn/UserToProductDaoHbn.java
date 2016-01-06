package com.braffa.sellem.webserviceinterface.hbn;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.braffa.sellem.hbn.Dao;
import com.braffa.sellem.model.hbn.UserToProductHbn;
import com.braffa.sellem.model.xml.UserToProductMsgXml;
import com.braffa.sellem.model.xml.UserToProductXml;

public class UserToProductDaoHbn extends Dao {

	private static final Logger logger = Logger.getLogger(UserToProductDaoHbn.class);

	private UserToProductMsgXml userToProductMsg;

	public UserToProductDaoHbn(UserToProductMsgXml userToProductMsg) {
		this.userToProductMsg = userToProductMsg;
	}

	public Object create() {
		if (logger.isDebugEnabled()) {
			logger.debug("create");
		}
		UserToProductXml newUserToProductXml = userToProductMsg.getUserToProduct();
		UserToProductHbn newUserToProductHbn = new UserToProductHbn(newUserToProductXml);
		Session session = getHibernateSessionFactory().openSession();
		session.beginTransaction();
		try {
			UserToProductHbn userToProduct = (UserToProductHbn) session.get(UserToProductHbn.class,
					newUserToProductHbn.getId());
			if (null == userToProduct) {
				session.save(newUserToProductHbn);
			}
			userToProductMsg.setSuccess("true");
		} catch (Exception e) {
			e.printStackTrace();
			userToProductMsg.setSuccess("false");
		}
		session.getTransaction().commit();
		return userToProductMsg;
	}

	public Object delete() {
		if (logger.isDebugEnabled()) {
			logger.debug("delete");
		}

		UserToProductXml newUserToProductXml = userToProductMsg.getUserToProduct();
		UserToProductHbn newUserToProductHbn = new UserToProductHbn(newUserToProductXml);
		Session session = getHibernateSessionFactory().openSession();
		session.beginTransaction();
		try {
			read();
			UserToProductHbn userToProduct = (UserToProductHbn) session.get(UserToProductHbn.class,
					userToProductMsg.getLOfUserToProduct().get(0).getId());
			if (null != userToProduct) {
				session.delete(userToProduct);
			}
			userToProductMsg.setSuccess("true");
		} catch (Exception e) {
			e.printStackTrace();
			userToProductMsg.setSuccess("false");
		}
		session.getTransaction().commit();
		return userToProductMsg;
	}

	public int getCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getCount");
		}
		Session session = getHibernateSessionFactory().openSession();
		Query q = session.createQuery("From UserToProductHbn ");
		List<UserToProductXml> lOfUserToProducts = q.list();
		userToProductMsg.setSuccess("true");
		return lOfUserToProducts.size();
	}

	private UserToProductMsgXml executeQuery(String sql) {
		if (logger.isDebugEnabled()) {
			logger.debug("executeQuery " + sql);
		}
		try {
			Session session = getHibernateSessionFactory().openSession();
			Query q = session.createQuery(sql);
			List<UserToProductHbn> lOfUserToProductHbns = q.list();
			if (lOfUserToProductHbns.size() > 0) {
				ArrayList<UserToProductXml> lOUserTofProductXml = new ArrayList<UserToProductXml>();
				for (UserToProductHbn userToProductHbn : lOfUserToProductHbns) {
					UserToProductXml userToProductXml = new UserToProductXml.UserToProductBuilder()
							.id(userToProductHbn.getId()).productId(userToProductHbn.getProductId())
							.productIndex(userToProductHbn.getProductIndex()).userId(userToProductHbn.getUserId())
							.crDate(userToProductHbn.getCrDate()).updDate(userToProductHbn.getUpdDate()).build();
					lOUserTofProductXml.add(userToProductXml);
				}
				userToProductMsg.setLOfUserToProduct(lOUserTofProductXml);
				userToProductMsg.setSuccess("true");
			} else {
				ArrayList<UserToProductXml> lOfUserToProductXmll = new ArrayList<UserToProductXml>();
				userToProductMsg.setLOfUserToProduct(lOfUserToProductXmll);
				userToProductMsg.setSuccess("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userToProductMsg.setSuccess("false");
		}
		return userToProductMsg;
	}

	public Object read() {
		if (logger.isDebugEnabled()) {
			logger.debug("read");
		}
		UserToProductXml userToProductXml = userToProductMsg.getUserToProduct();
		boolean hasProductId = (userToProductXml.getProductId() != null && userToProductXml.getProductId().length() > 0)
				? true : false;
		boolean hasProductIndex = (userToProductXml.getProductIndex() != null
				&& userToProductXml.getProductIndex().length() > 0) ? true : false;
		boolean hasUserId = (userToProductXml.getUserId() != null && userToProductXml.getUserId().length() > 0) ? true
				: false;
		StringBuffer sb = new StringBuffer("From UserToProductHbn WHERE ");

		if (hasProductId && hasProductIndex && hasUserId) {
			sb.append(" productId = '");
			sb.append(userToProductXml.getProductId());
			sb.append("' AND productIndex = '");
			sb.append(userToProductXml.getProductIndex());
			sb.append("' AND userId = '");
			sb.append(userToProductXml.getUserId());
			sb.append("'");
		} else if (hasProductId && hasProductIndex) {
			sb.append(" productId = '");
			sb.append(userToProductXml.getProductId());
			sb.append("' AND productIndex = '");
			sb.append(userToProductXml.getProductIndex());
			sb.append("'");
		} else if (hasProductId && hasUserId) {
			sb.append(" productId = '");
			sb.append(userToProductXml.getProductId());
			sb.append("' AND userId = '");
			sb.append(userToProductXml.getUserId());
			sb.append("'");
		} else if (hasProductId) {
			sb.append(" productId = '");
			sb.append(userToProductXml.getProductId());
			sb.append("'");
		} else if (hasUserId) {
			sb.append(" userId = '");
			sb.append(userToProductXml.getUserId());
			sb.append("'");
		}
		return executeQuery(sb.toString());
	}

	public Object readAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("readAll");
		}
		StringBuffer sb = new StringBuffer("From UserToProductHbn "); 
		return executeQuery(sb.toString());
	}

	public Object readListOfKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object update() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object search() {
		if (logger.isDebugEnabled()) {
			logger.debug("search");
		}
		UserToProductXml userToProductXml = userToProductMsg.getUserToProduct();
		boolean hasProductId = (userToProductXml.getProductId() != null && userToProductXml.getProductId().length() > 0)
				? true : false;
		boolean hasProductIndex = (userToProductXml.getProductIndex() != null
				&& userToProductXml.getProductIndex().length() > 0) ? true : false;
		boolean hasUserId = (userToProductXml.getUserId() != null && userToProductXml.getUserId().length() > 0) ? true
				: false;
		StringBuffer sb = new StringBuffer("From UserToProductHbn WHERE ");

		if (hasProductId && hasProductIndex && hasUserId) {
			sb.append(" productId = '");
			sb.append(userToProductXml.getProductId());
			sb.append("' AND productIndex = '");
			sb.append(userToProductXml.getProductIndex());
			sb.append("' AND userId = '");
			sb.append(userToProductXml.getUserId());
			sb.append("'");
		} else if (hasProductId && hasProductIndex) {
			sb.append(" productId = '");
			sb.append(userToProductXml.getProductId());
			sb.append("' AND productIndex = '");
			sb.append(userToProductXml.getProductIndex());
			sb.append("'");
		} else if (hasProductId && hasUserId) {
			sb.append(" productId = '");
			sb.append(userToProductXml.getProductId());
			sb.append("' AND userId = '");
			sb.append(userToProductXml.getUserId());
			sb.append("'");
		} else if (hasProductId) {
			sb.append(" productId = '");
			sb.append(userToProductXml.getProductId());
			sb.append("'");
		} else if (hasUserId) {
			sb.append(" userId = '");
			sb.append(userToProductXml.getUserId());
			sb.append("'");
		}
		return executeQuery(sb.toString());
	}

	public Object remove() {
		// TODO Auto-generated method stub
		return null;
	}

}
