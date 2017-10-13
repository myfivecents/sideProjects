package com.techelevator.chatroom;

import java.util.List;

public interface AccountDAO {
	public Account createAccount(String username, String firstName, String lastName, String password);
	public List<Account> getAllAccounts();
	public Account getAccountByAcountId(int id);
	public boolean removeAccountById(int id);
}
