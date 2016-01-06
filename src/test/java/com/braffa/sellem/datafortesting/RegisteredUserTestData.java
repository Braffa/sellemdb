package com.braffa.sellem.datafortesting;

import com.braffa.sellem.model.sql.RegisteredUser;

public class RegisteredUserTestData {

	public RegisteredUser setUpRegisteredUser1() {
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("99").userId("dave123").email("dave@gmail.com")
				.firstname("Dave").lastname("Allen").password("kelly1233").telephone("0772 234654").build();
		return registeredUser;
	}

	public RegisteredUser setUpRegisteredUser2() {
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("99").userId("gordon456").email("gordon@gmail.com")
				.firstname("gordon").lastname("Mills").password("kelly1233").telephone("1111 4444444").build();
		return registeredUser;
	}

	public RegisteredUser setUpRegisteredUser3() {
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("99").userId("naiomi876").email("naiomi@gmail.com")
				.firstname("naiomi").lastname("Smith").password("kelly1233").telephone("2222 5555555").build();
		return registeredUser;
	}

	public RegisteredUser setUpRegisteredUser4() {
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("99").userId("rachel33").email("rachel@gmail.com")
				.firstname("rachel").lastname("scott").password("kelly1233").telephone("3333 6666666").build();
		return registeredUser;
	}

	public RegisteredUser setUpRegisteredUser5() {
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("99").userId("Braffa")
				.email("dave.rogers@yahoo.co.uk").firstname("Dave").lastname("Rogers").password("kelly1233").telephone("01388 445566")
				.build();
		return registeredUser;
	}

	public RegisteredUser setUpRegisteredUser6() {
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("99").userId("georgie")
				.email("george.smith@yahoo.co.uk").firstname("george").lastname("smith").password("kelly1233").telephone("01642 641834")
				.build();
		return registeredUser;
	}

	public RegisteredUser setUpRegisteredUser7() {
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("99").userId("Braffa")
				.email("andrew.jones@yahoo.co.uk").firstname("andrew").lastname("jones").password("amanda33").telephone("01642 356745")
				.build();
		return registeredUser;
	}

	public RegisteredUser setUpRegisteredUser8() {
		RegisteredUser registeredUser = new RegisteredUser.RegisteredUserBuilder().authorityLevel("99").userId("SUE123")
				.email("susan.smurf@yahoo.co.uk").firstname("susan").lastname("smurf").password("kelly1233").telephone("01388 731258")
				.build();
		return registeredUser;
	}

}