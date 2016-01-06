package com.braffa.sellem.datafortesting;

import com.braffa.sellem.model.sql.Product;

public class ProductTestData {
	public Product setUpProduct() {
		Product product = new Product.ProductBuilder().author("").imageURL("").imageLargeURL("").manufacturer("")
				.productIndex("").productgroup("").productId("").productidtype("").source("").sourceid("").title("")
				.build();
		return product;
	}

	public Product setUpProduct1() {
		Product product = new Product.ProductBuilder().author("Sandercoe Justin")
				.imageURL("http://ecx.images-amazon.com/images/I/41hdqEVaWML._SL75_.jpg")
				.imageLargeURL("http://ecx.images-amazon.com/images/I/41hdqEVaWML._SL75_.jpg")
				.manufacturer("Music Sales").productIndex("1").productgroup("Book").productId("9781849386685")
				.productidtype("EAN").source("Amazon").sourceid("1849386684")
				.title("Justinguitar.Com Beginners Songbook J. Sandercoe (Easy Guitar With Notes and Tab)").build();
		return product;
	}

	public Product setUpProduct2() {
		Product product = new Product.ProductBuilder().author("")
				.imageURL("http://ecx.images-amazon.com/images/I/511R3AC2C8L._SL75_.jpg")
				.imageLargeURL("http://ecx.images-amazon.com/images/I/511R3AC2C8L._SL75_.jpg")
				.manufacturer("Universal Pictures UK").productIndex("0").productgroup("DVD").productId("5050582388237")
				.productidtype("ean").source("Amazon").sourceid("B000BKTBVI").title("Green Street (Hooligans) [DVD]")
				.build();
		return product;
	}

	public Product setUpProduct3() {
		Product product = new Product.ProductBuilder().author("Craig Sharkie")
				.imageURL("http://ecx.images-amazon.com/images/I/412Ddd%2Bq1ZL._SL75_.jpg")
				.imageLargeURL("http://ecx.images-amazon.com/images/I/412Ddd%2Bq1ZL._SL75_.jpg")
				.manufacturer("SITEPOINT (ACADEMIC)").productIndex("0").productgroup("Book").productId("978098056856")
				.productidtype("EAN").source("Amazon").sourceid("980576857").title("jQuery: Novice to Ninja").build();
		return product;
	}

	public Product setUpProduct4() {
		Product product = new Product.ProductBuilder().author("Subrahmanyam Allamaraju")
				.imageURL("http://ecx.images-amazon.com/images/I/51YKR0ZVKRL._SL75_.jpg")
				.imageLargeURL("http://ecx.images-amazon.com/images/I/51YKR0ZVKRL._SL75_.jpg")
				.manufacturer("WROX Press Ltd").productIndex("0").productgroup("Book").productId("9781861005618")
				.productidtype("EAN").source("Amazon").sourceid("186100561X").title("").build();
		return product;
	}

	public Product setUpProduct5() {
		Product product = new Product.ProductBuilder().author("Mark Wutka")
				.imageURL("http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg")
				.imageLargeURL("http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg").manufacturer("QUE")
				.productIndex("0").productgroup("Book").productId("9780789724410").productidtype("EAN").source("Amazon")
				.sourceid("789724413")
				.title("Using Java Server Pages and Servlets Special Edition (Special Edition Using)").build();
		return product;
	}

	public Product setUpProduct6() {
		Product product = new Product.ProductBuilder().author("Willy wonka")
				.imageURL("http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg")
				.imageLargeURL("http://ecx.images-amazon.com/images/I/51TW6S55A4L._SL75_.jpg").manufacturer("QUE")
				.productIndex("0").productgroup("DVD").productId("9780789799999").productidtype("EAN").source("Amazon")
				.sourceid("789724999").title("The Choocolate Factory").build();
		return product;
	}
}
