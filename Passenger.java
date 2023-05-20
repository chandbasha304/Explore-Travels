package Kingk.travel;

import java.lang.annotation.Documented;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Passenger  {
	String firstname;
	String lastname;
	String mobile;
	String gender;
	String email;
	String password;
	String userWalletAmount;

	public Passenger(String string, String string2, String string3, String string4, String string5, String string6,
			String string7) {
		this.firstname = string;
		this.lastname = string2;
		this.mobile = string3;
		this.gender = string4;
		this.email = string5;
		this.password = string6;
		this.userWalletAmount = string7;
	}

	@Override
	public String toString() {
		return "Passenger [firstname=" + firstname + ", lastname=" + lastname + ", mobile=" + mobile + ", gender="
				+ gender + ", email=" + email + ", password=" + password + ", userWalletAmount=" + userWalletAmount
				+ "]";
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserWalletAmount() {
		return userWalletAmount;
	}

	public void setUserWalletAmount(String userWalletAmount) {
		this.userWalletAmount = userWalletAmount;
	}


	

}
