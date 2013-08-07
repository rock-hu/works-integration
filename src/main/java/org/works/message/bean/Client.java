package org.works.message.bean;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.Link;

@Link
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1599452370661043549L;

	@DataField(pos = 1, required = true)
	private String username;
	@DataField(pos = 2, required = true)
	private String firstName;
	@DataField(pos = 3, required = true)
	private String lastName;
	@DataField(pos = 4, required = true)
	private String adress;
	@DataField(pos = 5, required = true)
	private String phone;
	@DataField(pos = 6, required = true)
	private String mobile;
	@DataField(pos = 7, required = true)
	private String sex;
	@DataField(pos = 8, required = true)
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
