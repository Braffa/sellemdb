package com.braffa.sellem.model.sql;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "com.braffa.sellem.model.Products" )
@XmlType(propOrder = {"author", "imageURL", "imageLargeURL", "imageLargeURL", "manufacturer", "productIndex", "productgroup" ,
		"productid", "productidtype", "source", "sourceid", "title", "crDate", "updDate"})
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String author;
	private String imageURL;
	private String imageLargeURL;
	private String manufacturer;
	private String productIndex;
	private String productgroup;
	private String productId;
	private String productidtype;
	private String source;
	private String sourceid;
	private String title;
	private Date crDate;
	private Date updDate;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getImageLargeURL() {
		return imageLargeURL;
	}

	public void setImageLargeURL(String imageLargeURL) {
		this.imageLargeURL = imageLargeURL;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getProductIndex() {
		return productIndex;
	}

	public void setProductIndex(String productIndex) {
		this.productIndex = productIndex;
	}

	public String getProductgroup() {
		return productgroup;
	}

	public void setProductgroup(String productgroup) {
		this.productgroup = productgroup;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductidtype() {
		return productidtype;
	}

	public void setProductidtype(String productidtype) {
		this.productidtype = productidtype;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public static class ProductBuilder {
		private String author;
		private String imageURL;
		private String imageLargeURL;
		private String manufacturer;
		private String productIndex;
		private String productgroup;
		private String productId;
		private String productidtype;
		private String source;
		private String sourceid;
		private String title;
		private Date crDate;
		private Date updDate;

		public ProductBuilder() {

		}

		public ProductBuilder author(String author) {
			this.author = author;
			return this;
		}

		public ProductBuilder imageURL(String imageURL) {
			this.imageURL = imageURL;
			return this;
		}

		public ProductBuilder imageLargeURL(String imageLargeURL) {
			this.imageLargeURL = imageLargeURL;
			return this;
		}

		public ProductBuilder manufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}

		public ProductBuilder productIndex(String productIndex) {
			this.productIndex = productIndex;
			return this;
		}

		public ProductBuilder productgroup(String productgroup) {
			this.productgroup = productgroup;
			return this;
		}

		public ProductBuilder productId(String productId) {
			this.productId = productId;
			return this;
		}

		public ProductBuilder productidtype(String productidtype) {
			this.productidtype = productidtype;
			return this;
		}

		public ProductBuilder source(String source) {
			this.source = source;
			return this;
		}

		public ProductBuilder sourceid(String sourceid) {
			this.sourceid = sourceid;
			return this;
		}

		public ProductBuilder title(String title) {
			this.title = title;
			return this;
		}

		public ProductBuilder crDate(Date crDate) {
			this.crDate = crDate;
			return this;
		}

		public ProductBuilder updDate(Date updDate) {
			this.updDate = updDate;
			return this;
		}
		
		public Product build() {
			return new Product(this);
		}
	}
	private Product(ProductBuilder productBuilder) {
		this.author =productBuilder.author;
		this.imageURL = productBuilder.imageURL;
		this.imageLargeURL = productBuilder.imageLargeURL;
		this.manufacturer = productBuilder.manufacturer;
		this.productIndex = productBuilder.productIndex;
		this.productgroup = productBuilder.productgroup;
		this.productId = productBuilder.productId;
		this.productidtype = productBuilder.productidtype;
		this.source = productBuilder.source;
		this.sourceid = productBuilder.sourceid;
		this.title = productBuilder.title;
	}
	
	public String toString () {
		StringBuffer sb = new StringBuffer();
		sb.append("author " + author);
		sb.append("\nimageURL " + imageURL);
		sb.append("\nimageLargeURL " + imageLargeURL);
		sb.append("\nmanufacturer " + manufacturer);
		sb.append("\nproductIndex " + productIndex);
		sb.append("\nproductgroup " + productgroup);
		sb.append("\nproductId " + productId);
		sb.append("\nproductidtype " + productidtype);
		sb.append("\nsource " + source);
		sb.append("\nsourceid " + sourceid);
		sb.append("\ntitle " + title);
		sb.append("\ncrDate " + crDate);
		sb.append("\nupdDate " + updDate);
		
		return sb.toString();
	}
}
