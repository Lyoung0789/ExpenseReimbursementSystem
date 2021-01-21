package com.revature.models;

public class User {
	private String username; 
	private String password;
	private int user_id;  
	private String firstName; 
	private String lastName; 
	private String title; 
	private String streetAddress; 
	private String city; 
	private String state; 
	private int zipcode; 
	


	public User(String username, String password, int user_id, String firstName, String lastName, String title,
			String streetAddress, String city, String state, int zipcode) {
		super();
		this.username = username;
		this.password = password;
		this.user_id = user_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}



	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", user_id=" + user_id + ", firstName="
				+ firstName + ", lastName=" + lastName + ", title=" + title + ", streetAddress=" + streetAddress
				+ ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + "]";
	}
	
	

	
	

}
