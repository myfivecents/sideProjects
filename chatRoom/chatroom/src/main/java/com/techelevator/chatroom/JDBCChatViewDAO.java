package com.techelevator.chatroom;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCChatViewDAO implements ChatViewDAO {
	private JdbcTemplate jdbcTemplate;
	
	public JDBCChatViewDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<ChatView> getAllChats() {
		List<ChatView> allChats = new ArrayList<ChatView>();
		String sqlAllChats = "SELECT * " +
							 "FROM account_chat";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlAllChats);
		while(rowSet.next()) {
			ChatView chat = new ChatView();
			chat.setAccountId(rowSet.getInt("accpunt_id"));
			chat.setChat_id(rowSet.getInt("chat_id"));
			allChats.add(chat);
		}
		return null;
	}

	public ChatView addAccountToChat(int accountId, int chatId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeAccountFromChat(int accountId) {
		// TODO Auto-generated method stub
		
	}

}
