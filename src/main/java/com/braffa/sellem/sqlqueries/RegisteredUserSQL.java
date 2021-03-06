package com.braffa.sellem.sqlqueries;

import java.util.Date;

import com.braffa.sellem.model.sql.RegisteredUser;

public class RegisteredUserSQL {

	public String createTableSQL() {
		StringBuffer sql = new StringBuffer("CREATE TABLE REGISTEREDUSER ");
		sql.append("(id INT NOT NULL AUTO_INCREMENT,");
		sql.append(" authorityLevel  VARCHAR(2), ");
		sql.append(" email VARCHAR(50),     ");
		sql.append(" firstname VARCHAR(50), ");
		sql.append(" lastname VARCHAR(50),  ");
		sql.append(" password VARCHAR(50), ");
		sql.append(" telephone VARCHAR(50), ");
		sql.append(" userId  VARCHAR(20), ");
		sql.append(" CRDATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,");
		sql.append(" UPDDATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP,");
		sql.append(" PRIMARY KEY ( id ))    ");
		return sql.toString();
	}

	public String creatTableHSQL() {
		StringBuffer sql = new StringBuffer("CREATE TABLE REGISTEREDUSER ");
		sql.append("(id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) not null, ");
		sql.append(" authorityLevel  VARCHAR(2), ");
		sql.append(" email VARCHAR(50),     ");
		sql.append(" firstname VARCHAR(50), ");
		sql.append(" lastname VARCHAR(50),  ");
		sql.append(" password VARCHAR(50), ");
		sql.append(" telephone VARCHAR(50), ");
		sql.append(" userId  VARCHAR(20), ");
		sql.append(" CRDATE TIMESTAMP,      ");
		sql.append(" UPDDATE TIMESTAMP,     ");
		sql.append(" PRIMARY KEY ( id ))    ");
		return sql.toString();
	}

	public String createRegisteredUser(RegisteredUser registeredUser) {
		StringBuffer sql = new StringBuffer("INSERT INTO REGISTEREDUSER (");
		sql.append("authorityLevel, email, firstname, lastname, password, telephone, ");
		sql.append("userId, CRDATE, UPDDATE) VALUES (");
		sql.append("'" + registeredUser.getAuthorityLevel() + "', ");
		sql.append("'" + registeredUser.getEmail() + "', ");
		sql.append("'" + registeredUser.getFirstname() + "', ");
		sql.append("'" + registeredUser.getLastname() + "', ");
		sql.append("'" + registeredUser.getPassword() + "', ");
		sql.append("'" + registeredUser.getTelephone() + "', ");
		sql.append("'" + registeredUser.getUserId() + "', ");
		sql.append("CURRENT_TIMESTAMP, ");
		sql.append("CURRENT_TIMESTAMP ");
		sql.append(")");
		return sql.toString();
	}

	public String deleteSQL(String userId) {
		StringBuffer sb = new StringBuffer("DELETE FROM REGISTEREDUSER ");
		sb.append(" WHERE USERID = '" + userId + "'");
		return sb.toString();
	}

	public String dropSQL() {
		return "DROP TABLE REGISTEREDUSER ";
	}

	public String readSQL(String userId) {
		StringBuffer sb = new StringBuffer("SELECT authorityLevel, email, firstname, lastname, password, ");
		sb.append("telephone, userId, CRDATE, UPDDATE ");
		sb.append("FROM REGISTEREDUSER WHERE USERID = '");
		sb.append(userId + "'");
		return sb.toString();
	}

	public String readAllSQL() {
		StringBuffer sb = new StringBuffer("SELECT authorityLevel, email, firstname, lastname, password, ");
		sb.append("telephone, userId, CRDATE, UPDDATE ");
		sb.append("FROM REGISTEREDUSER ");
		return sb.toString();
	}
	
	public String readByListOfKeysSQL(String userIds) {
		StringBuffer sb = new StringBuffer("SELECT authorityLevel, email, firstname, lastname, password, ");
		sb.append("telephone, userId, CRDATE, UPDDATE ");
		sb.append("FROM REGISTEREDUSER WHERE ");
		sb.append(userIds);
		return sb.toString();
	}

	public String updateSQL(RegisteredUser registeredUser) {
		StringBuffer sb = new StringBuffer("UPDATE REGISTEREDUSER SET ");
		boolean comma = false;
		int commaNeeded = 0;
		int commaOutput = 0;
		if (registeredUser.getAuthorityLevel().length() > 0) {
			sb.append("authorityLevel = '" + registeredUser.getAuthorityLevel() + "' ");
			comma = true;
			commaNeeded++;
		}	
		if (registeredUser.getEmail().length() > 0) {
			if (commaNeeded > commaOutput) {
				commaOutput++;commaNeeded++;
				sb.append(", email = '" + registeredUser.getEmail() + "' ");
				comma = false;
			} else {
				sb.append("email = '" + registeredUser.getEmail() + "' ");
				comma = true;
				commaNeeded++;
			}
		}
		if (registeredUser.getFirstname().length() > 0) {
			if (commaNeeded > commaOutput) {
				commaOutput++;commaNeeded++;
				sb.append(", FIRSTNAME = '" + registeredUser.getFirstname() + "' ");
				comma = false;
			} else {
				sb.append("FIRSTNAME = '" + registeredUser.getFirstname() + "' ");
				comma = true;
				commaNeeded++;
			}
		}
		if (registeredUser.getLastname().length() > 0) {
			if (commaNeeded > commaOutput) {
				commaOutput++;commaNeeded++;
				sb.append(", LASTNAME = '" + registeredUser.getLastname() + "' ");
				comma = false;
			} else {
				sb.append("LASTNAME = '" + registeredUser.getLastname() + "' ");
				comma = true;
				commaNeeded++;
			}
		}
		if (registeredUser.getPassword().length() > 0) {
			if (commaNeeded > commaOutput) {
				commaOutput++;commaNeeded++;
				sb.append(", password = '" + registeredUser.getPassword() + "' ");
				comma = false;
			} else {
				sb.append("password = '" + registeredUser.getPassword() + "' ");
				comma = true;
				commaNeeded++;
			}
		}
		if (registeredUser.getTelephone().length() > 0) {
			if (commaNeeded > commaOutput) {
				commaOutput++;commaNeeded++;
				sb.append(", TELEPHONE = '" + registeredUser.getTelephone() + "' ");
				comma = false;
			} else {
				sb.append("TELEPHONE = '" + registeredUser.getTelephone() + "' ");
				comma = true;
				commaNeeded++;
			}
		}
		sb.append("WHERE USERID = '" + registeredUser.getUserId() + "'");
		return sb.toString();
	}

}
