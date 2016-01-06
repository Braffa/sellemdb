package com.braffa.sellem.model.xml;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.braffa.sellem.model.hbn.RegisteredUserHbn;
import com.braffa.sellem.model.sql.RegisteredUser;

@XmlRootElement(namespace = "com.braffa.model.RegisteredUsers")
@XmlType(propOrder = { "authorityLevel", "email", "firstname", "lastname", "password", "telephone", "userId", "crDate", "updDate" })
public class RegisteredUserXml implements Serializable {

	private static final long serialVersionUID = 1L;

	private String authorityLevel;

	private String email;

	private String firstname;

	private String lastname;

	private String password;

	private String telephone;

	private String userId;

	private Date crDate;

	private Date updDate;
	
	public RegisteredUserXml () {
		
	}

	public String getAuthorityLevel() {
		return authorityLevel;
	}

	public void setAuthorityLevel(String authorityLevel) {
		this.authorityLevel = authorityLevel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCrDate() {
		return crDate;
	}

	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public static class RegisteredUserXmlBuilder {
		private String authorityLevel;
		private String email;
		private String firstname;
		private String lastname;
		private String password;
		private String telephone;
		private String userId;
		private Date crDate;
		private Date updDate;

		public RegisteredUserXmlBuilder() {
		}

		public RegisteredUserXmlBuilder userId(String userId) {
			this.userId = userId;
			return this;
		}

		public RegisteredUserXmlBuilder authorityLevel(String authorityLevel) {
			this.authorityLevel = authorityLevel;
			return this;
		}

		public RegisteredUserXmlBuilder email(String email) {
			this.email = email;
			return this;
		}

		public RegisteredUserXmlBuilder firstname(String firstname) {
			this.firstname = firstname;
			return this;
		}

		public RegisteredUserXmlBuilder lastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public RegisteredUserXmlBuilder password(String password) {
			this.password = password;
			return this;
		}

		public RegisteredUserXmlBuilder telephone(String telephone) {
			this.telephone = telephone;
			return this;
		}

		public RegisteredUserXmlBuilder crDate(Date crDate) {
			this.crDate = crDate;
			return this;
		}

		public RegisteredUserXmlBuilder updDate(Date updDate) {
			this.updDate = updDate;
			return this;
		}

		public RegisteredUserXml build() {
			return new RegisteredUserXml(this);
		}
	}

	private RegisteredUserXml(RegisteredUserXmlBuilder registeredUserBuilder) {
		this.authorityLevel = registeredUserBuilder.authorityLevel;
		this.email = registeredUserBuilder.email;
		this.firstname = registeredUserBuilder.firstname;
		this.lastname = registeredUserBuilder.lastname;
		this.password = registeredUserBuilder.password;
		this.telephone = registeredUserBuilder.telephone;
		this.userId = registeredUserBuilder.userId;
		this.crDate = registeredUserBuilder.crDate;
		this.updDate = registeredUserBuilder.updDate;
	}
	
	public RegisteredUserXml (RegisteredUser registeredUser) {
		this.authorityLevel = registeredUser.getAuthorityLevel();
		this.email = registeredUser.getEmail();
		this.firstname = registeredUser.getFirstname();
		this.lastname = registeredUser.getLastname();
		this.password = registeredUser.getPassword();
		this.telephone = registeredUser.getTelephone();
		this.userId = registeredUser.getUserId();
		this.crDate = registeredUser.getCrDate();
		this.updDate = registeredUser.getUpdDate();
	}

	public RegisteredUserXml (RegisteredUserHbn registeredUser) {
		this.authorityLevel = registeredUser.getAuthorityLevel();
		this.email = registeredUser.getEmail();
		this.firstname = registeredUser.getFirstname();
		this.lastname = registeredUser.getLastname();
		this.password = registeredUser.getPassword();
		this.telephone = registeredUser.getTelephone();
		this.userId = registeredUser.getUserId();
		this.crDate = registeredUser.getCrDate();
		this.updDate = registeredUser.getUpdDate();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(" authorityLevel - " + authorityLevel);
		sb.append("\n email - " + email);
		sb.append("\n firstname - " + firstname);
		sb.append("\n lastname - " + lastname);
		sb.append("\n password - " + password);
		sb.append("\n telephone - " + telephone);
		sb.append("\n userId - " + userId);
		sb.append("\n crDate - " + crDate);
		sb.append("\n updDate - " + updDate);
		return sb.toString();
	}
}
