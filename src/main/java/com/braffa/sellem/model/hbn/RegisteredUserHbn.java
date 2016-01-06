package com.braffa.sellem.model.hbn;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.braffa.sellem.model.xml.RegisteredUserXml;

@Entity
@Table(name = "REGISTEREDUSER")
public class RegisteredUserHbn {

	@Column(name = "authorityLevel")
	private String authorityLevel;

	@Column(name = "email")
	private String email;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "password")
	private String password;

	@Column(name = "telephone")
	private String telephone;

	@Id
	@Column(name = "userId")
	private String userId;

	@Column(name = "crDate")
	private Date crDate;

	@Column(name = "updDate")
	private Date updDate;

	public RegisteredUserHbn() {

	}

	public RegisteredUserHbn(RegisteredUserXml registeredUserXml) {
		this.authorityLevel = registeredUserXml.getAuthorityLevel();
		this.email = registeredUserXml.getEmail();
		this.firstname = registeredUserXml.getFirstname();
		this.lastname = registeredUserXml.getLastname();
		this.password = registeredUserXml.getPassword();
		this.telephone = registeredUserXml.getTelephone();
		this.userId = registeredUserXml.getUserId();
		this.crDate = registeredUserXml.getCrDate();
		this.updDate = registeredUserXml.getUpdDate();
	}

	public static class RegisteredUserBuilder {
		private String authorityLevel;
		private String email;
		private String firstname;
		private String lastname;
		private String password;
		private String telephone;
		private String userId;
		private Date crDate;
		private Date updDate;

		public RegisteredUserBuilder() {
		}

		public RegisteredUserBuilder userId(String userId) {
			this.userId = userId;
			return this;
		}

		public RegisteredUserBuilder authorityLevel(String authorityLevel) {
			this.authorityLevel = authorityLevel;
			return this;
		}

		public RegisteredUserBuilder email(String email) {
			this.email = email;
			return this;
		}

		public RegisteredUserBuilder firstname(String firstname) {
			this.firstname = firstname;
			return this;
		}

		public RegisteredUserBuilder lastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public RegisteredUserBuilder password(String password) {
			this.password = password;
			return this;
		}

		public RegisteredUserBuilder telephone(String telephone) {
			this.telephone = telephone;
			return this;
		}

		public RegisteredUserBuilder crDate(Date crDate) {
			this.crDate = crDate;
			return this;
		}

		public RegisteredUserBuilder updDate(Date updDate) {
			this.updDate = updDate;
			return this;
		}

		public RegisteredUserHbn build() {
			return new RegisteredUserHbn(this);
		}
	}

	private RegisteredUserHbn(RegisteredUserBuilder registeredUserBuilder) {
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthorityLevel() {
		return authorityLevel;
	}

	public void setAuthorityLevel(String authorityLevel) {
		this.authorityLevel = authorityLevel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n authorityLevel " + authorityLevel);
		sb.append("\n email " + email);
		sb.append("\n firstname " + firstname);
		sb.append("\n lastname " + lastname);
		sb.append("\n password " + password);
		sb.append("\n telephone " + telephone);
		sb.append("\n userId " + userId);
		sb.append("\n crDate " + crDate);
		sb.append("\n updDate " + updDate);
		return sb.toString();
	}
}
