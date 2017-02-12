package de.onlineferries.view;

import java.io.Serializable;
 
public class CustomerView implements Serializable {

	private static final long serialVersionUID = 1L;

	private int customer_id;
	private String firstname;
	private String lastname;
	private String password;
	private String street;
	private String zipcode;
	private String city;
	private String email;

	public CustomerView() {}
	

	public CustomerView(int customer_id, String firstname, String lastname, String password, String street,
			String zipcode, String city, String email) {
		super();
		this.customer_id = customer_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
		this.email = email;
	}
	
	public CustomerView(String firstname, String lastname, String password, String street,
			String zipcode, String city, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
		this.email = email;
	}


	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerView [customer_id=" + customer_id + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", password=" + password + "]";
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
