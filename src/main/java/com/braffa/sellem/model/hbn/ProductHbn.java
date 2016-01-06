package com.braffa.sellem.model.hbn;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.braffa.sellem.model.sql.Product;
import com.braffa.sellem.model.sql.Product.ProductBuilder;
import com.braffa.sellem.model.xml.ProductXml;

@Entity
@Table(name = "PRODUCT")
public class ProductHbn {

	@Column(name = "author")
	private String author;

	@Column(name = "imageURL")
	private String imageURL;

	@Column(name = "imageLargeURL")
	private String imageLargeURL;

	@Column(name = "manufacturer")
	private String manufacturer;

	@Column(name = "productgroup")
	private String productgroup;

	@Id
	@Column(name = "productId")
	private String productId;

	@Column(name = "productidtype")
	private String productidtype;
	
	@Column(name = "productIndex")
	private String productIndex;

	@Column(name = "source")
	private String source;

	@Column(name = "sourceid")
	private String sourceid;

	@Column(name = "title")
	private String title;

	@Column(name = "crDate")
	private Date crDate;

	@Column(name = "updDate")
	private Date updDate;

	public ProductHbn() {

	}

	public ProductHbn(ProductXml aXmlProduct) {
		super();
		this.author = aXmlProduct.getAuthor();
		this.imageURL = aXmlProduct.getImageURL();
		this.imageLargeURL = aXmlProduct.getImageLargeURL();
		this.manufacturer = aXmlProduct.getManufacturer();
		this.productIndex = aXmlProduct.getProductIndex();
		this.productgroup = aXmlProduct.getProductgroup();
		this.productId = aXmlProduct.getProductId();
		this.productidtype = aXmlProduct.getProductidtype();
		this.source = aXmlProduct.getSource();
		this.sourceid = aXmlProduct.getSourceid();
		this.title = aXmlProduct.getTitle();
		this.crDate = aXmlProduct.getCrDate();
		this.updDate = aXmlProduct.getUpdDate();
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
		
		public ProductHbn build() {
			return new ProductHbn(this);
		}
	}
	private ProductHbn(ProductBuilder productBuilder) {
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
	
	public String getProductIndex() {
		return productIndex;
	}

	public void setProductIndex(String productIndex) {
		this.productIndex = productIndex;
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

}
