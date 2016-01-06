package com.braffa.sellem.model.xml;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "com.braffa.model.products")
@XmlType(propOrder = { "product", "LOfProducts", "searchField", "success" })
public class ProductMsgXml implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<ProductXml> lOfProducts;

	private ProductXml product;
	
	private String searchField;

	private String success;

	public ProductMsgXml() {

	}

	public ProductMsgXml(ProductXml Product) {
		this.product = Product;
	}

	public ProductMsgXml(ArrayList<ProductXml> aLOfProducts) {
		this.lOfProducts = aLOfProducts;
	}

	public ArrayList<ProductXml> getLOfProducts() {
		return lOfProducts;
	}

	@XmlElementWrapper(name = "productList")
	@XmlElement(name = "product")
	public void setLOfProducts(ArrayList<ProductXml> lOfProducts) {
		this.lOfProducts = lOfProducts;
	}

	public ProductXml getProduct() {
		return product;
	}

	@XmlElement(name = "product")
	public void setProduct(ProductXml aProduct) {
		this.product = aProduct;
	}
	
	public String getSearchField() {
		return searchField;
	}

	@XmlElement(name = "searchField")
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSuccess() {
		return success;
	}

	@XmlElement(name = "success")
	public void setSuccess(String success) {
		this.success = success;
	}
}
