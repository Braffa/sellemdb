package com.braffa.sellem.datafortesting;

import java.util.Date;

import com.braffa.sellem.model.sql.UserToProduct;

public class UserToProductTestData {

	public UserToProduct insertUserToProductTable() {
		return new UserToProduct.UserToProductBuilder().userId("").productId("").productIndex("").crDate(new Date())
				.updDate(new Date()).build();
	}

	public UserToProduct insertUserToProductTable1() {
		return new UserToProduct.UserToProductBuilder().id(1).userId("georgie").productId("9781861005618").productIndex("0")
				.build();
	}

	public UserToProduct insertUserToProductTable2() {
		return new UserToProduct.UserToProductBuilder().id(2).userId("georgie").productId("9780789724410").productIndex("0")
				.build();
	}

	public UserToProduct insertUserToProductTable3() {
		return new UserToProduct.UserToProductBuilder().id(3).userId("Braffa").productId("9780789724410").productIndex("0")
				.build();
	}

	public UserToProduct insertUserToProductTable4() {
		return new UserToProduct.UserToProductBuilder().id(4).userId("Braffa").productId("9781861005618").productIndex("0")
				.build();
	}

	public UserToProduct insertUserToProductTable5() {
		return new UserToProduct.UserToProductBuilder().id(5).userId("Braffa").productId("978098056856").productIndex("0")
				.build();
	}

	public UserToProduct insertUserToProductTable6() {
		return new UserToProduct.UserToProductBuilder().id(6).userId("Braffa").productId("5050582388237").productIndex("0")
				.build();
	}

	public UserToProduct insertUserToProductTable7() {
		return new UserToProduct.UserToProductBuilder().id(7).userId("Braffa").productId("9781849386685").productIndex("0")
				.build();
	}

	public UserToProduct insertUserToProductTable8() {
		return new UserToProduct.UserToProductBuilder().id(8).userId("amanda33").productId("9999999999000").productIndex("4")
				.build();
	}

	public UserToProduct insertUserToProductTable9() {
		return new UserToProduct.UserToProductBuilder().id(9).userId("grimbo").productId("9999999999001").productIndex("0")
				.build();
	}
}
