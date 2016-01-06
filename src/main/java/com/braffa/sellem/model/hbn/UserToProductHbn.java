package com.braffa.sellem.model.hbn;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.braffa.sellem.model.xml.UserToProductXml;

@Entity
@Table(name = "UserToProduct")
public class UserToProductHbn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "productId")
	private String productId;

	@Column(name = "productIndex")
	private String productIndex;

	@Column(name = "userId")
	private String userId;

	@Column(name = "crDate")
	private Date crDate;

	@Column(name = "updDate")
	private Date updDate;

	public UserToProductHbn() {

	}

	public UserToProductHbn(UserToProductXml userToProduct) {
		super();
		this.id = userToProduct.getId();
		this.productId = userToProduct.getProductId();
		this.productIndex = userToProduct.getProductIndex();
		this.userId = userToProduct.getUserId();
		this.crDate = userToProduct.getCrDate();
		this.updDate = userToProduct.getUpdDate();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductIndex() {
		return productIndex;
	}

	public void setProductIndex(String productIndex) {
		this.productIndex = productIndex;
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

}
