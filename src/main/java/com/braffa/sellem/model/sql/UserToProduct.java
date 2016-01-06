package com.braffa.sellem.model.sql;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "com.braffa.sellem.UserToProducts")
@XmlType(propOrder = { "id", "userId", "productId", "productIndex", "crDate", "updDate"})
public class UserToProduct {
	
	private int id;

	private String productId;

	private String productIndex;
	
	private String userId;

	private Date crDate;

	private Date updDate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public static class UserToProductBuilder {
		private int id;
		private String userId;
		private String productId;
		private String productIndex;
		private Date crDate;
		private Date updDate;

		public UserToProductBuilder() {

		}
		
		public UserToProductBuilder id(int id) {
			this.id = id;
			return this;
		}

		public UserToProductBuilder userId(String userId) {
			this.userId = userId;
			return this;
		}

		public UserToProductBuilder productId(String productId) {
			this.productId = productId;
			return this;
		}

		public UserToProductBuilder productIndex(String productIndex) {
			this.productIndex = productIndex;
			return this;
		}

		public UserToProductBuilder crDate(Date crDate) {
			this.crDate = crDate;
			return this;
		}

		public UserToProductBuilder updDate(Date updDate) {
			this.updDate = updDate;
			return this;
		}

		public UserToProduct build() {
			return new UserToProduct(this);
		}
	}

	private UserToProduct(UserToProductBuilder userToProductBuilder) {
		this.id = userToProductBuilder.id;
		this.userId = userToProductBuilder.userId;
		this.productId = userToProductBuilder.productId;
		this.productIndex = userToProductBuilder.productIndex;
		this.crDate = userToProductBuilder.crDate;
		this.updDate = userToProductBuilder.updDate;
	}
}
