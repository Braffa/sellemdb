package com.braffa.sellem.model.xml;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "com.braffa.sellem.model.xml.usertoproducts")
@XmlType(propOrder = { "userToProduct", "product", "LOfUserToProduct", "success" })
public class UserToProductMsgXml implements Serializable {

	private static final long serialVersionUID = 1L;

	private UserToProductXml userToProduct;
	
	private ProductXml product;
	
	private ArrayList<UserToProductXml> lOfUserToProduct;
	
	private String success;

	public UserToProductMsgXml() {

	}
	
	public UserToProductMsgXml(UserToProductXml userToProduct) {
		this.userToProduct = userToProduct;
	}

	public UserToProductXml getUserToProduct() {
		return userToProduct;
	}

	@XmlElement(name = "usertoproduct")
	public void setUserToProduct(UserToProductXml userToProduct) {
		this.userToProduct = userToProduct;
	}
	
	@XmlElementWrapper(name = "usertoproductList")
	@XmlElement(name = "usertoproduct")
	public void setLOfUserToProduct(ArrayList<UserToProductXml> lOfUserToProduct) {
		this.lOfUserToProduct = lOfUserToProduct;
	}

	public ArrayList<UserToProductXml> getLOfUserToProduct() {
		return lOfUserToProduct;
	}

	public String getSuccess() {
		return success;
	}

	@XmlElement(name = "success")
	public void setSuccess(String success) {
		this.success = success;
	}

	@XmlElement(name = "product")
	public ProductXml getProduct() {
		return product;
	}

	public void setProduct(ProductXml product) {
		this.product = product;
	}

}
