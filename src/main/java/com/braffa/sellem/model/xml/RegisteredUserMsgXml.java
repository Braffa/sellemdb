package com.braffa.sellem.model.xml;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "registeredUsers")
@XmlType(propOrder = { "searchField", "success", "registeredUser", "LOfRegisteredUsers" })
public class RegisteredUserMsgXml implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<RegisteredUserXml> lOfRegisteredUsers;

	private String searchField;

	private String success;

	private RegisteredUserXml registeredUser;

	public RegisteredUserMsgXml() {

	}

	public RegisteredUserMsgXml(RegisteredUserXml aRegisteredUser) {
		this.registeredUser = aRegisteredUser;
	}

	public RegisteredUserMsgXml(ArrayList<RegisteredUserXml> aLOfRegisteredUsers) {
		this.lOfRegisteredUsers = aLOfRegisteredUsers;
	}

	public ArrayList<RegisteredUserXml> getLOfRegisteredUsers() {
		return lOfRegisteredUsers;
	}

	@XmlElementWrapper(name = "registereduserlist")
	@XmlElement(name = "registereduser")
	public void setLOfRegisteredUsers(
			ArrayList<RegisteredUserXml> aLOfRegisteredUsers) {
		this.lOfRegisteredUsers = aLOfRegisteredUsers;
	}

	public String getSearchField() {
		return searchField;
	}

	@XmlElement(name = "searchField")
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public RegisteredUserXml getRegisteredUser() {
		return registeredUser;
	}

	@XmlElement(name = "registereduser")
	public void setRegisteredUser(RegisteredUserXml aRegisteredUser) {
		this.registeredUser = aRegisteredUser;
	}

	public String getSuccess() {
		return success;
	}

	@XmlElement(name = "success")
	public void setSuccess(String success) {
		this.success = success;
	}

}
