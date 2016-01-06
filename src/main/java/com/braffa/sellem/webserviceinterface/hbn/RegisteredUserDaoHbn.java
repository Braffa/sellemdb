package com.braffa.sellem.webserviceinterface.hbn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.braffa.sellem.hbn.Dao;
import com.braffa.sellem.hbn.HibernateUtil;
import com.braffa.sellem.model.hbn.RegisteredUserHbn;
import com.braffa.sellem.model.xml.RegisteredUserMsgXml;
import com.braffa.sellem.model.xml.RegisteredUserXml;

public class RegisteredUserDaoHbn extends Dao {

	private static final Logger logger = Logger.getLogger(RegisteredUserDaoHbn.class);

	private RegisteredUserMsgXml registeredUserMsg;

	public RegisteredUserDaoHbn(RegisteredUserMsgXml registeredUserMsg) {
		this.registeredUserMsg = registeredUserMsg;
	}

	public Object create() {
		if (logger.isDebugEnabled()) {
			logger.debug("create RegisteredUser");
		}
		try {
			RegisteredUserXml registeredUserxml = registeredUserMsg.getRegisteredUser();
			RegisteredUserHbn newRegisteredUserHbn = new RegisteredUserHbn(registeredUserxml);

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			RegisteredUserHbn registeredUserHbn = (RegisteredUserHbn) session.get(RegisteredUserHbn.class,
					newRegisteredUserHbn.getUserId());
			if (null == registeredUserHbn) {
				session.save(newRegisteredUserHbn);
			}
			session.getTransaction().commit();
			registeredUserMsg.setRegisteredUser(new RegisteredUserXml());
			registeredUserMsg.setSuccess("true");
		} catch (Exception e) {
			registeredUserMsg.setSuccess("false");
		}
		return registeredUserMsg;
	}

	public Object delete() {
		if (logger.isDebugEnabled()) {
			logger.debug("delete RegisteredUser");
		}
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			RegisteredUserHbn registeredUser = (RegisteredUserHbn) session.get(RegisteredUserHbn.class,
					registeredUserMsg.getRegisteredUser().getUserId());
			if (null != registeredUser) {
				session.delete(registeredUser);
			}
			session.getTransaction().commit();
			registeredUserMsg.setRegisteredUser(new RegisteredUserXml());
			registeredUserMsg.setSuccess("true");
		} catch (Exception e) {
			registeredUserMsg.setSuccess("false");
		}
		return registeredUserMsg;
	}

	public int getCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("get count");
		}
		Session session = this.getHibernateSessionFactory().openSession();
		Query q = session.createQuery("From RegisteredUserHbn ");
		List<RegisteredUserHbn> lOfRegisteredUser = q.list();
		return lOfRegisteredUser.size();
	}

	public Object read() {
		if (logger.isDebugEnabled()) {
			logger.debug("read RegisteredUser");
		}
		StringBuffer sql = new StringBuffer("From RegisteredUserHbn WHERE USERID = '");
		sql.append(registeredUserMsg.getRegisteredUser().getUserId());
		sql.append("'");
		return executeQuery(sql.toString());
	}

	public Object readAll() {
		if (logger.isDebugEnabled()) {
			logger.debug("readAll RegisteredUser");
		}
		return executeQuery("From RegisteredUserHbn");
	}

	public Object readListOfKeys() {
		if (logger.isDebugEnabled()) {
			logger.debug("readListOfKeys");
		}
		StringBuffer sql = new StringBuffer("From RegisteredUserHbn WHERE ");
		int index = registeredUserMsg.getLOfRegisteredUsers().size();
		int count = 0;
		for (RegisteredUserXml registeredUser : registeredUserMsg.getLOfRegisteredUsers()) {
			sql.append("(userId = '" + registeredUser.getUserId() + "')");
			count++;
			if (index > 1 && count < index) {
				sql.append(" or ");
			}
		}
		return executeQuery(sql.toString());
	}

	private RegisteredUserMsgXml executeQuery(String sql) {
		try {
			Session session = getHibernateSessionFactory().openSession();
			Query query = session.createQuery(sql.toString());
			List<RegisteredUserHbn> lOfRegisteredUsersHbn = query.list();
			ArrayList<RegisteredUserXml> lOfRegisteredUsersXml = new ArrayList<RegisteredUserXml>();
			for (RegisteredUserHbn registeredUserHbn : lOfRegisteredUsersHbn) {
				RegisteredUserXml xmlRegisteredUser = new RegisteredUserXml(registeredUserHbn);
				lOfRegisteredUsersXml.add(xmlRegisteredUser);
			}
			registeredUserMsg.setLOfRegisteredUsers(lOfRegisteredUsersXml);
			registeredUserMsg.setSuccess("true");
		} catch (Exception e) {
			e.printStackTrace();
			registeredUserMsg.setSuccess("false");
		}
		return registeredUserMsg;
	}

	public Object update() {
		if (logger.isDebugEnabled()) {
			logger.debug("updateRegisteredUser");
		}
		try {
			RegisteredUserXml registeredUserxml = registeredUserMsg.getRegisteredUser();
			RegisteredUserHbn updateRegisteredUser = new RegisteredUserHbn(registeredUserxml);

			Session session = this.getHibernateSessionFactory().openSession();
			session.beginTransaction();
			RegisteredUserHbn registeredUser = (RegisteredUserHbn) session.get(RegisteredUserHbn.class,
					updateRegisteredUser.getUserId());
			boolean changed = false;
			if (null != registeredUser) {
				if (null != updateRegisteredUser.getAuthorityLevel()
						&& updateRegisteredUser.getAuthorityLevel().length() > 0
						&& updateRegisteredUser.getAuthorityLevel() != registeredUser
								.getAuthorityLevel()) {
					registeredUser.setAuthorityLevel(updateRegisteredUser.getAuthorityLevel());
					changed = true;
				}
				if (null != updateRegisteredUser.getEmail()
						&& updateRegisteredUser.getEmail().length() > 0
						&& updateRegisteredUser.getEmail() != registeredUser
								.getEmail()) {
					registeredUser.setEmail(updateRegisteredUser.getEmail());
					changed = true;
				}
				if (null != updateRegisteredUser.getFirstname()
						&& updateRegisteredUser.getFirstname().length() > 0
						&& updateRegisteredUser.getFirstname() != registeredUser
								.getFirstname()) {
					registeredUser.setFirstname(updateRegisteredUser
							.getFirstname());
					changed = true;
				}
				if (null != updateRegisteredUser.getLastname()
						&& updateRegisteredUser.getLastname().length() > 0
						&& updateRegisteredUser.getLastname() != registeredUser
								.getLastname()) {
					registeredUser.setLastname(updateRegisteredUser
							.getLastname());
					changed = true;
				}
				if (null != updateRegisteredUser.getPassword()
						&& updateRegisteredUser.getPassword().length() > 0
						&& updateRegisteredUser.getPassword() != registeredUser
								.getPassword()) {
					registeredUser.setPassword(updateRegisteredUser
							.getPassword());
					changed = true;
				}
				if (null != updateRegisteredUser.getTelephone()
						&& updateRegisteredUser.getTelephone().length() > 0
						&& updateRegisteredUser.getTelephone() != registeredUser
								.getTelephone()) {
					registeredUser.setTelephone(updateRegisteredUser
							.getTelephone());
					changed = true;
				}
				/*
				if (null != updateRegisteredUser.getUserId()
						&& updateRegisteredUser.getUserId().length() > 0
						&& updateRegisteredUser.getUserId() != registeredUser
								.getUserId()) {
					registeredUser.setUserId(updateRegisteredUser
							.getUserId());
					changed = true;
				}
				*/
				if (changed) {
					registeredUser.setUpdDate(new Date());
					session.update(registeredUser);
					session.getTransaction().commit();
				}
			}
			ArrayList<RegisteredUserXml> lOfRegisteredUsersXml = new ArrayList<RegisteredUserXml>();
			lOfRegisteredUsersXml.add(new RegisteredUserXml(registeredUser));
			registeredUserMsg = new RegisteredUserMsgXml(lOfRegisteredUsersXml);
			registeredUserMsg.setSuccess("true");
		} catch (Exception e) {
			e.printStackTrace();
			registeredUserMsg.setSuccess("false");
		}
		return registeredUserMsg;
	}

	public Object search() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object remove() {
		// TODO Auto-generated method stub
		return null;
	}

}