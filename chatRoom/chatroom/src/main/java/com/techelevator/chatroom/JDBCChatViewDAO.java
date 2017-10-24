package com.techelevator.chatroom;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCChatViewDAO implements ChatViewDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
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
		String sqlInsertChatRoom = "INSERT INTO account_chat (accout_id, chat_id) VALUES (?, ?)";
		
		jdbcTemplate.update(sqlInsertChatRoom, accountId, chatId);
		ChatView chatView = new ChatView();
		chatView.setAccountId(accountId);
		chatView.setChat_id(chatId);
		return chatView;

	}

	public boolean removeAccountFromChat(int accountId) {
		String sqlDeleteChatRoom = "DELETE FROM account_chat WHERE account_id = ?";
		int rowsImpacted = jdbcTemplate.update(sqlDeleteChatRoom, accountId);
		
		if (rowsImpacted > 0) {
			return true;
		} else {
			return false;
		}
	}

}
