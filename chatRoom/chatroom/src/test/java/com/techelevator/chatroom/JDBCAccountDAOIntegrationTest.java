package com.techelevator.chatroom;

import java.util.List;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class JDBCAccountDAOIntegrationTest {
	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	private JDBCAccountDAO accountDao;
	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;
	
	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/chatroom");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections 
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}
	
	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	/* This method provides access to the DataSource for subclasses so that 
	 * they can instantiate a DAO for testing */
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	@Before
	public void setup() {
		accountDao = new JDBCAccountDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate();
	}
	
	@Test
	public void create_account_works() {
		Account account = accountDao.createAccount("UserName", "FirstName", "LastName", "Password!");
		
		Assert.assertNotNull("An account should have been created", account);
		Assert.assertTrue("The account should have a valid id", account.getAccountId() != 0);
		Assert.assertEquals("UserName", account.getUserName());
		Assert.assertEquals("FirstName", account.getFirstName());
		Assert.assertEquals("LastName", account.getLastName());
	}
	
	@Test
	public void get_account_by_id() {
		Account account = accountDao.createAccount("UserName", "FirstName", "LastName", "Password!");
		Account savedAccount = accountDao.getAccountByAcountId(account.getAccountId());
		
		assertAccountsMatch(account, savedAccount);
	}
	
	@Test
	public void get_all_accounts_returns_correctly() {
		Account account = accountDao.createAccount("UserName", "FirstName", "LastName", "Password!");
		List<Account> accountList = accountDao.getAllAccounts();
		
		Assert.assertTrue("There should be at least one account", accountList.size() >= 1);
		Assert.assertTrue("Our account should be in the list", accountList.contains(account));
	}
	
	@Test
	public void delete_account() {
		Account account = accountDao.createAccount("UserName", "FirstName", "LastName", "Password!");
		accountDao.removeAccountById(account.getAccountId());
		List<Account> accountList = accountDao.getAllAccounts();

		Assert.assertFalse("Our account should NOT be in the list", accountList.contains(account));
	}
	
	private void assertAccountsMatch(Account expected, Account actual) {
		Assert.assertEquals(expected.getAccountId(), actual.getAccountId());
		Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
		Assert.assertEquals(expected.getLastName(), actual.getLastName());
		Assert.assertEquals(expected.getUserName(), actual.getUserName());
	}
	
}










