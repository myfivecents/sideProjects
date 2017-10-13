package com.techelevator.chatroom;

public class Account {
	private int accountId;
	private String userName;
	private String firstName;
	private String lastName;
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	@Override
	public boolean equals(Object other) {
		if(other == null || !(other instanceof Account)) {
			return false;
		} else {
			return accountId == ((Account)other).accountId;
		}
	}
}
