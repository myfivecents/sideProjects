package com.techelevator.chatroom;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCAccountDAO implements AccountDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCAccountDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Account> getAllAccounts() {
		List<Account> accountList = new ArrayList<Account>();
		String sqlQuery = "SELECT * " +
						  "FROM accounts";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlQuery);
		
		while (rowSet.next()) {
			Account account = makeAccountFromRowSet(rowSet);
			accountList.add(account);
		}
		
		return accountList;
	}

	public Account getAccountByAcountId(int id) {
		String sqlQuery = "SELECT * " +
						  "FROM accounts " +
						  "WHERE id = ?";
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlQuery, id);
		
		Account account = null;
		if(rowSet.next()) {
			account = makeAccountFromRowSet(rowSet);
		}
		
		return account;
	}

	public Account createAccount(String username, String firstName, String lastName, String password) {
		String sqlSiteId = "SELECT nextval(pg_get_serial_sequence('accounts', 'id'))";
		
		SqlRowSet nextAccountId = jdbcTemplate.queryForRowSet(sqlSiteId);
		nextAccountId.next();
		int accountId = nextAccountId.getInt(1);
		
		String sqlUpdate = "INSERT INTO accounts (id, username, first_name, last_name, password) VALUES (?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sqlUpdate, accountId, username, firstName, lastName, password);
		
		Account account = new Account();
		account.setAccountId(accountId);
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setUserName(username);
		
		return account;
	}

	public boolean removeAccountById(int id) {
		String accountRemove = "DELETE FROM accounts WHERE id = ?";
		return jdbcTemplate.update(accountRemove, id) == 1;
	}
	
	private Account makeAccountFromRowSet(SqlRowSet results) {
		Account account = new Account();
		account.setAccountId(results.getInt("id"));
		account.setUserName(results.getString("username"));
		account.setFirstName(results.getString("first_name"));
		account.setLastName(results.getString("last_name"));
		return account;
	}
	
}
