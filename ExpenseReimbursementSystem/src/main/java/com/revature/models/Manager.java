package com.revature.models;

public class Manager {
	private String username; 
	private String password;
	private int manager_id;  
	private String firstName; 
	private String lastName; 
	private String city; 
	private String state;
	
	
	
	public Manager(String username, String password, int manager_id, String firstName, String lastName, String city,
			String state) {
		super();
		this.username = username;
		this.password = password;
		this.manager_id = manager_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.state = state;
	}
	
	
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
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
	@Override
	public String toString() {
		return "Manager [username=" + username + ", password=" + password + ", manager_id=" + manager_id
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city + ", state=" + state + "]";
	} 
	
	
}
