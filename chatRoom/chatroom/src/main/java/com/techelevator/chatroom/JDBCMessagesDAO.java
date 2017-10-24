package com.techelevator.chatroom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCMessagesDAO implements MessagesDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCMessagesDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Messages> getAllMessages() {
		List<Messages> messageList = new ArrayList<Messages>();
		
		String sqlQueryStrings = "SELECT * " +
							     "FROM messages";
		SqlRowSet allMessages = jdbcTemplate.queryForRowSet(sqlQueryStrings);
		while (allMessages.next()) {
			Messages thisMessage = makeMessageFromRowSet(allMessages);
			messageList.add(thisMessage);
		}
		return messageList;
	}

	public List<Messages> getAllMessagesByUser(int userId) {
		List<Messages> messageList = new ArrayList<Messages>();
		String sqlQueryStrings = "SELECT * " +
						         "FROM messages " +
						         "WHERE user_id = ?";
		SqlRowSet allMessages = jdbcTemplate.queryForRowSet(sqlQueryStrings, userId);
		while (allMessages.next()) {
			Messages thisMessage = makeMessageFromRowSet(allMessages);
			messageList.add(thisMessage);
		}
		return messageList;
	}

	public List<Messages> getAllMessagesInChat(int chatId) {
		List<Messages> messageList = new ArrayList<Messages>();
		String sqlQueryStrings = "SELECT * " +
						         "FROM messages " +
						         "WHERE chat_id = ?";
		SqlRowSet allMessages = jdbcTemplate.queryForRowSet(sqlQueryStrings, chatId);
		while (allMessages.next()) {
			Messages thisMessage = makeMessageFromRowSet(allMessages);
			messageList.add(thisMessage);
		}
		return messageList;
	}

	public List<Messages> messagesBetweenDates(LocalDate fromDate, LocalDate toDate) {
		List<Messages> messageList = new ArrayList<Messages>();
		String sqlSites = "SELECT * " +
						  "FROM messages " +
						  "WHERE date_time >= ? AND date_time <= ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlSites, fromDate, toDate);
		while(rowSet.next()) {
			Messages post = makeMessageFromRowSet(rowSet);
			messageList.add(post);
		}
		return messageList;
	}

	public List<Messages> messagesSinceDate(LocalDate sinceDate) {
		List<Messages> messageList = new ArrayList<Messages>();
		String sqlSites = "SELECT * " +
						  "FROM messages " +
						  "WHERE date_time >= ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlSites, sinceDate);
		while(rowSet.next()) {
			Messages post = makeMessageFromRowSet(rowSet);
			messageList.add(post);
		}
		return messageList;
	}

	public Messages newMessage(LocalDate dateTime, int chatId, int accountId, String messageBody) {
		String sqlMessageId = "SELECT nextval(pg_get_serial_sequence('messages_post', 'id'))";
		
		SqlRowSet nextMessageId = jdbcTemplate.queryForRowSet(sqlMessageId);
		nextMessageId.next();
		int messagesId = nextMessageId.getInt(1);
		
		String sqlUpdate = "INSERT INTO messages_post (id, date_time, chat_id, account_id, message) VALUES (?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sqlUpdate, messagesId, dateTime, chatId, accountId, messageBody);
		
		Messages message = new Messages();
		message.setMessageId(messagesId);
		message.setAccountId(accountId);
		message.setDateTime(dateTime);
		message.setChatId(chatId);
		message.setMessageBody(messageBody);
		return message;
	}

	public Messages removeMessageByUserid() {
		
		return null;
	}
	
	private Messages makeMessageFromRowSet(SqlRowSet results) {
		Messages newMessage = new Messages();
		newMessage.setMessageId(results.getInt("id"));
		newMessage.setDateTime(results.getDate("date_time").toLocalDate());
		newMessage.setChatId(results.getInt("chat_id"));
		newMessage.setAccountId(results.getInt("account_id"));
		newMessage.setMessageBody(results.getString("message"));
		return null;
	}


}
